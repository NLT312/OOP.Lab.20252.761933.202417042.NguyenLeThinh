package hust.soict.dsai.lab01;

import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        int[] a = {1789, 2035, 1899, 1456, 2013};

        System.out.println("Array: " + Arrays.toString(a));

        Arrays.sort(a);
        System.out.println("Sort: " + Arrays.toString(a));

        int sum = 0;
        for (int i = 0; i < a.length; i++) sum += a[i]; 
       
        double average = (double) sum / a.length;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }
}
