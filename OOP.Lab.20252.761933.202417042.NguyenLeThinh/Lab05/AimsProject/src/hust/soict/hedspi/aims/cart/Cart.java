// File path: AimsProject/src/hust/soict/hedspi/aims/cart/Cart.java
package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private List<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) throws LimitExceededException {
        if (media == null) {
            System.out.println("Cannot add a null media item to the cart.");
            return;
        }
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            throw new LimitExceededException("ERROR: The number of media in the cart has reached the limit (" + MAX_NUMBERS_ORDERED + ")");
        }
        itemsOrdered.add(media);
        System.out.println("Added to cart: " + media.getTitle() + " (id=" + media.getId() + ")");
    }

    public void removeMedia(Media media) {
        if (media == null) {
            System.out.println("Cannot remove a null media item from the cart.");
            return;
        }
        if (itemsOrdered.remove(media)) {
            System.out.println("Removed from cart: " + media.getTitle() + " (id=" + media.getId() + ")");
        } else {
            System.out.println("Media not found in cart: " + media.getTitle());
        }
    }

    public float totalCost() {
        float total = 0.0f;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public List<Media> searchByTitle(String title) {
        List<Media> results = new ArrayList<>();
        if (title == null || title.trim().isEmpty()) {
            return results;
        }
        String normalized = title.trim().toLowerCase();
        for (Media media : itemsOrdered) {
            if (media.getTitle() != null && media.getTitle().toLowerCase().contains(normalized)) {
                results.add(media);
            }
        }
        return results;
    }

    public void sortByTitleThenCost() {
        Collections.sort(itemsOrdered, Comparator.comparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Media::getCost));
        System.out.println("Cart sorted by title then cost.");
    }

    public void sortByCostThenTitle() {
        Collections.sort(itemsOrdered, Comparator.comparing(Media::getCost)
                .thenComparing(Media::getTitle, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Cart sorted by cost then title.");
    }

    public void print() {
        System.out.println("\n--- Current Cart ---");
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }
        for (int index = 0; index < itemsOrdered.size(); index++) {
            Media media = itemsOrdered.get(index);
            System.out.println((index + 1) + ". " + media.toString());
        }
        System.out.printf("Total cost: $%.2f%n", totalCost());
    }

    public void clear() {
        itemsOrdered.clear();
    }

    public boolean isEmpty() {
        return itemsOrdered.isEmpty();
    }

    public List<Media> getItemsOrdered() {
        return new ArrayList<>(itemsOrdered);
    }
}
