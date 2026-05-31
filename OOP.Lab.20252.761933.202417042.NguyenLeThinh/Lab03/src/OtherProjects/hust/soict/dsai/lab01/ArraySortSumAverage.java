package hust.soict.dsai.lab01;

import java.util.Arrays;
import java.util.Scanner;

public class ArraySortSumAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] myArray = new int[n];

        for (int i = 0; i < n; i++) {
            myArray[i] = sc.nextInt();
        }

        Arrays.sort(myArray);

        long sum = 0;
        for (int value : myArray) {
            sum += value;
        }

        double average = (double) sum / myArray.length;

        System.out.println("Sorted array: " + Arrays.toString(myArray));
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);

        sc.close();
    }
}
