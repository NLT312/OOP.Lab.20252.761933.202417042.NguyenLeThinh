package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.playable.Playable;
import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Demo store items
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f));
        store.addMedia(new Book("1984", "Fiction", "George Orwell", 15f));
        String[] tracks = {"Come Together", "Something"};
        store.addMedia(new CompactDisc("Abbey Road", "Rock", "The Beatles", 12.99f, tracks));

        int choice;
        do {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    storeMenu();
                    break;
                case 2:
                    updateStoreMenu();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\nAIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        int choice;
        do {
            store.printStore();
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");
            choice = sc.nextInt();
            sc.nextLine();
            int id = 0;
            Media m = null;
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    m = store.getItem(id);
                    if (m != null) {
                        System.out.println(m);
                        mediaDetailsMenu(id);
                    } else {
                        System.out.println("Item not found!");
                    }
                    break;
                case 2:
                    System.out.print("Enter ID to add: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    m = store.getItem(id);
                    if (m != null) {
                        cart.addMedia(m);
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to play: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    m = store.getItem(id);
                    if (m instanceof Playable) {
                        ((Playable) m).play();
                    } else {
                        System.out.println("Not playable!");
                    }
                    break;
                case 4:
                    System.out.println(cart);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void mediaDetailsMenu(int id) {
        int choice;
        do {
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            choice = sc.nextInt();
            sc.nextLine();
            Media m;
            switch (choice) {
                case 1:
                    m = store.getItem(id);
                    cart.addMedia(m);
                    break;
                case 2:
                    m = store.getItem(id);
                    if (m instanceof Playable) {
                        ((Playable) m).play();
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void cartMenu() {
        int choice;
        do {
            System.out.println(cart);
            System.out.println("\nOptions: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter title keyword: ");
                    String keyword = sc.nextLine();
                    cart.searchTitle(keyword);
                    break;
                case 2:
                    System.out.println("Sort options:");
                    System.out.println("1. By title-cost");
                    System.out.println("2. By cost-title");
                    int subchoice = sc.nextInt();
                    sc.nextLine();
                    if (subchoice == 1) {
                        cart.sortByTitleCost();
                    } else if (subchoice == 2) {
                        cart.sortByCostTitle();
                    }
                    System.out.println(cart);
                    break;
                case 3:
                    System.out.print("Enter ID to remove: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Media m = findInCart(id);
                    if (m != null) {
                        cart.removeMedia(m);
                    } else {
                        System.out.println("Not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to play: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    m = findInCart(id);
                    if (m instanceof Playable) {
                        ((Playable) m).play();
                    } else {
                        System.out.println("Not playable!");
                    }
                    break;
                case 5:
                    System.out.println("Order placed! Total: " + cart.totalCost());
                    cart.clear();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static Media findInCart(int id) {
        for (Media item : cart.getItems()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public static void updateStoreMenu() {
        int choice;
        do {
            System.out.println("\nUpdate Store: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a DVD");
            System.out.println("2. Remove media by ID");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter length: ");
                    int length = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter cost: ");
                    float cost = sc.nextFloat();
                    sc.nextLine();
                    store.addMedia(new DigitalVideoDisc(title, category, director, length, cost));
                    break;
                case 2:
                    System.out.print("Enter ID to remove: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Media m = store.getItem(id);
                    if (m != null) {
                        store.removeMedia(m);
                    } else {
                        System.out.println("Item not found!");
                    }
                    break;
                case 0:
                    break;
                default:
