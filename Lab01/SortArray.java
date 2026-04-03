import java.util.Arrays;
import java.util.Scanner;

public class SortArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of elements in array: ");
        int size = sc.nextInt();
        double[] my_array = new double[size];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < size; i++) {
            my_array[i] = sc.nextDouble();
        }
        
        Arrays.sort(my_array);
        
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum = sum + my_array[i];
        }
        
        double average = sum / size;
        
        System.out.println("Sorted array: " + Arrays.toString(my_array));
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average value: " + average);
        
        sc.close();
    }
}