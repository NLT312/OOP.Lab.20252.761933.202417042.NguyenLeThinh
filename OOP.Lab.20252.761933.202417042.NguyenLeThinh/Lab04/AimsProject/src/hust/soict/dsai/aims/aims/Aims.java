package hust.soict.dsai.aims.aims;

import java.util.Scanner;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();

        initData(store);

        int choice;

        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewStore(store, cart);
                    break;
                case 2:
                    updateStore(store);
                    break;
                case 3:
                    viewCart(cart);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public static void initData(Store store) {
        Book book = new Book(1, "Java Core", "Programming", 10.5f);
        book.addAuthor("John Smith");

        DigitalVideoDisc dvd = new DigitalVideoDisc(
                2, "Interstellar", "Sci-fi", 20.0f, 169, "Christopher Nolan"
        );

        CompactDisc cd = new CompactDisc(
                3, "Best Hits", "Music", 15.0f, "Director A", "Artist A"
        );

        cd.addTrack(new Track("Song A", 3));
        cd.addTrack(new Track("Song B", 4));

        store.addMedia(book);
        store.addMedia(dvd);
        store.addMedia(cd);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void viewStore(Store store, Cart cart) {
        int choice;

        do {
            store.printStore();
            storeMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    seeMediaDetails(store, cart);
                    break;
                case 2:
                    addMediaToCart(store, cart);
                    break;
                case 3:
                    playMediaFromStore(store);
                    break;
                case 4:
                    viewCart(cart);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public static void seeMediaDetails(Store store, Cart cart) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.searchByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        System.out.println(media.toString());

        int choice;
        do {
            mediaDetailsMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public static void addMediaToCart(Store store, Cart cart) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.searchByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        cart.addMedia(media);
        System.out.println("Number of DVDs in cart: " + cart.countDVDs());
    }

    public static void playMediaFromStore(Store store) {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.searchByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }

    public static void updateStore(Store store) {
        System.out.println("1. Add DVD");
        System.out.println("2. Remove media by title");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Id: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Category: ");
            String category = scanner.nextLine();

            System.out.print("Cost: ");
            float cost = Float.parseFloat(scanner.nextLine());

            System.out.print("Length: ");
            int length = Integer.parseInt(scanner.nextLine());

            System.out.print("Director: ");
            String director = scanner.nextLine();

            DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, cost, length, director);
            store.addMedia(dvd);

        } else if (choice == 2) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            Media media = store.searchByTitle(title);

            if (media == null) {
                System.out.println("Media not found.");
            } else {
                store.removeMedia(media);
            }
        }
    }

    public static void viewCart(Cart cart) {
        int choice;

        do {
            cart.print();
            cartMenu();

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    filterCart(cart);
                    break;
                case 2:
                    sortCart(cart);
                    break;
                case 3:
                    removeMediaFromCart(cart);
                    break;
                case 4:
                    playMediaFromCart(cart);
                    break;
                case 5:
                    System.out.println("An order is created.");
                    cart.emptyCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    public static void filterCart(Cart cart) {
        System.out.println("1. Filter by id");
        System.out.println("2. Filter by title");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Enter id: ");
            int id = Integer.parseInt(scanner.nextLine());

            Media media = cart.searchById(id);

            if (media == null) {
                System.out.println("Media not found.");
            } else {
                System.out.println(media);
            }

        } else if (choice == 2) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            Media media = cart.searchByTitle(title);

            if (media == null) {
                System.out.println("Media not found.");
            } else {
                System.out.println(media);
            }
        }
    }

    public static void sortCart(Cart cart) {
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            cart.sortByTitleCost();
        } else if (choice == 2) {
            cart.sortByCostTitle();
        }

        cart.print();
    }

    public static void removeMediaFromCart(Cart cart) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        Media media = cart.searchByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
        } else {
            cart.removeMedia(media);
        }
    }

    public static void playMediaFromCart(Cart cart) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        Media media = cart.searchByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        if (media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("This media cannot be played.");
        }
    }
}