package hust.soict.dsai.aims.cart;

import java.util.ArrayList;
import java.util.Collections;

import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (!itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("The media has been added: " + media.getTitle());
        } else {
            System.out.println("The media already exists in cart: " + media.getTitle());
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("The media has been removed: " + media.getTitle());
        } else {
            System.out.println("The media does not exist in cart: " + media.getTitle());
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        for (Media media : itemsOrdered) {
            System.out.println(media.toString());
        }

        System.out.println("Total cost: " + totalCost());
        System.out.println("**************************************************");
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public int countDVDs() {
        int count = 0;
        for (Media media : itemsOrdered) {
            if (media instanceof hust.soict.dsai.aims.media.DigitalVideoDisc) {
                count++;
            }
        }
        return count;
    }

    public void emptyCart() {
        itemsOrdered.clear();
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}