// File path: OtherProjects/src/hust/soict/hedspi/lab01/MatrixAddition.java
package hust.soict.hedspi.lab01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixAddition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the number of rows: ");
            int rows = readPositiveInt(scanner);
            System.out.print("Enter the number of columns: ");
            int cols = readPositiveInt(scanner);

            System.out.println("Enter values for matrix A:");
            int[][] matrixA = readMatrix(scanner, rows, cols, "A");

            System.out.println("Enter values for matrix B:");
            int[][] matrixB = readMatrix(scanner, rows, cols, "B");

            int[][] sumMatrix = addMatrices(matrixA, matrixB);
            System.out.println("Matrix A + Matrix B result:");
            printMatrix(sumMatrix);
        } catch (NumberFormatException | InputMismatchException ex) {
            System.out.println("Invalid input. Please enter integer sizes and values.");
        } finally {
            scanner.close();
        }
    }

    private static int readPositiveInt(Scanner scanner) {
        int value = Integer.parseInt(scanner.nextLine().trim());
        if (value <= 0) {
            throw new NumberFormatException("Matrix dimensions must be positive.");
        }
        return value;
    }

    private static int[][] readMatrix(Scanner scanner, int rows, int cols, String name) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter value for " + name + "[" + (i + 1) + "][" + (j + 1) + "]: ");
                matrix[i][j] = Integer.parseInt(scanner.nextLine().trim());
            }
        }
        return matrix;
    }

    private static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] sum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }
        return sum;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%d ", value);
            }
            System.out.println();
        }
    }
}
