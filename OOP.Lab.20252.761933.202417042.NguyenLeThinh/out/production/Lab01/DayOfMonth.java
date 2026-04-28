import java.util.Scanner;

public class DayOfMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = 0;
        int year = 0;
        
        while (true) {
            System.out.print("Enter month (Name, Abbreviation, or Number): ");
            String inputMonth = scanner.next().toLowerCase();
            
            System.out.print("Enter year (e.g. 1999): ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
            } else {
                System.out.println("Invalid year. Please enter a number.");
                scanner.next(); 
                continue;
            }

            if (year < 0) {
                System.out.println("Invalid year. Please try again.");
                continue;
            }

            if (inputMonth.equals("january") || inputMonth.equals("jan.") || inputMonth.equals("jan") || inputMonth.equals("1")) {
                month = 1;
            } else if (inputMonth.equals("february") || inputMonth.equals("feb.") || inputMonth.equals("feb") || inputMonth.equals("2")) {
                month = 2;
            } else if (inputMonth.equals("march") || inputMonth.equals("mar.") || inputMonth.equals("mar") || inputMonth.equals("3")) {
                month = 3;
            } else if (inputMonth.equals("april") || inputMonth.equals("apr.") || inputMonth.equals("apr") || inputMonth.equals("4")) {
                month = 4;
            } else if (inputMonth.equals("may") || inputMonth.equals("5")) {
                month = 5;
            } else if (inputMonth.equals("june") || inputMonth.equals("jun") || inputMonth.equals("6")) {
                month = 6;
            } else if (inputMonth.equals("july") || inputMonth.equals("jul") || inputMonth.equals("7")) {
                month = 7;
            } else if (inputMonth.equals("august") || inputMonth.equals("aug.") || inputMonth.equals("aug") || inputMonth.equals("8")) {
                month = 8;
            } else if (inputMonth.equals("september") || inputMonth.equals("sept.") || inputMonth.equals("sep") || inputMonth.equals("9")) {
                month = 9;
            } else if (inputMonth.equals("october") || inputMonth.equals("oct.") || inputMonth.equals("oct") || inputMonth.equals("10")) {
                month = 10;
            } else if (inputMonth.equals("november") || inputMonth.equals("nov.") || inputMonth.equals("nov") || inputMonth.equals("11")) {
                month = 11;
            } else if (inputMonth.equals("december") || inputMonth.equals("dec.") || inputMonth.equals("dec") || inputMonth.equals("12")) {
                month = 12;
            } else {
                System.out.println("Invalid month. Try again!");
                continue;
            }
            break; 
        }

        int numDays = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            numDays = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            numDays = 30;
        } else if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                numDays = 29;
            } else {
                numDays = 28;
            }
        }

        System.out.println("Number of days: " + numDays);
        scanner.close();
    }
}