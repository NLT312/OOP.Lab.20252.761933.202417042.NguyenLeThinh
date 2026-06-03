// File path: OtherProjects/src/hust/soict/hedspi/lab01/InputFromKeyboard.java
package hust.soict.hedspi.lab01;

import java.util.Scanner;

public class InputFromKeyboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter your age: ");
            int age = readInt(scanner);

            System.out.print("Enter your height in meters (e.g. 1.75): ");
            double height = readDouble(scanner);

            System.out.println();
            System.out.println("User Information:");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Height: " + height + " meters");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid integer for age and a valid decimal for height.");
        } finally {
            scanner.close();
        }
    }

    private static int readInt(Scanner scanner) {
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);
    }

    private static double readDouble(Scanner scanner) {
        String input = scanner.nextLine().trim();
        return Double.parseDouble(input);
    }
}
