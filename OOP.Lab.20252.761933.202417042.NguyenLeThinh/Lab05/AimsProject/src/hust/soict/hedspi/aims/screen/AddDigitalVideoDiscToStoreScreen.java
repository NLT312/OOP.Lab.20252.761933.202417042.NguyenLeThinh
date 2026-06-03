// File path: AimsProject/src/hust/soict/hedspi/aims/screen/AddDigitalVideoDiscToStoreScreen.java
package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField directorField;
    private JTextField lengthField;

    public AddDigitalVideoDiscToStoreScreen(Store store, StoreManagerScreen parent) {
        super(store, parent, "Add DVD to Store");
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleField = new JTextField();
        categoryField = new JTextField();
        costField = new JTextField();
        directorField = new JTextField();
        lengthField = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Cost:"));
        panel.add(costField);
        panel.add(new JLabel("Director:"));
        panel.add(directorField);
        panel.add(new JLabel("Length (minutes):"));
        panel.add(lengthField);

        return panel;
    }

    @Override
    protected void addItemToStore() {
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String director = directorField.getText().trim();
            float cost = Float.parseFloat(costField.getText().trim());
            int length = Integer.parseInt(lengthField.getText().trim());

            if (title.isEmpty() || category.isEmpty() || director.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            int id = store.getNextMediaId();
            DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, director, length, cost);
            store.addMedia(dvd);
            onAddSuccess();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cost and length must be numeric.", "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
