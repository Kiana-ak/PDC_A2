/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author 64210
 */
public class HotelController implements ActionListener {

    private final HotelView view;
    private final HotelModel model;

    public HotelController(HotelView view, HotelModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String buttonText = source.getText().trim();

        if (buttonText.equalsIgnoreCase("View Available Rooms")) {
            model.loadRoomCategories("Guest");
        } else if (buttonText.equals("Receptionist Login")) {
            view.setContentPane(new LoginPanel(view));
            view.revalidate();
        }
    }

}
