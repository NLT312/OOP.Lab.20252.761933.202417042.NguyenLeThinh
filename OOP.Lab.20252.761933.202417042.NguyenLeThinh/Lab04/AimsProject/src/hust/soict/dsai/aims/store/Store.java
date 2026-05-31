package hust.soict.dsai.aims.store;

import java.util.ArrayList;

import hust.soict.dsai.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media has been added to store: " + media.getTitle());
        } else {
            System.out.println("The media already exists in store: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("The media has been removed from store: " + media.getTitle());
        } else {
            System.out.println("The media does not exist in store: " + media.getTitle());
        }
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");

        for (Media media : itemsInStore) {
            System.out.println(media.toString());
        }

        System.out.println("***************************************************");
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}