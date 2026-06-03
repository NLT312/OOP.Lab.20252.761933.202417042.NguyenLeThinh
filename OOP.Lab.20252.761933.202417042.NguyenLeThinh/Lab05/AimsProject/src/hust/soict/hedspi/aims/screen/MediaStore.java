// File path: AimsProject/src/hust/soict/hedspi/aims/screen/MediaStore.java
package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import hust.soict.hedspi.aims.exception.LimitExceededException;
import hust.soict.hedspi.aims.exception.PlayerException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MediaStore extends JPanel {
    public MediaStore(Media media, Cart cart, StoreManagerScreen parent) {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(400, 150));

        JLabel titleLabel = new JLabel("Title: " + media.getTitle());
        JLabel categoryLabel = new JLabel("Category: " + media.getCategory());
        JLabel costLabel = new JLabel(String.format("Cost: $%.2f", media.getCost()));

        JPanel infoPanel = new JPanel(new BorderLayout(4, 4));
        infoPanel.add(titleLabel, BorderLayout.NORTH);
        infoPanel.add(categoryLabel, BorderLayout.CENTER);
        infoPanel.add(costLabel, BorderLayout.SOUTH);

        add(infoPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add to cart");
        addButton.addActionListener(e -> {
            try {
                cart.addMedia(media);
                parent.updateCartStatus();
            } catch (LimitExceededException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Cart Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton playButton = new JButton("Play");
        if (media instanceof Playable) {
            playButton.addActionListener(e -> {
                try {
                    parent.showPlayDialog(media);
                    ((Playable) media).play();
                } catch (PlayerException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Player Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else {
            playButton.setEnabled(false);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        buttonPanel.add(addButton);
        buttonPanel.add(playButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
