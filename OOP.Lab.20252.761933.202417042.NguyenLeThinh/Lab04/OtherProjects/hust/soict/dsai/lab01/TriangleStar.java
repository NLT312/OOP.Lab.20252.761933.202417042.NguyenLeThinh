package hust.soict.dsai.lab01;

import java.util.Scanner;

public class TriangleStar {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Input n: ");
		String nStr = keyboard.nextLine();
		int n = Integer.parseInt(nStr);
		
		for (int i = 1; i <= n; i++) {
		    for (int j = i; j < n; j++) System.out.print(" ");
		    for (int k = 1; k <= (2 * i - 1); k++) System.out.print("*");
		    System.out.println();
		}
	}
}
