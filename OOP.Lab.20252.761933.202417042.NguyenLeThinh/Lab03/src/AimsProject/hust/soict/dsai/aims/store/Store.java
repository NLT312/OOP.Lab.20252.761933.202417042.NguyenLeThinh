package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;

public class Store {
    public static final int MAX_STORE = 100;
    private Media[] itemsInStore = new Media[MAX_STORE];
    private int qtyStore = 0;

    public void addMedia(Media media) {
        if (qtyStore < MAX_STORE) {
            itemsInStore[qtyStore] = media;
            qtyStore++;
            System.out.println("Added to store: " + media.toString());
        } else {
            System.out.println("Store is full!");
        }
    }

    public void removeMedia(Media media) {
        int index = -1;
        for (int i = 0; i < qtyStore; i++) {
            if (itemsInStore[i].equals(media)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < qtyStore - 1; i++) {
                itemsInStore[i] = itemsInStore[i + 1];
            }
            itemsInStore[qtyStore - 1] = null;
            qtyStore--;
            System.out.println("Removed from store: " + media.toString());
        } else {
            System.out.println("Media not found in store!");
        }
    }

    public Media getItem(int id) {
        for (int i = 0; i < qtyStore; i++) {
            if (itemsInStore[i].getId() == id) {
                return itemsInStore[i];
            }
        }
        return null;
    }
    // update store
    public void printStore() {
        System.out.println("*** STORE ITEMS ***");
        for (int i = 0; i < qtyStore; i++) {
            System.out.println((i + 1) + ". " + itemsInStore[i].toString());
        }
    }
    //
}
