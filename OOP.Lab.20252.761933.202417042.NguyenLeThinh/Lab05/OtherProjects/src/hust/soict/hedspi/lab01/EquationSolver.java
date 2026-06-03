// File path: OtherProjects/src/hust/soict/hedspi/lab01/EquationSolver.java
package hust.soict.hedspi.lab01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Choose the equation type:");
                System.out.println("1. Linear equation ax + b = 0");
                System.out.println("2. Linear system of 2 variables (Cramer)");
                System.out.println("3. Quadratic equation ax^2 + bx + c = 0");
                System.out.println("4. Exit");
                System.out.print("Your choice: ");

                int choice = readInt(scanner);
                switch (choice) {
                    case 1:
                        solveLinearEquation(scanner);
                        break;
                    case 2:
                        solveLinearSystem(scanner);
                        break;
                    case 3:
                        solveQuadraticEquation(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please choose 1-4.");
                }
                System.out.println();
            }
        } catch (NumberFormatException | InputMismatchException ex) {
            System.out.println("Invalid input. Please enter valid numbers.");
        } finally {
            scanner.close();
        }
    }

    private static void solveLinearEquation(Scanner scanner) {
        System.out.println("Solve ax + b = 0");
        System.out.print("Enter a: ");
        double a = readDouble(scanner);
        System.out.print("Enter b: ");
        double b = readDouble(scanner);

        if (Math.abs(a) < 1e-12) {
            if (Math.abs(b) < 1e-12) {
                System.out.println("The equation has infinitely many solutions.");
            } else {
                System.out.println("The equation has no solution.");
            }
        } else {
            double x = -b / a;
            System.out.println("Solution: x = " + x);
        }
    }

    private static void solveLinearSystem(Scanner scanner) {
        System.out.println("Solve system using Cramer's rule:");
        System.out.print("Enter a11: ");
        double a11 = readDouble(scanner);
        System.out.print("Enter a12: ");
        double a12 = readDouble(scanner);
        System.out.print("Enter b1: ");
        double b1 = readDouble(scanner);

        System.out.print("Enter a21: ");
        double a21 = readDouble(scanner);
        System.out.print("Enter a22: ");
        double a22 = readDouble(scanner);
        System.out.print("Enter b2: ");
        double b2 = readDouble(scanner);

        double determinant = a11 * a22 - a12 * a21;
        if (Math.abs(determinant) > 1e-12) {
            double x1 = (b1 * a22 - a12 * b2) / determinant;
            double x2 = (a11 * b2 - b1 * a21) / determinant;
            System.out.println("Unique solution:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } else {
            double determinant1 = b1 * a22 - a12 * b2;
            double determinant2 = a11 * b2 - b1 * a21;
            if (Math.abs(determinant1) < 1e-12 && Math.abs(determinant2) < 1e-12) {
                System.out.println("The system has infinitely many solutions.");
            } else {
                System.out.println("The system has no solution.");
            }
        }
    }

    private static void solveQuadraticEquation(Scanner scanner) {
        System.out.println("Solve ax^2 + bx + c = 0");
        System.out.print("Enter a: ");
        double a = readDouble(scanner);
        System.out.print("Enter b: ");
        double b = readDouble(scanner);
        System.out.print("Enter c: ");
        double c = readDouble(scanner);

        if (Math.abs(a) < 1e-12) {
            System.out.println("Coefficient a is zero, reduce to a linear equation.");
            if (Math.abs(b) < 1e-12) {
                if (Math.abs(c) < 1e-12) {
                    System.out.println("The equation has infinitely many solutions.");
                } else {
                    System.out.println("The equation has no solution.");
                }
            } else {
                double x = -c / b;
                System.out.println("Solution: x = " + x);
            }
            return;
        }

        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double sqrtDelta = Math.sqrt(discriminant);
            double x1 = (-b + sqrtDelta) / (2 * a);
            double x2 = (-b - sqrtDelta) / (2 * a);
            System.out.println("The equation has two real solutions:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        } else if (Math.abs(discriminant) < 1e-12) {
            double x = -b / (2 * a);
            System.out.println("The equation has one real double root:");
            System.out.println("x = " + x);
        } else {
            System.out.println("The equation has no real solutions.");
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
