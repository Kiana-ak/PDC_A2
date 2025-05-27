/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

/**
 *
 * @author 64210
 */
public class HotelSetupMain {
    public static void main(String[] args) {
        HotelRoomManager manager = new HotelRoomManager();
        manager.setupHotelDB();         //create the HotelDB and ROOMS table
        manager.closeConnection();      //close the DB
    }
}
