/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author 64210
 */
public class HotelView extends JFrame implements HotelListener {

    private JButton viewRoomsButton;
    private JLabel backgroundLabel;
    private JLabel welcomeLabel;
    private JPanel buttonPanel;

    public HotelView() {
        setTitle("Hotel Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load background image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/resources/hotel_bg.png"));
        backgroundLabel = new JLabel(bgImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Welcome label
        welcomeLabel = new JLabel("Welcome to Our Hotel", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        backgroundLabel.add(welcomeLabel, BorderLayout.NORTH);

        // View rooms button
        viewRoomsButton = new JButton("View Available Rooms");
        viewRoomsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewRoomsButton.setFocusPainted(false);

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(viewRoomsButton);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Let controller register a listener
    public void addActionListener(ActionListener listener) {
        viewRoomsButton.addActionListener(listener);
    }

    // Called by the model when room info is ready
    @Override
    public void hotelUpdate(HotelData data) {
        JOptionPane.showMessageDialog(this,
                "You selected: " + data.beds + " bed(s), Room #" + data.roomNumber);
    }
}
