package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King", "Animation", "Roger Allers", 87, 19.95f
        );
        anOrder.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars", "Science Fiction", "George Lucas", 87, 24.95f
        );
        anOrder.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Aladdin", "Animation", "John Musker", 90, 18.99f
        );
        anOrder.addMedia(dvd3);

        System.out.println("Total cost is: " + anOrder.totalCost());

        System.out.println("\nRemoving Star Wars...");
        anOrder.removeMedia(dvd2);

        System.out.println("Total cost is: " + anOrder.totalCost());

        System.out.println("\nTrying to remove Star Wars again...");
        anOrder.removeMedia(dvd2);

        System.out.println("\nTrying to remove a disc not in the cart...");
        DigitalVideoDisc dvd4 = new DigitalVideoDisc(
                "Inception", "Science Fiction", "Christopher Nolan", 148, 14.99f
        );
        anOrder.removeMedia(dvd4);

        System.out.println("\nFinal cart contents:");
        anOrder.print();
    }
}