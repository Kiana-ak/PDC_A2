/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        model.loadRoomCategories();
    }
}
