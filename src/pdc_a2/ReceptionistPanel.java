/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author 64210
 */
public class ReceptionistPanel extends JPanel {

    public ReceptionistPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Receptionist Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JTextArea bookingsArea = new JTextArea();
        bookingsArea.setEditable(false);
        bookingsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(bookingsArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> {
            HotelView view = new HotelView();
            view.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose(); // close current frame
        });
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        displayAllBookings(bookingsArea);
    }

    private void displayAllBookings(JTextArea area) {
        RoomDatabase db = new RoomDatabase();
        List<Room> allRooms = db.getAllRooms();

        StringBuilder sb = new StringBuilder();
        sb.append("Showing all rooms.\n\n");

        for (Room room : allRooms) {
            sb.append("Room ").append(room.getRoomNumber())
                    .append(" | Beds: ").append(room.getBeds())
                    .append(" | Booked: ").append(room.isBooked() ? "Yes" : "No")
                    .append("\n");
        }

        area.setText(sb.toString());
        db.closeConnection();
    }

}
