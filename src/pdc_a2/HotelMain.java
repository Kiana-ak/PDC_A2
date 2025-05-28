/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

/**
 *
 * @author 64210
 */
public class HotelMain {

    public static void main(String[] args) {
        HotelView view = new HotelView();
        HotelModel model = new HotelModel(view);
        HotelController controller = new HotelController(view, model);

        view.addActionListener(controller);
        view.setVisible(true);
    }
}
