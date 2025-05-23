/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;
import javax.swing.*;
import javax.swing.JPanel;

/**
 *
 * @author 64210
 */
public class RoomListPanel extends JPanel{
    
    private JList<String> roomList;
    private JButton bookButton;

    public RoomListPanel(List<Room> rooms, String imageFile, HotelBookingSystem mainFrame) {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        // LEFT: List of room numbers
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Room room : rooms) {
            listModel.addElement("Room " + room.getRoomNumber());
        }
        roomList = new JList<>(listModel);
        roomList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane listScrollPane = new JScrollPane(roomList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Available Rooms"));
        listScrollPane.setPreferredSize(new Dimension(200, 300));

        // RIGHT: Room image
        JLabel imageLabel;
        try {
            Image img = new ImageIcon(getClass().getResource("/resources/" + imageFile)).getImage();
            Image scaledImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImg));
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        // BOTTOM: Book Button
        bookButton = new JButton("Book Selected Room");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        bookButton.setEnabled(false); // enable only when a room is selected

        bookButton.addActionListener(e -> {
            String selected = roomList.getSelectedValue();
            if (selected != null) {
                int roomNumber = Integer.parseInt(selected.split(" ")[1]);
                JOptionPane.showMessageDialog(this,
                        "You selected Room " + roomNumber + "\nNext step: show booking form!",
                        "Booking", JOptionPane.INFORMATION_MESSAGE);
                // go to booking form
            }
        });

        roomList.addListSelectionListener(e -> {
            bookButton.setEnabled(roomList.getSelectedIndex() != -1);
        });

        add(listScrollPane, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);
    }
}
