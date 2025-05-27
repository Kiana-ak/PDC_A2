/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 64210
 */
public class SelectRoom extends JPanel {

    public SelectRoom(List<Room> rooms) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        for (Room room : rooms) {
            if (!room.isBooked()) {
                add(createRoomCard(room));
            }
        }
    }

    private JPanel createRoomCard(Room room) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setMaximumSize(new Dimension(600, 120));

        // Room image based on bed count
        String imageName = "/resources/room" + room.getBeds() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(imageName));
        JLabel imageLabel = new JLabel(icon);
        panel.add(imageLabel, BorderLayout.WEST);

        // Room info
        JLabel info = new JLabel("Room " + room.getRoomNumber()
                + " | Beds: " + room.getBeds()
                + " | Price: $" + room.getPrice());
        panel.add(info, BorderLayout.CENTER);

        // Book button
        JButton bookButton = new JButton("Book");
        panel.add(bookButton, BorderLayout.EAST);

        return panel;
    }
}
