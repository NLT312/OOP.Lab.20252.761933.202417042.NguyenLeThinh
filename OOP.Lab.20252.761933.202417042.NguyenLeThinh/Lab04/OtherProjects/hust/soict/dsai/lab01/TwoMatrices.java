package hust.soict.dsai.lab01;

public class TwoMatrices {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] B = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        int rows = A.length;       
        int cols = A[0].length;    

        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {           
            for (int j = 0; j < cols; j++) {      
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        
        System.out.println("Result: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(result[i][j] + "\t"); 
            }
            System.out.println(); 
        }
    }
}