// File path: OtherProjects/src/hust/soict/hedspi/lab01/ChoosingOption.java
package hust.soict.hedspi.lab01;

import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Do you want to change to the first class ticket?",
                "Ticket Upgrade",
                JOptionPane.YES_NO_CANCEL_OPTION);

        String message;
        switch (option) {
            case JOptionPane.YES_OPTION:
                message = "You chose Yes.";
                break;
            case JOptionPane.NO_OPTION:
                message = "You chose No.";
                break;
            case JOptionPane.CANCEL_OPTION:
                message = "You chose Cancel.";
                break;
            default:
                message = "No selection was made.";
        }

        JOptionPane.showMessageDialog(null, message, "Selected Option", JOptionPane.INFORMATION_MESSAGE);
    }
}
