/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author 64210
 */
public class HotelView extends JFrame {
    
    private JButton viewRoomsButton;
    private JLabel backgroundLabel;
    
    public HotelView() {
        setTitle("Hotel Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // background image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/resources/hotel_bg.png"));
        backgroundLabel = new JLabel(bgImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to Our Hotel", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        backgroundLabel.add(welcomeLabel, BorderLayout.NORTH);

        // View Rooms Button
        viewRoomsButton = new JButton("View Available Rooms");
        viewRoomsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewRoomsButton.setFocusPainted(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(viewRoomsButton);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);
    }
        public void addActionListener(java.awt.event.ActionListener listener) {
        viewRoomsButton.addActionListener(listener);
    }

}
