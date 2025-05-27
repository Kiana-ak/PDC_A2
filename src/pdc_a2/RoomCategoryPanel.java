/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author 64210
 */
public class RoomCategoryPanel extends JPanel {
    
    public RoomCategoryPanel(HotelModel model) {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    JLabel message = new JLabel("Please select a room type to view available options", SwingConstants.CENTER);
    message.setFont(new Font("SansSerif", Font.BOLD, 16));
    message.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
    add(message, BorderLayout.NORTH);

    JPanel cardRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
    cardRow.setBackground(Color.WHITE);

    cardRow.add(createBedOption("room1.png", "1-Bed Room", 1, model));
    cardRow.add(createBedOption("room2.png", "2-Bed Room", 2, model));
    cardRow.add(createBedOption("room3.png", "3-Bed Room", 3, model));

    add(cardRow, BorderLayout.CENTER);
}


    private JPanel createBedOption(String imageFile, String label, int bedCount, HotelModel model) {
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());
    card.setBackground(Color.LIGHT_GRAY);
    card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    // Load and scale image
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

    JLabel textLabel = new JLabel(label, SwingConstants.CENTER);
    textLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
    card.add(textLabel, BorderLayout.SOUTH);

    // Handle click
    card.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            model.loadAvailableRooms(bedCount);
        }
    });

    return card;
    }
}
