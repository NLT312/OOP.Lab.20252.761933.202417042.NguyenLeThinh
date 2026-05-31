package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;

public class CartTest {
    
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Test DVD", "Action", "Dir", 120, 10.5f);
        Book book = new Book("Test Book", "Fiction", "Author", 25f);
        cart.addMedia(dvd1);
        cart.addMedia(book);
        System.out.println(cart);
        cart.searchTitle("Test");
        cart.searchId(dvd1.getId());
        System.out.println("Match 'Test DVD': " + cart.isMatch("Test DVD"));
        cart.removeMedia(dvd1);
        System.out.println("After remove: \\n" + cart);
        //
    }
}
