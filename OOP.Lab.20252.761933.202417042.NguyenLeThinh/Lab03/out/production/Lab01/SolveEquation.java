import javax.swing.JOptionPane;

public class SolveEquation {
    public static void main(String[] args) {
        String choice = JOptionPane.showInputDialog("Choose: 1. Linear   2. System   3. Quadratic");
        int ch = Integer.parseInt(choice);

        if (ch == 1) solveLinear();
        else if (ch == 2) solveSystem();
        else if (ch == 3) solveQuadratic();
    }

    public static void solveLinear () {
        String strNum1, strNum2;
        strNum1 = JOptionPane.showInputDialog("a: ");
        strNum2 = JOptionPane.showInputDialog("b: ");  
        double a = Double.parseDouble(strNum1);
        double b = Double.parseDouble(strNum2);
        String result = "";

        if (a == 0) {
            if (b == 0) result = "Infinite solutions";
            else result = "No solution";
        }
        else {
            double x = -b / a;
            result = "x = " + x;
        }

        JOptionPane.showMessageDialog(null, result);
    }

    public static void solveSystem () {
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("a12:"));
        double b1  = Double.parseDouble(JOptionPane.showInputDialog("b1:"));

        double a21 = Double.parseDouble(JOptionPane.showInputDialog("a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("a22:"));
        double b2  = Double.parseDouble(JOptionPane.showInputDialog("b2:"));

        double D = a11*a22 - a21*a12;
        double D1 = b1*a22 - b2*a12;
        double D2 = a11*b2 - a21*b1;

        String result = "";

        if (D != 0) {
            double x1 = D1 / D;
            double x2 = D2 / D;
            result = "x1 = " + x1 + "\nx2 = " + x2;
        } 
        else {
            if (D1 == 0 && D2 == 0)
                result = "Infinite solutions";
            else
                result = "No solution";
        }

        JOptionPane.showMessageDialog(null, result);
    }

    public static void solveQuadratic () {
        double a = Double.parseDouble(JOptionPane.showInputDialog("a: "));
        double b = Double.parseDouble(JOptionPane.showInputDialog("b: "));
        double c = Double.parseDouble(JOptionPane.showInputDialog("c: "));
        
        String result = "";

        if (a == 0) {
            if (b == 0) {
                if (c == 0) result = "Infinite solutions";
                else result = "No solution";
            }
            else {
                double x = -c / b;
                result = "x = " + x;
            }
        }
        else {
            double delta = b * b - 4 * a * c;

            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                result = "x1 = " + x1 + "\nx2 = " + x2;
            }
            else if (delta == 0) {
                double x = -b / (2 * a);
                result = "x = " + x;
            }
            else {
                result = "No real solution";
            }
        }

        JOptionPane.showMessageDialog(null, result);
    } 
}