package hust.soict.dsai.lab01;
import javax.swing.JOptionPane;

public class Calculator {
    public static void main (String[] args) {
        String strNum1, strNum2;
        strNum1 = JOptionPane.showInputDialog("Please input the first number: ");
        strNum2 = JOptionPane.showInputDialog("Please input the second number: ");
        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        double sum = num1 + num2;
        double diff = num1 - num2;
        double product = num1 * num2;

        String result = "";
        result += "Sum = " + sum + "\n";
        result += "Difference = " + diff + "\n";
        result += "Product = " + product + "\n";

        if (num2 != 0) {
            double quotient = num1 / num2;
            result += "Quotient = " + quotient + "\n";
        }
        else {
            result += "Cannot divide by 0\n";
        }

        JOptionPane.showMessageDialog(null, result);
        System.exit(0);
    }   
}
