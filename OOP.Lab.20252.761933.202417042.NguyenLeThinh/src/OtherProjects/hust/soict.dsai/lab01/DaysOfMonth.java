package hust.soict.dsai.lab01;

import java.util.Scanner;

public class DaysOfMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = -1;
        int month = -1;

        while (true) {
            System.out.print("Enter year (e.g., 1999): ");
            String yearInput = sc.nextLine().trim();

            if (yearInput.matches("\\d+")) {
                year = Integer.parseInt(yearInput);
                break;
            } else {
                System.out.println("Invalid year. Please enter a non-negative number with all digits.");
            }
        }

        while (month == -1) {
            System.out.print("Enter month (Full name, Abbreviation, 3 letters, or Number): ");
            String monthInput = sc.nextLine().trim();
            month = getMonthNumber(monthInput);

            if (month == -1) {
                System.out.println("Invalid month. Please try again (e.g., January, Jan., Jan, or 1).");
            }
        }

        int days = calculateDays(month, year);
        System.out.println("The number of days in the chosen month/year is: " + days);

        sc.close();
    }

    private static int getMonthNumber(String input) {
        String s = input.toLowerCase();
        if (s.equals("january") || s.equals("jan.") || s.equals("jan") || s.equals("1")) return 1;
        if (s.equals("february") || s.equals("feb.") || s.equals("feb") || s.equals("2")) return 2;
        if (s.equals("march") || s.equals("mar.") || s.equals("mar") || s.equals("3")) return 3;
        if (s.equals("april") || s.equals("apr.") || s.equals("apr") || s.equals("4")) return 4;
        if (s.equals("may") || s.equals("5")) return 5;
        if (s.equals("june") || s.equals("jun.") || s.equals("jun") || s.equals("6")) return 6;
        if (s.equals("july") || s.equals("jul.") || s.equals("jul") || s.equals("7")) return 7;
        if (s.equals("august") || s.equals("aug.") || s.equals("aug") || s.equals("8")) return 8;
        if (s.equals("september") || s.equals("sept.") || s.equals("sep") || s.equals("9")) return 9;
        if (s.equals("october") || s.equals("oct.") || s.equals("oct") || s.equals("10")) return 10;
        if (s.equals("november") || s.equals("nov.") || s.equals("nov") || s.equals("11")) return 11;
        if (s.equals("december") || s.equals("dec.") || s.equals("dec") || s.equals("12")) return 12;
        return -1; // Không hợp lệ
    }

    private static int calculateDays(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }
}
