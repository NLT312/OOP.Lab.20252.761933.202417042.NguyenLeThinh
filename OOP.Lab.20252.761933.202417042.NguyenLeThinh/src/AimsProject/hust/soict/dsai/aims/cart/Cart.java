package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;

public class Cart {
    private List<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) {
        itemsOrdered.add(media);
        System.out.println("Added to cart: " + media.toString());
    }

    public void removeMedia(Media media) {
        Iterator<Media> it = itemsOrdered.iterator();
        while (it.hasNext()) {
            if (it.next().equals(media)) {
                it.remove();
                System.out.println("Removed from cart: " + media.toString());
                return;
            }
        }
        System.out.println("Media not found in cart!");
    }

    public float totalCost() {
        float total = 0;
        for (Media m : itemsOrdered) {
            total += m.getCost();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***********************CART***********************\n");
        sb.append("Ordered Items:\n");
        int index = 1;
        for (Media m : itemsOrdered) {
            sb.append(index++).append(". ").append(m.toString()).append("\n");
        }
        sb.append("Total cost: ").append(totalCost()).append("\n");
        sb.append("***************************************************");
        return sb.toString();
    }

    public void searchId(int id) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.getId() == id) {
                System.out.println(m.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No item with ID " + id + " found in cart.");
        }
    }
//
    public void searchTitle(String title) {
        boolean found = false;
        for (Media m : itemsOrdered) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(m.toString());
                found = true;
            }
        }
        //
        if (!found) {
            System.out.println("No item with title containing '" + title + "' found in cart.");
        }
    }

    public boolean isMatch(String title) {
        for (Media m : itemsOrdered) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart sorted by title-cost.");
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart sorted by cost-title.");
    }

    public List<Media> getItems() {
        return new ArrayList<>(itemsOrdered);
    }
}
