// File path: AimsProject/src/hust/soict/hedspi/aims/store/Store.java
package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private List<Media> items = new ArrayList<>();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot add null media to the store.");
            return;
        }
        items.add(media);
        System.out.println("Added to store: " + media.getTitle() + " (id=" + media.getId() + ")");
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove null media from the store.");
            return;
        }
        if (items.remove(media)) {
            System.out.println("Removed from store: " + media.getTitle() + " (id=" + media.getId() + ")");
        } else {
            System.out.println("Media not found in store: " + media.getTitle());
        }
    }

    public Media getMediaById(int id) {
        for (Media media : items) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public List<Media> getItems() {
        return new ArrayList<>(items);
    }

    public void print() {
        System.out.println("\n--- Store Inventory ---");
        if (items.isEmpty()) {
            System.out.println("The store currently has no media items.");
            return;
        }
        for (Media media : items) {
            System.out.println(media.toString());
        }
        System.out.println("-----------------------");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getNextMediaId() {
        int maxId = 0;
        for (Media media : items) {
            if (media.getId() > maxId) {
                maxId = media.getId();
            }
        }
        return maxId + 1;
    }
}
