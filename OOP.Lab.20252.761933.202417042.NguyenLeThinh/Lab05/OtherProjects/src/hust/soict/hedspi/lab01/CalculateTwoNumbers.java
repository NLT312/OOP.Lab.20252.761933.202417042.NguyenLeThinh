// File path: OtherProjects/src/hust/soict/hedspi/lab01/CalculateTwoNumbers.java
package hust.soict.hedspi.lab01;

import java.util.Scanner;

public class CalculateTwoNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the first number: ");
            double firstNumber = readDouble(scanner);

            System.out.print("Enter the second number: ");
            double secondNumber = readDouble(scanner);

            double sum = firstNumber + secondNumber;
            double difference = firstNumber - secondNumber;
            double product = firstNumber * secondNumber;

            System.out.println("Results:");
            System.out.println("Sum: " + sum);
            System.out.println("Difference: " + difference);
            System.out.println("Product: " + product);

            if (Math.abs(secondNumber) < 1e-12) {
                System.out.println("Quotient: Cannot divide by zero.");
            } else {
                double quotient = firstNumber / secondNumber;
                System.out.println("Quotient: " + quotient);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter valid decimal numbers.");
        } finally {
            scanner.close();
        }
    }

    private static double readDouble(Scanner scanner) {
        String input = scanner.nextLine().trim();
        return Double.parseDouble(input);
    }
}
