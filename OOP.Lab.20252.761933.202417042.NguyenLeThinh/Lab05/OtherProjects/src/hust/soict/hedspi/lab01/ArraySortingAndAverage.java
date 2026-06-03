// File path: OtherProjects/src/hust/soict/hedspi/lab01/ArraySortingAndAverage.java
package hust.soict.hedspi.lab01;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArraySortingAndAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the number of elements in the array:");
            int n = readPositiveInt(scanner);
            int[] numbers = new int[n];

            System.out.println("Enter " + n + " integer values:");
            for (int i = 0; i < n; i++) {
                System.out.print("Element " + (i + 1) + ": ");
                numbers[i] = readInt(scanner);
            }

            Arrays.sort(numbers);
            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }
            double average = n == 0 ? 0 : (double) sum / n;

            System.out.println("Sorted array (ascending): " + Arrays.toString(numbers));
            System.out.println("Sum: " + sum);
            System.out.println("Average: " + average);
        } catch (NumberFormatException | InputMismatchException ex) {
            System.out.println("Invalid input. Please enter valid integer values.");
        } finally {
            scanner.close();
        }
    }

    private static int readInt(Scanner scanner) {
        String input = scanner.nextLine().trim();
        return Integer.parseInt(input);
    }

    private static int readPositiveInt(Scanner scanner) {
        int value = readInt(scanner);
        if (value <= 0) {
            throw new NumberFormatException("The number of elements must be greater than zero.");
        }
        return value;
    }
}
