// File path: GUIProject/src/hust/soict/hedspi/swing/NumberGrid.java
package hust.soict.hedspi.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGrid extends JFrame implements ActionListener {
    private JTextField displayField;

    public NumberGrid() {
        super("Number Grid");
        setLayout(new BorderLayout(10, 10));

        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        String[] buttons = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "DEL", "CLR"};
        for (String label : buttons) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        String currentValue = displayField.getText();
        switch (command) {
            case "DEL":
                if (!currentValue.isEmpty()) {
                    displayField.setText(currentValue.substring(0, currentValue.length() - 1));
                }
                break;
            case "CLR":
                displayField.setText("");
                break;
            default:
                displayField.setText(currentValue + command);
                break;
        }
    }

    public static void main(String[] args) {
        new NumberGrid();
    }
}
