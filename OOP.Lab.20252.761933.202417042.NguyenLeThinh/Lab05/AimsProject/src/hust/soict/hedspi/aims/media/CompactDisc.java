// File path: AimsProject/src/hust/soict/hedspi/aims/media/CompactDisc.java
package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;
import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Media implements Playable {
    private String artist;
    private List<Track> tracks = new ArrayList<>();

    public CompactDisc(int id, String title, String category, String artist, float cost) {
        super(id, title, category, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Track> getTracks() {
        return new ArrayList<>(tracks);
    }

    public void addTrack(Track track) {
        if (track == null) {
            return;
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
        }
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
        System.out.println("Playing CD: " + getTitle());
        System.out.println("- Artist: " + artist);
        System.out.println("- Category: " + getCategory());
        System.out.println("- Total length: " + getLength() + " minutes");
        System.out.println("- Cost: $" + getCost());
        if (tracks.isEmpty()) {
            System.out.println("No tracks available on this CD.");
            return;
        }
        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        return "CompactDisc{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", artist='" + artist + '\'' +
                ", tracks=" + tracks.size() +
                ", cost=" + getCost() +
                '}';
    }
}
