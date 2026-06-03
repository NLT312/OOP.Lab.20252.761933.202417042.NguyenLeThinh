// File path: AimsProject/src/hust/soict/hedspi/aims/screen/StoreManagerScreen.java
package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StoreManagerScreen extends JFrame {
    private Store store;
    private Cart cart;
    private JPanel centerPanel;
    private JLabel cartStatusLabel;

    public StoreManagerScreen(Store store, Cart cart) {
        super("AIMS Store Manager");
        this.store = store;
        this.cart = cart;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        setJMenuBar(createMenuBar());
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        refreshStoreDisplay();
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu updateMenu = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem addCDItem = new JMenuItem("Add CD");
        JMenuItem addDVDItem = new JMenuItem("Add DVD");

        addBookItem.addActionListener(e -> new AddBookToStoreScreen(store, this));
        addCDItem.addActionListener(e -> new AddCompactDiscToStoreScreen(store, this));
        addDVDItem.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store, this));

        updateMenu.add(addBookItem);
        updateMenu.add(addCDItem);
        updateMenu.add(addDVDItem);

        JMenu viewMenu = new JMenu("View Store");
        JMenuItem refreshItem = new JMenuItem("Refresh Store");
        refreshItem.addActionListener(e -> refreshStoreDisplay());
        viewMenu.add(refreshItem);

        menuBar.add(updateMenu);
        menuBar.add(viewMenu);
        return menuBar;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("AIMS Store Management");
        headerPanel.add(titleLabel);
        return headerPanel;
    }

    private JScrollPane createCenterPanel() {
        centerPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        centerPanel.setPreferredSize(new Dimension(840, 420));
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        return scrollPane;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cartStatusLabel = new JLabel();
        footer.add(cartStatusLabel);
        return footer;
    }

    public void refreshStoreDisplay() {
        centerPanel.removeAll();
        List<Media> items = store.getItems();
        if (items.isEmpty()) {
            centerPanel.add(new JLabel("There are no media items in the store."));
        } else {
            for (Media media : items) {
                centerPanel.add(new MediaStore(media, cart, this));
            }
        }
        updateCartStatus();
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void updateCartStatus() {
        cartStatusLabel.setText("Cart items: " + cart.getItemsOrdered().size() + " | Total: $" + String.format("%.2f", cart.totalCost()));
    }

    public void showPlayDialog(Media media) {
        String message = "Playing " + media.getClass().getSimpleName() + ": " + media.getTitle();
        javax.swing.JOptionPane.showMessageDialog(this, message, "Play Media", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();
        new StoreManagerScreen(store, cart);
    }
}
