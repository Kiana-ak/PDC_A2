/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import pdc_a2.controller.HotelController;
import pdc_a2.controller.HotelModel;

/**
 *
 * @author 64210 shows room type options: 1-bed, 2-bed, 3-bed Each option is
 * shown as a card with an image and label
 */
public class RoomCategoryPanel extends JPanel {

    public RoomCategoryPanel(HotelModel model, String guestName) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Title at the top
        JLabel message = new JLabel("Please select a room type to view available options", SwingConstants.CENTER);
        message.setFont(new Font("SansSerif", Font.BOLD, 16));
        message.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(message, BorderLayout.NORTH);

        // Room type cards
        JPanel cardRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        cardRow.setBackground(Color.WHITE);

        cardRow.add(createBedOption("room1.png", "1-Bed Room", 1, model, guestName));
        cardRow.add(createBedOption("room2.png", "2-Bed Room", 2, model, guestName));
        cardRow.add(createBedOption("room3.png", "3-Bed Room", 3, model, guestName));

        add(cardRow, BorderLayout.CENTER);

        JButton myBookingsButton = new JButton("My Bookings");
        myBookingsButton.addActionListener(e -> {
            String name = guestName;

            if (name == null || name.trim().isEmpty()) {
                name = JOptionPane.showInputDialog(this, "Enter your name to view your bookings:");
            }

            if (name != null && !name.trim().isEmpty()) {
                model.getView().setContentPane(new MyBookingsPanel(name.trim(), model.getView()));
                model.getView().revalidate();
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(myBookingsButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // back button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 14));

        backButton.addActionListener(e -> {
            HotelView newView = new HotelView();
            HotelModel newModel = new HotelModel(newView);
            HotelController newController = new HotelController(newView, model);
            newView.addActionListener(newController);
            newView.setVisible(true);

            // Close the current frame
            SwingUtilities.getWindowAncestor(this).dispose();
        });

        bottomPanel.add(backButton);

    }

    // create clickable cards
    private JPanel createBedOption(String imageFile, String label, int bedCount, HotelModel model, String guestName) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.LIGHT_GRAY);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Load image
        ImageIcon icon;
        try {
            Image img = new ImageIcon(getClass().getResource("/resources/" + imageFile)).getImage();
            Image scaledImg = img.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImg);
        } catch (Exception e) {
            icon = new ImageIcon();
            System.err.println("Image not found: " + imageFile);
        }

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);

        // Label under image
        JLabel textLabel = new JLabel(label, SwingConstants.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        card.add(textLabel, BorderLayout.SOUTH);

        // Handle click and show available rooms
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                model.loadAvailableRooms(bedCount, guestName);
            }
        });

        return card;
    }
}
