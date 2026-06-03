// File path: OtherProjects/src/hust/soict/hedspi/lab01/DaysInMonth.java
package hust.soict.hedspi.lab01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DaysInMonth {
    private static final Map<String, Integer> MONTH_MAP = createMonthMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter month (1-12, full name, or 3-letter abbreviation): ");
                String monthInput = scanner.nextLine().trim();
                Integer month = parseMonth(monthInput);
                if (month == null) {
                    System.out.println("Invalid month. Please try again.");
                    continue;
                }
                System.out.print("Enter year: ");
                int year = Integer.parseInt(scanner.nextLine().trim());
                if (year <= 0) {
                    System.out.println("Invalid year. Year must be a positive integer.");
                    continue;
                }
                int days = getDaysInMonth(month, year);
                System.out.println("Month " + month + " of year " + year + " has " + days + " days.");
                break;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a valid month and a valid numeric year.");
        } finally {
            scanner.close();
        }
    }

    private static Map<String, Integer> createMonthMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("10", 10);
        map.put("11", 11);
        map.put("12", 12);
        map.put("january", 1);
        map.put("jan", 1);
        map.put("jan.", 1);
        map.put("february", 2);
        map.put("feb", 2);
        map.put("feb.", 2);
        map.put("march", 3);
        map.put("mar", 3);
        map.put("mar.", 3);
        map.put("april", 4);
        map.put("apr", 4);
        map.put("apr.", 4);
        map.put("may", 5);
        map.put("june", 6);
        map.put("jun", 6);
        map.put("jun.", 6);
        map.put("july", 7);
        map.put("jul", 7);
        map.put("jul.", 7);
        map.put("august", 8);
        map.put("aug", 8);
        map.put("aug.", 8);
        map.put("september", 9);
        map.put("sep", 9);
        map.put("sep.", 9);
        map.put("sept", 9);
        map.put("sept.", 9);
        map.put("october", 10);
        map.put("oct", 10);
        map.put("oct.", 10);
        map.put("november", 11);
        map.put("nov", 11);
        map.put("nov.", 11);
        map.put("december", 12);
        map.put("dec", 12);
        map.put("dec.", 12);
        return map;
    }

    private static Integer parseMonth(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        String normalized = input.toLowerCase().trim();
        return MONTH_MAP.get(normalized);
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
}
