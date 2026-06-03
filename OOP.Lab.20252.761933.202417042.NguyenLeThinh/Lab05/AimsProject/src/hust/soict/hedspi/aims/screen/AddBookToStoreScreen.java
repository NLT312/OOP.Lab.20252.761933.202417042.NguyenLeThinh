// File path: AimsProject/src/hust/soict/hedspi/aims/screen/AddBookToStoreScreen.java
package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField costField;
    private JTextField authorsField;

    public AddBookToStoreScreen(Store store, StoreManagerScreen parent) {
        super(store, parent, "Add Book to Store");
    }

    @Override
    protected JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        titleField = new JTextField();
        categoryField = new JTextField();
        costField = new JTextField();
        authorsField = new JTextField();

        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Cost:"));
        panel.add(costField);
        panel.add(new JLabel("Authors (comma separated):"));
        panel.add(authorsField);

        return panel;
    }

    @Override
    protected void addItemToStore() {
        try {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String authorsText = authorsField.getText().trim();
            float cost = Float.parseFloat(costField.getText().trim());

            if (title.isEmpty() || category.isEmpty() || authorsText.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            int id = store.getNextMediaId();
            Book book = new Book(id, title, category, cost);
            String[] authors = authorsText.split(",");
            for (String author : authors) {
                if (!author.trim().isEmpty()) {
                    book.addAuthor(author.trim());
                }
            }
            store.addMedia(book);
            onAddSuccess();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Cost must be numeric.", "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
