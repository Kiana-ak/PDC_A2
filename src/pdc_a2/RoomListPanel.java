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
 * @author 64210 Displays list of available rooms when user selects a room,
 * prompts for their name to confirm booking
 */
public class RoomListPanel extends JPanel {

    private JList<String> roomList;
    private JButton bookButton;

    public RoomListPanel(List<Room> rooms, int bedCount, HotelView view) {

        //Setting layout
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        // List of room numbers in the left
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Room room : rooms) {
            listModel.addElement("Room " + room.getRoomNumber());
        }
        roomList = new JList<>(listModel);
        roomList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane listScrollPane = new JScrollPane(roomList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Available Rooms"));
        listScrollPane.setPreferredSize(new Dimension(200, 300));

        // Room image at the right
        JLabel imageLabel;
        try {
            Image img = new ImageIcon(getClass().getResource("/resources/room" + bedCount + ".png")).getImage();
            Image scaledImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImg));
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        // Book Button at bottom
        bookButton = new JButton("Book Selected Room");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        bookButton.setEnabled(false); // enable only when a room is selected

        // Enable book button if a room is selected
        roomList.addListSelectionListener(e -> {
            bookButton.setEnabled(roomList.getSelectedIndex() != -1);
        });

        // ask for guest name and confirm booking
        bookButton.addActionListener(e -> {
            String selected = roomList.getSelectedValue();
            if (selected != null) {
                int roomNumber = Integer.parseInt(selected.split(" ")[1]);

                String guestName = JOptionPane.showInputDialog(
                        this,
                        "Enter guest name to confirm booking for Room " + roomNumber + ":",
                        "Booking Confirmation",
                        JOptionPane.PLAIN_MESSAGE
                );

                // check if valid name entered
                if (guestName != null && !guestName.trim().isEmpty()) {
                    //update database
                    RoomDatabase roomDatabase = new RoomDatabase();
                    roomDatabase.bookRoom(roomNumber);

                    // confirm message
                    JOptionPane.showMessageDialog(this,
                        "Booking confirmed for " + guestName + " in Room " + roomNumber,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Booking cancelled or no name entered.",
                            "Booking Cancelled",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        // Add all components to panel
        add(listScrollPane, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);
    }
}
