// File path: OtherProjects/src/hust/soict/hedspi/lab01/StarTriangle.java
package hust.soict.hedspi.lab01;

import java.util.Scanner;

public class StarTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the triangle height (positive integer): ");
            int height = readPositiveInt(scanner);
            drawTriangle(height);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a positive integer for the height.");
        } finally {
            scanner.close();
        }
    }

    private static int readPositiveInt(Scanner scanner) {
        String input = scanner.nextLine().trim();
        int value = Integer.parseInt(input);
        if (value <= 0) {
            throw new NumberFormatException("Height must be positive.");
        }
        return value;
    }

    private static void drawTriangle(int height) {
        for (int row = 1; row <= height; row++) {
            for (int spaces = 0; spaces < height - row; spaces++) {
                System.out.print(' ');
            }
            for (int stars = 0; stars < 2 * row - 1; stars++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
