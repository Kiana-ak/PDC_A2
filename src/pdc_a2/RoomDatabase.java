/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 64210
 */
public class RoomDatabase {
    private final DBManager dbManager;
    private final Connection conn;

    public RoomDatabase() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ROOMS WHERE BOOKED = FALSE");

            while (rs.next()) {
                int roomNumber = rs.getInt("ROOMNUMBER");
                int beds = rs.getInt("BEDS");
                boolean booked = rs.getBoolean("BOOKED");

                Room room = new Room(roomNumber, beds);
                room.setBooked(booked);
                rooms.add(room);
            }

            rs.close();
            statement.close();

        } catch (SQLException ex) {
            System.out.println("Error reading rooms: " + ex.getMessage());
        }

        return rooms;
    }

    public void closeConnection() {
        dbManager.closeConnection();
    }
}
