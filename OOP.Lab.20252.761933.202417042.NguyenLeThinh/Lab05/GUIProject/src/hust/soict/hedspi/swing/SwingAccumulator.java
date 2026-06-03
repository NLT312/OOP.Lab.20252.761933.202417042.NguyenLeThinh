// File path: GUIProject/src/hust/soict/hedspi/swing/SwingAccumulator.java
package hust.soict.hedspi.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingAccumulator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JLabel sumLabel;
    private int accumulatedSum = 0;

    public SwingAccumulator() {
        super("Swing Accumulator");
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField = new JTextField(15);
        inputField.addActionListener(this);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        inputPanel.add(new JLabel("Enter an integer:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);

        sumLabel = new JLabel("Sum: 0");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(sumLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = inputField.getText().trim();
            int value = Integer.parseInt(text);
            accumulatedSum += value;
            sumLabel.setText("Sum: " + accumulatedSum);
            inputField.setText("");
        } catch (NumberFormatException ex) {
            sumLabel.setText("Invalid integer. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new SwingAccumulator();
    }
}
