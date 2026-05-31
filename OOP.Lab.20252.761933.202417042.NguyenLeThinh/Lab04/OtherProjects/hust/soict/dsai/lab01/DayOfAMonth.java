package hust.soict.dsai.lab01;

import javax.swing.JOptionPane;

public class DayOfAMonth {
    public static void main (String[] args) {
        String monthStr, yearStr;
        int month, year;
        
        while (true) {
            yearStr = JOptionPane.showInputDialog(null, "Enter the year:");
            if (yearStr == null) System.exit(0);
            
            yearStr = yearStr.trim();
            try {
                year = Integer.parseInt(yearStr);
                if (year >= 0) break;
                else {
                    JOptionPane.showMessageDialog(null, "Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            monthStr = JOptionPane.showInputDialog(null, "Enter the month:");
            if (monthStr == null) System.exit(0);
          
            monthStr = monthStr.trim();
            month = getMonthNumber(monthStr);

            if (month > 0 && month < 13) break;
            else {
                JOptionPane.showMessageDialog(null, "Invalid month!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        
        int days = 0;
        switch (month) { 
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;
            case 4: case 6: case 9: case 11:
                days = 30;
                break;
            case 2:
                days = isLeapYear ? 29 : 28;
                break;
        }
        
        String message = "Month " + month + " of year " + year + " has " + days + " days.";
        JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
        
    public static int getMonthNumber(String monthInput) {
        switch (monthInput) {
            case "January": case "Jan.": case "Jan": case "1": 
                return 1;
            case "February": case "Feb.": case "Feb": case "2": 
                return 2;
            case "March": case "Mar.": case "Mar": case "3": 
                return 3;
            case "April": case "Apr.": case "Apr": case "4": 
                return 4;
            case "May": case "5": 
                return 5;
            case "June": case "Jun": case "6": 
                return 6;
            case "July": case "Jul": case "7": 
                return 7;
            case "August": case "Aug.": case "Aug": case "8": 
                return 8;
            case "September": case "Sept.": case "Sep": case "9": 
                return 9;
            case "October": case "Oct.": case "Oct": case "10": 
                return 10;
            case "November": case "Nov.": case "Nov": case "11": 
                return 11;
            case "December": case "Dec.": case "Dec": case "12": 
                return 12;
            default: 
                return -1;
        }
    }
}