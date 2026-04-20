"""
app.py  —  Flask backend for Bangkok A* Multi-Modal Routing
Run with:  python app.py

Endpoints
─────────
GET  /                      → User map (index.html)
GET  /admin                 → Admin panel (admin.html)
GET  /api/status            → Graph load status
POST /api/route             → Find route (JSON body: start_lat, start_lng, end_lat, end_lng, mode)
GET  /api/graph/stations    → All rail stations + rail/transfer edge geometries
GET  /api/graph/edges       → Paginated edge list (all modes); ?page=N&per_page=M&mode=rail
GET  /api/graph/rail_edges  → Shortcut: only rail + transfer edges
POST /api/admin/block       → Block an edge {u, v, key}
POST /api/admin/unblock     → Unblock an edge {u, v, key}
POST /api/admin/unblock_all → Clear all blocks
GET  /api/admin/blocked     → List all currently blocked edges
"""

import logging
import os
import json
import threading
from flask import Flask, request, jsonify, render_template, send_file

from graph_builder import build_graph, build_walk_edge_cache, WALK_CACHE_PATH
from astar import astar_route
from schedule_data import _HEADWAYS, LINE_SPEED_KMH, operating_hours

logging.basicConfig(level=logging.INFO, format="%(asctime)s %(levelname)s %(message)s")
log = logging.getLogger(__name__)

app = Flask(__name__)

# ─── Shared state ─────────────────────────────────────────────────────────────
state = {
    "G":     None,
    "ready": False,
    "error": None,
}
_G_lock  = threading.Lock()
_blocked: set = set()   # set of (u, v, key) tuples – blocked at runtime


def _load_graph_background():
    """Load (or rebuild) the super-graph in a background thread."""
    try:
        log.info("Loading super-graph in background …")
        G = build_graph()
        with _G_lock:
            state["G"]     = G
            state["ready"] = True
        log.info("Graph ready: %d nodes, %d edges",
                 G.number_of_nodes(), G.number_of_edges())

        # Build walk edge cache if it doesn't exist yet (nhóm 17 xlsx pattern)
        if not os.path.exists(WALK_CACHE_PATH):
            log.info("Walk edge cache missing — building in background …")
            threading.Thread(
                target=build_walk_edge_cache, args=(G,), daemon=True
            ).start()

    except Exception as exc:
        with _G_lock:
            state["error"] = str(exc)
        log.error("Graph load failed: %s", exc)


# Start loading immediately on server startup
threading.Thread(target=_load_graph_background, daemon=True).start()


# ─── Helper ───────────────────────────────────────────────────────────────────

def _require_graph():
    """Return (G, None, None) when ready, or (None, response, status) on error."""
    if state["error"]:
        return None, jsonify({"error": f"Graph load failed: {state['error']}"}), 500
    if not state["ready"] or state["G"] is None:
        return None, jsonify({"error": "Graph still loading. Please wait and try again."}), 503
    return state["G"], None, None


# ─── Pages ────────────────────────────────────────────────────────────────────

@app.route("/")
def index():
    return render_template("index.html")


@app.route("/admin")
def admin():
    return render_template("admin.html")


# ─── Status ───────────────────────────────────────────────────────────────────

@app.route("/api/status")
def api_status():
    G = state["G"]
    return jsonify({
        "ready":         state["ready"],
        "error":         state["error"],
        "nodes":         G.number_of_nodes() if G else 0,
        "edges":         G.number_of_edges() if G else 0,
        "blocked_count": len(_blocked),
    })


# ─── Routing ──────────────────────────────────────────────────────────────────

