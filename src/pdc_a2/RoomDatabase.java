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

    // mark selected room as booked in database
    public List<Room> getBookingsForGuest(String guestName) {
        guestName = guestName.trim().toLowerCase();
        List<Room> rooms = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM ROOMS WHERE BOOKED = TRUE AND LOWER(GUESTNAME) = '" + guestName + "'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int roomNumber = rs.getInt("ROOMNUMBER");
                int beds = rs.getInt("BEDS");
                boolean booked = rs.getBoolean("BOOKED");
                Room room = new Room(roomNumber, beds);
                room.setBooked(booked);
                room.setGuestName(guestName);
                rooms.add(room);
                System.out.println("Found booking: " + roomNumber + " for " + guestName);

            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving bookings: " + ex.getMessage());
        }
        return rooms;
    }

    //cancel booking
    public void cancelBooking(int roomNumber) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE ROOMS SET BOOKED = FALSE, GUESTNAME = NULL WHERE ROOMNUMBER = " + roomNumber;
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("❌ Error canceling booking: " + ex.getMessage());
        }
    }

    public void bookRoom(int roomNumber, String guestName) {
        try {
            Statement statement = conn.createStatement();
            String sql = "UPDATE ROOMS SET BOOKED = TRUE, GUESTNAME = '" + guestName + "' "
                    + "WHERE ROOMNUMBER = " + roomNumber;
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Room " + roomNumber + " booked by " + guestName);
        } catch (SQLException ex) {
            System.out.println("Error booking room: " + ex.getMessage());
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ROOMS");

            while (rs.next()) {
                int roomNumber = rs.getInt("ROOMNUMBER");
                int beds = rs.getInt("BEDS");
                boolean booked = rs.getBoolean("BOOKED");
                String guestName = rs.getString("GUESTNAME");

                Room room = new Room(roomNumber, beds);
                room.setBooked(booked);
                room.setGuestName(guestName); // ✅
                rooms.add(room);
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error reading all rooms: " + ex.getMessage());
        }
        return rooms;
    }

    public void closeConnection() {
        dbManager.closeConnection();
    }
}
