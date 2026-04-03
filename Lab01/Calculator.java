import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        String strNum1 = JOptionPane.showInputDialog("Enter the first number: ");
        String strNum2 = JOptionPane.showInputDialog("Enter the second number: ");

        double num1 = Double.parseDouble(strNum1); 
        double num2 = Double.parseDouble(strNum2);

        double sum = num1 + num2;
        double diff = num1 - num2;
        double prod = num1 * num2;

        String result = "Sum: " + sum + "\n" +
                        "Difference: " + diff + "\n" +
                        "Product: " + prod + "\n";

        if (num2 != 0) {
            double quot = num1 / num2;
            result += "Quotient: " + quot;
        } else {
            result += "Quotient: Cannot divide by zero!";
        }

        JOptionPane.showMessageDialog(null, result);
        System.exit(0);
    }
}