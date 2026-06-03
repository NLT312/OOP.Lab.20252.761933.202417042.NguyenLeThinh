// File path: AimsProject/src/hust/soict/hedspi/aims/screen/AddItemToStoreScreen.java
package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.store.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected StoreManagerScreen parent;
    private JButton addButton;
    private JButton cancelButton;

    public AddItemToStoreScreen(Store store, StoreManagerScreen parent, String title) {
        super(title);
        this.store = store;
        this.parent = parent;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        setJMenuBar(createMenuBar());
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu updateMenu = new JMenu("Update Store");
        JMenuItem backItem = new JMenuItem("Back to Store");
        backItem.addActionListener(e -> dispose());
        updateMenu.add(backItem);

        JMenu viewMenu = new JMenu("View Store");
        JMenuItem refreshItem = new JMenuItem("Refresh Store");
        refreshItem.addActionListener(e -> parent.refreshStoreDisplay());
        viewMenu.add(refreshItem);

        menuBar.add(updateMenu);
        menuBar.add(viewMenu);
        return menuBar;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToStore();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        return buttonPanel;
    }

    protected void onAddSuccess() {
        parent.refreshStoreDisplay();
        dispose();
    }

    protected abstract JPanel createFormPanel();

    protected abstract void addItemToStore();
}
