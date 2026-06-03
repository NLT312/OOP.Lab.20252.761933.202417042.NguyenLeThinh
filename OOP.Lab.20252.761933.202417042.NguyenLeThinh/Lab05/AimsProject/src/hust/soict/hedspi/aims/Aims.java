// File path: AimsProject/src/hust/soict/hedspi/aims/Aims.java
package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;

import java.util.List;
import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = initializeStore();
        Cart cart = new Cart();

        while (true) {
            printMainMenu();
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1:
                    storeMenu(scanner, store, cart);
                    break;
                case 2:
                    updateStoreMenu(scanner, store);
                    break;
                case 3:
                    cartMenu(scanner, cart);
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 0-3.");
            }
        }
    }

    private static Store initializeStore() {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(2, "The Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);

        Book book = new Book(3, "The Alchemist", "Fiction", 16.50f);
        book.addAuthor("Paulo Coelho");

        CompactDisc cd = new CompactDisc(4, "Random Access Memories", "Soundtrack", "Daft Punk", 13.99f);
        cd.addTrack(new Track("Give Life Back to Music", 4));
        cd.addTrack(new Track("Instant Crush", 5));
        cd.addTrack(new Track("Lose Yourself to Dance", 5));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book);
        store.addMedia(cd);

        return store;
    }

    private static void printMainMenu() {
        System.out.println("\n===== AIMS MAIN MENU =====");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
    }

    private static void storeMenu(Scanner scanner, Store store, Cart cart) {
        while (true) {
            System.out.println("\n--- Store Menu ---");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");

            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1:
                    viewMediaDetails(scanner, store, cart);
                    break;
                case 2:
                    addStoreMediaToCart(scanner, store, cart);
                    break;
                case 3:
                    playMedia(scanner, store.getItems());
                    break;
                case 4:
                    cart.print();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 0-4.");
            }
        }
    }

    private static void viewMediaDetails(Scanner scanner, Store store, Cart cart) {
        if (store.isEmpty()) {
            System.out.println("The store is empty.");
            return;
        }
        store.print();
        int id = readInt(scanner, "Enter the id of the media to view details: ");
        Media media = store.getMediaById(id);
        if (media == null) {
            System.out.println("Media not found with id " + id + ".");
            return;
        }
        System.out.println(media.toString());
        while (true) {
            System.out.println("1. Add to cart");
            System.out.println("2. Play media");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    return;
                case 2:
                    if (media instanceof Playable) {
                        try {
                            ((Playable) media).play();
                        } catch (PlayerException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 0-2.");
            }
        }
    }

    private static void addStoreMediaToCart(Scanner scanner, Store store, Cart cart) {
        if (store.isEmpty()) {
            System.out.println("The store is empty.");
            return;
        }
        store.print();
        int id = readInt(scanner, "Enter the id of the media to add to cart: ");
        Media media = store.getMediaById(id);
        if (media == null) {
            System.out.println("Media not found with id " + id + ".");
            return;
        }
        try {
            cart.addMedia(media);
        } catch (LimitExceededException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void playMedia(Scanner scanner, List<Media> mediaList) {
        if (mediaList.isEmpty()) {
            System.out.println("No media available.");
            return;
        }
        for (Media media : mediaList) {
            System.out.println(media.toString());
        }
        int id = readInt(scanner, "Enter the id of the media you want to play: ");
        Media media = null;
        for (Media item : mediaList) {
            if (item.getId() == id) {
                media = item;
                break;
            }
        }
        if (media == null) {
            System.out.println("Media not found with id " + id + ".");
            return;
        }
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static void updateStoreMenu(Scanner scanner, Store store) {
        while (true) {
            System.out.println("\n--- Update Store Menu ---");
            System.out.println("1. Add media to store");
            System.out.println("2. Remove media from store");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1:
                    addNewMediaToStore(scanner, store);
                    break;
                case 2:
                    removeMediaFromStore(scanner, store);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 0-2.");
            }
        }
    }

    private static void addNewMediaToStore(Scanner scanner, Store store) {
        System.out.println("Choose media type to add:");
        System.out.println("1. DVD");
        System.out.println("2. Book");
        System.out.println("3. CD");
        int choice = readInt(scanner, "Choose an option: ");
        int nextId = store.getNextMediaId();
        switch (choice) {
            case 1:
                String title = readNonEmptyString(scanner, "Enter DVD title: ");
                String category = readNonEmptyString(scanner, "Enter DVD category: ");
                String director = readNonEmptyString(scanner, "Enter DVD director: ");
                int length = readInt(scanner, "Enter DVD length (minutes): ");
                float cost = readFloat(scanner, "Enter DVD cost: ");
                store.addMedia(new DigitalVideoDisc(nextId, title, category, director, length, cost));
                break;
            case 2:
                String bookTitle = readNonEmptyString(scanner, "Enter book title: ");
                String bookCategory = readNonEmptyString(scanner, "Enter book category: ");
                float bookCost = readFloat(scanner, "Enter book cost: ");
                Book book = new Book(nextId, bookTitle, bookCategory, bookCost);
                String author = readNonEmptyString(scanner, "Enter author name: ");
                book.addAuthor(author);
                store.addMedia(book);
                break;
            case 3:
                String cdTitle = readNonEmptyString(scanner, "Enter CD title: ");
                String cdCategory = readNonEmptyString(scanner, "Enter CD category: ");
                String artist = readNonEmptyString(scanner, "Enter CD artist: ");
                float cdCost = readFloat(scanner, "Enter CD cost: ");
                CompactDisc cd = new CompactDisc(nextId, cdTitle, cdCategory, artist, cdCost);
                System.out.println("Add tracks to the CD. Enter 0 for title to stop.");
                while (true) {
                    String trackTitle = readNonEmptyString(scanner, "Enter track title (or 0 to stop): ");
                    if ("0".equals(trackTitle)) {
                        break;
                    }
                    int trackLength = readInt(scanner, "Enter track length (minutes): ");
                    cd.addTrack(new Track(trackTitle, trackLength));
                }
                store.addMedia(cd);
                break;
            default:
                System.out.println("Invalid choice. Returning to update store menu.");
        }
    }

    private static void removeMediaFromStore(Scanner scanner, Store store) {
        if (store.isEmpty()) {
            System.out.println("The store is empty.");
            return;
        }
        store.print();
        int id = readInt(scanner, "Enter the id of the media to remove: ");
        Media media = store.getMediaById(id);
        if (media == null) {
            System.out.println("Media not found with id " + id + ".");
            return;
        }
        store.removeMedia(media);
    }

    private static void cartMenu(Scanner scanner, Cart cart) {
        while (true) {
            System.out.println("\n--- Cart Menu ---");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case 1:
                    filterCart(scanner, cart);
                    break;
                case 2:
                    sortCart(scanner, cart);
                    break;
                case 3:
                    removeMediaFromCart(scanner, cart);
                    break;
                case 4:
                    playMediaInCart(scanner, cart);
                    break;
                case 5:
                    placeOrder(cart);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 0-5.");
            }
        }
    }

    private static void filterCart(Scanner scanner, Cart cart) {
        System.out.println("Filter by:");
        System.out.println("1. Id");
        System.out.println("2. Title");
        int choice = readInt(scanner, "Choose an option: ");
        switch (choice) {
            case 1:
                int id = readInt(scanner, "Enter the media id to search: ");
                Media found = cart.searchById(id);
                if (found != null) {
                    System.out.println(found.toString());
                } else {
                    System.out.println("No media found with id " + id + ".");
                }
                break;
            case 2:
                String title = readNonEmptyString(scanner, "Enter title keyword to search: ");
                List<Media> results = cart.searchByTitle(title);
                if (results.isEmpty()) {
                    System.out.println("No media items matched title: " + title);
                } else {
                    for (Media media : results) {
                        System.out.println(media.toString());
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Returning to cart menu.");
        }
    }

    private static void sortCart(Scanner scanner, Cart cart) {
        System.out.println("Sort by:");
        System.out.println("1. Title then cost");
        System.out.println("2. Cost then title");
        int choice = readInt(scanner, "Choose an option: ");
        switch (choice) {
            case 1:
                cart.sortByTitleThenCost();
                cart.print();
                break;
            case 2:
                cart.sortByCostThenTitle();
                cart.print();
                break;
            default:
                System.out.println("Invalid choice. Returning to cart menu.");
        }
    }

    private static void removeMediaFromCart(Scanner scanner, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }
        cart.print();
        int id = readInt(scanner, "Enter the media id to remove from cart: ");
        Media media = cart.searchById(id);
        if (media == null) {
            System.out.println("No media found with id " + id + " in the cart.");
            return;
        }
        cart.removeMedia(media);
    }

    private static void playMediaInCart(Scanner scanner, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }
        cart.print();
        int id = readInt(scanner, "Enter the media id to play: ");
        Media media = cart.searchById(id);
        if (media == null) {
            System.out.println("No media found with id " + id + " in the cart.");
            return;
        }
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    private static void placeOrder(Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("There is no media in the cart to place an order.");
            return;
        }
        System.out.printf("Order placed successfully. Total cost: $%.2f%n", cart.totalCost());
        cart.clear();
    }

    private static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static float readFloat(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            try {
                return Float.parseFloat(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    private static String readNonEmptyString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty.");
        }
    }
}