@app.route("/api/route", methods=["POST"])
def api_route():
    G, err_resp, status = _require_graph()
    if err_resp:
        return err_resp, status

    data = request.get_json(force=True)
    try:
        start_lat = float(data["start_lat"])
        start_lng = float(data["start_lng"])
        end_lat   = float(data["end_lat"])
        end_lng   = float(data["end_lng"])
        mode      = data.get("mode", "multimodal")

        # departure_time: ISO string "HH:MM" or "2026-04-05T08:30" or omit for now
        dep_str = data.get("departure_time", None)
        departure_time_s = None
        if dep_str:
            # Accept both HH:MM and datetime-local formats
            dep_str = str(dep_str).strip()
            if "T" in dep_str:
                dep_str = dep_str.split("T")[1]   # "2026-04-05T08:30" → "08:30"
            parts = dep_str.split(":")
            h, m = int(parts[0]), int(parts[1])
            departure_time_s = h * 3600 + m * 60
    except (KeyError, TypeError, ValueError) as exc:
        return jsonify({"error": f"Bad request: {exc}"}), 400

    try:
        with _G_lock:
            result = astar_route(
                G, start_lat, start_lng, end_lat, end_lng,
                mode=mode, blocked=set(_blocked),
                departure_time_s=departure_time_s,
            )

        def s_to_hhmm(secs):
            secs = int(secs) % 86400
            return f"{secs//3600:02d}:{(secs%3600)//60:02d}"

        return jsonify({
            "ok":               True,
            "coords":           result["coords"],
            "segments":         result["segments"],
            "distance_m":       result["distance_m"],
            "time_s":           result["time_s"],
            "walk_time_s":      result.get("walk_time_s", 0),
            "rail_time_s":      result.get("rail_time_s", 0),
            "transfer_time_s":  result.get("transfer_time_s", 0),
            "departure_time":   s_to_hhmm(result.get("departure_time_s", 0)),
            "arrival_time":     s_to_hhmm(result.get("arrival_time_s", 0)),
        })
    except ValueError as exc:
        return jsonify({"error": str(exc)}), 404
    except Exception as exc:
        log.exception("Route error")
        return jsonify({"error": str(exc)}), 500


# ─── Admin – block / unblock ──────────────────────────────────────────────────

@app.route("/api/admin/block", methods=["POST"])
def api_block():
    data = request.get_json(force=True)
    try:
        u   = data["u"]
        v   = data["v"]
        key = int(data.get("key", 0))
    except (KeyError, TypeError, ValueError) as exc:
        return jsonify({"error": str(exc)}), 400

    _blocked.add((u, v, key))
    # Also block the reverse direction (all keys)
    G, _, _ = _require_graph()
    if G and G.has_edge(v, u):
        for rev_key in list(G[v][u].keys()):
            _blocked.add((v, u, rev_key))

    log.info("Blocked edge (%s, %s, %d). Total blocked: %d", u, v, key, len(_blocked))
    return jsonify({"ok": True, "blocked_count": len(_blocked)})


@app.route("/api/admin/unblock", methods=["POST"])
def api_unblock():
    data = request.get_json(force=True)
    try:
        u   = data["u"]
        v   = data["v"]
        key = int(data.get("key", 0))
    except (KeyError, TypeError, ValueError) as exc:
        return jsonify({"error": str(exc)}), 400

    _blocked.discard((u, v, key))
    _blocked.discard((v, u, key))
    # Discard all reverse keys too
    G, _, _ = _require_graph()
    if G and G.has_edge(v, u):
        for rev_key in list(G[v][u].keys()):
            _blocked.discard((v, u, rev_key))

    return jsonify({"ok": True, "blocked_count": len(_blocked)})


@app.route("/api/admin/unblock_all", methods=["POST"])
def api_unblock_all():
    _blocked.clear()
    return jsonify({"ok": True})


@app.route("/api/admin/blocked", methods=["GET"])
def api_blocked_list():
    return jsonify({
        "blocked": [{"u": u, "v": v, "key": k} for u, v, k in _blocked]
    })


# ─── Graph edge list (admin map) ──────────────────────────────────────────────

@app.route("/api/graph/edges")
def api_edges():
    """
    Return a paginated list of graph edges with geometry for the admin map.
    Query params: page (int, default 1), per_page (int, default 500), mode (str, optional).
    """
    G, err_resp, status = _require_graph()
    if err_resp:
        return err_resp, status

    page        = int(request.args.get("page", 1))
    per_page    = int(request.args.get("per_page", 500))
    mode_filter = request.args.get("mode", None)

    blocked_lookup = {(str(a), str(b), c) for a, b, c in _blocked}
    edges_out = []

    for u, v, k, data in G.edges(keys=True, data=True):
        if mode_filter and data.get("mode") != mode_filter:
            continue
        geom = data.get("geometry")
        if geom is None:
            u_d, v_d = G.nodes[u], G.nodes[v]
            if "y" not in u_d or "y" not in v_d:
                continue
            coords = [[u_d["y"], u_d["x"]], [v_d["y"], v_d["x"]]]
        else:
            coords = [[lat, lng] for lng, lat in geom.coords]

        edges_out.append({
            "u":       str(u),
            "v":       str(v),
            "key":     k,
            "mode":    data.get("mode", "walk"),
            "name":    str(data.get("name", "")),
            "coords":  coords,
            "blocked": (str(u), str(v), k) in blocked_lookup,
        })

    start = (page - 1) * per_page
    chunk = edges_out[start: start + per_page]
    return jsonify({
        "total":    len(edges_out),
        "page":     page,
        "per_page": per_page,
        "edges":    chunk,
    })


