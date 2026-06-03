// File path: AimsProject/src/hust/soict/hedspi/aims/screen/AddCompactDiscToStoreScreen.java
package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField artistField;

    public AddCompactDiscToStoreScreen(Store store, StoreManagerScreen parent) {
        super(store, parent, "Add CD to Store");
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleField = new JTextField();
        categoryField = new JTextField();
        costField = new JTextField();
        artistField = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Cost:"));
        panel.add(costField);
        panel.add(new JLabel("Artist:"));
        panel.add(artistField);

        return panel;
    }

    @Override
    protected void addItemToStore() {
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String artist = artistField.getText().trim();
            float cost = Float.parseFloat(costField.getText().trim());

            if (title.isEmpty() || category.isEmpty() || artist.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            int id = store.getNextMediaId();
            CompactDisc cd = new CompactDisc(id, title, category, artist, cost);
            store.addMedia(cd);
            onAddSuccess();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cost must be numeric.", "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
