/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import pdc_a2.model.HotelRoomManager;
import pdc_a2.model.UserDatabase;

/**
 *
 * @author 64210
 */
public class HotelSetupMain {

    public static void main(String[] args) {
        
        
        HotelRoomManager manager = new HotelRoomManager();
        manager.setupHotelDB();      //create the HotelDB and ROOMS table
        manager.closeConnection();      //close the DB
        
        // user
        UserDatabase userDb = new UserDatabase();
        userDb.setupUsersTable();
    
    }
}
