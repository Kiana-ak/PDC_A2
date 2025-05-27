/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author 64210
 */
public class HotelView extends JFrame implements HotelListener {

 
    public HotelView() {
        setTitle("Hotel Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    // Called by the model when room info is ready
    @Override
    public void hotelUpdate(HotelData data) {
        JOptionPane.showMessageDialog(this,
                "You selected: " + data.beds + " bed(s), Room #" + data.roomNumber);
    }
}
