package hust.soict.hedspi.aims.screen.customer;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();
        
        // Add dummy media to test the UI
        store.addMedia(new DigitalVideoDisc(1, "The Lion King", "Animation", 19.95f));
        store.addMedia(new DigitalVideoDisc(2, "Star Wars", "Science Fiction", 24.95f));
        store.addMedia(new DigitalVideoDisc(3, "Aladdin", "Animation", 18.99f));
        
        CompactDisc cd = new CompactDisc(4, "Adele 21", "Music", "Adele", 15.00f);
        store.addMedia(cd);
        
        Book book = new Book(5, "Harry Potter", "Fantasy", 20.00f);
        store.addMedia(book);
        
        store.addMedia(new DigitalVideoDisc(6, "The Matrix", "Science Fiction", 19.99f));
        store.addMedia(new DigitalVideoDisc(7, "Titanic", "Romance", 14.50f));
        store.addMedia(new DigitalVideoDisc(8, "Inception", "Science Fiction", 18.50f));
        
        // Add something to cart to test initially
        cart.addMedia(cd);
        cart.addMedia(book);

        launch(args);
    }
}
