// File path: GUIProject/src/hust/soict/hedspi/swing/AWTAccumulator.java
package hust.soict.hedspi.swing;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTAccumulator extends Frame implements ActionListener {
    private TextField inputField;
    private Label resultLabel;
    private int accumulatedSum = 0;

    public AWTAccumulator() {
        super("AWT Accumulator");
        setLayout(new BorderLayout(10, 10));

        Panel inputPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        Label promptLabel = new Label("Enter an integer:");
        inputField = new TextField(15);
        inputField.addActionListener(this);

        inputPanel.add(promptLabel);
        inputPanel.add(inputField);

        resultLabel = new Label("Sum: 0");

        Button resetButton = new Button("Reset");
        resetButton.addActionListener(e -> {
            accumulatedSum = 0;
            resultLabel.setText("Sum: 0");
            inputField.setText("");
        });

        Panel bottomPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(resultLabel);
        bottomPanel.add(resetButton);

        add(inputPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(350, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = inputField.getText().trim();
            int value = Integer.parseInt(text);
            accumulatedSum += value;
            resultLabel.setText("Sum: " + accumulatedSum);
            inputField.setText("");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid integer. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new AWTAccumulator();
    }
}