@app.route("/api/graph/rail_edges")
def api_rail_edges():
    """Return only rail + transfer edges (deduped by unordered pair)."""
    G, err_resp, status = _require_graph()
    if err_resp:
        return err_resp, status

    edges_out, seen = [], set()
    for u, v, k, data in G.edges(keys=True, data=True):
        mode = data.get("mode", "")
        if mode not in ("rail", "transfer"):
            continue
        pair = (min(str(u), str(v)), max(str(u), str(v)))
        if pair in seen:
            continue
        seen.add(pair)
        geom = data.get("geometry")
        if geom is None:
            u_d, v_d = G.nodes[u], G.nodes[v]
            if "y" not in u_d or "y" not in v_d:
                continue
            coords = [[u_d["y"], u_d["x"]], [v_d["y"], v_d["x"]]]
        else:
            coords = [[lat, lng] for lng, lat in geom.coords]

        edges_out.append({
            "u":      str(u),
            "v":      str(v),
            "key":    k,
            "mode":   mode,
            "name":   str(data.get("name", "")),
            "coords": coords,
        })
    return jsonify({"edges": edges_out})


@app.route("/api/graph/stations")
def api_stations():
    """Return all rail station nodes + rail/transfer edge geometries."""
    G, err, status = _require_graph()
    if err:
        return err, status

    stations, edges = [], []
    seen_edges = set()

    for n, data in G.nodes(data=True):
        if str(n).startswith("rail_"):
            stations.append({
                "id":   str(n),
                "lat":  data.get("y"),
                "lng":  data.get("x"),
                "name": data.get("name", ""),
            })

    for u, v, k, data in G.edges(keys=True, data=True):
        mode = data.get("mode", "")
        # Only draw actual rail track lines between stations — NOT transfer edges
        # Transfer edges connect walk_nodes to rail_stations and create visual spaghetti
        if mode != "rail":
            continue
        pair = tuple(sorted([str(u), str(v)]))
        if pair in seen_edges:
            continue
        seen_edges.add(pair)
        # Build coords from node positions (transfer edge geometry not needed)
        u_d = G.nodes[u]; v_d = G.nodes[v]
        if "y" not in u_d or "y" not in v_d:
            continue
        coords = [[u_d["y"], u_d["x"]], [v_d["y"], v_d["x"]]]
        edges.append({"coords": coords, "mode": mode})

    return jsonify({"stations": stations, "edges": edges})



# ─── Schedule (GTFS headway data per line) ───────────────────────────────────

@app.route("/api/schedule")
def api_schedule():
    """
    Return real GTFS headways per line per hour.
    Used by the frontend schedule modal.
    """
    result = []
    for line, hourly in sorted(_HEADWAYS.items()):
        first_h, last_h = operating_hours(line)
        per_hour = [
            {"h": h, "headway_min": hw}
            for h, hw in sorted(hourly.items())
            if hw < 900
        ]
        speed_kmh = LINE_SPEED_KMH.get(line, 35)
        result.append({
            "line":        line,
            "first_hour":  first_h,
            "last_hour":   last_h,
            "speed_kmh":   speed_kmh,
            "per_hour":    per_hour,
        })
    return jsonify({"ok": True, "lines": result})


# ─── Walk edge cache (for admin map overlay) ──────────────────────────────────

@app.route("/api/graph/walk_cache")
def api_walk_cache():
    """
    Serve the pre-built walk edge JSON cache (major roads only).
    Mirrors nhóm 17's xlsx approach: pre-export graph data once → serve as static file.
    If cache doesn't exist yet, build it on-demand (blocks until done).
    """
    if not os.path.exists(WALK_CACHE_PATH):
        G, err_resp, status = _require_graph()
        if err_resp:
            return err_resp, status
        build_walk_edge_cache(G)

    return send_file(
        WALK_CACHE_PATH,
        mimetype="application/json",
        max_age=3600,
    )


# ─── Run ──────────────────────────────────────────────────────────────────────

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=False, use_reloader=False)
