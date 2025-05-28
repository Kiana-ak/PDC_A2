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
public class MyBookingsPanel extends JPanel {
    
    private String guestName;
    private HotelView view;
    private RoomDatabase roomDatabase;

    public MyBookingsPanel(String guestName, HotelView view) {
        this.guestName = guestName;
        this.view = view;
        this.roomDatabase = new RoomDatabase();

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("My Bookings", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        add(createBookingList(), BorderLayout.CENTER);
        add(createBackButton(), BorderLayout.SOUTH);
    }

    private JScrollPane createBookingList() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        List<Room> bookings = roomDatabase.getBookingsForGuest(guestName);
        if (bookings.isEmpty()) {
            JLabel noBookingLabel = new JLabel("You have no current bookings.");
            noBookingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            listPanel.add(noBookingLabel);
        } else {
            for (Room room : bookings) {
                JPanel row = new JPanel(new BorderLayout());
                row.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                row.setBackground(Color.WHITE);

                JLabel roomInfo = new JLabel("Room " + room.getRoomNumber() +
                        " | Beds: " + room.getBeds() + " | Price: $" + room.getPrice());
                roomInfo.setFont(new Font("SansSerif", Font.PLAIN, 14));

                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(e -> {
                    roomDatabase.cancelBooking(room.getRoomNumber());
                    JOptionPane.showMessageDialog(this,
                            "Booking for Room " + room.getRoomNumber() + " canceled.");
                    refreshPanel();
                });

                row.add(roomInfo, BorderLayout.CENTER);
                row.add(cancelButton, BorderLayout.EAST);
                listPanel.add(row);
            }
        }

        return new JScrollPane(listPanel);
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            HotelModel model = new HotelModel(view);
            model.loadRoomCategories(guestName);
        });
        return backButton;
    }

    private void refreshPanel() {
        view.setContentPane(new MyBookingsPanel(guestName, view));
        view.revalidate();
    }
}
