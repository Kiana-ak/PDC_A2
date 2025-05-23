/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
import java.util.*;

/**
 *
 * @author 64210
 */
public class HotelManager {
    
    
    public static List<Room> loadRooms() 
    {
        List<Room> rooms = new ArrayList<>();
        BufferedReader reader = null;
        try 
        {
            reader = new BufferedReader(new FileReader("roomsList.txt"));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                int roomNumber = Integer.parseInt(parts[0]);
                int beds = Integer.parseInt(parts[1]);
                Room room = new Room(roomNumber, beds);
                if (parts.length > 2 && parts[2].equalsIgnoreCase("BOOKED"))
                {
                    room.setBooked(true);
                }
                rooms.add(room);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading rooms: " + e.getMessage());
        } 
        finally 
        {
            if (reader != null) 
            {
                try 
                {
                    reader.close();
                } catch (IOException e) 
                {
                    System.out.println("Error closing reader: " + e.getMessage());
                }
            }
        }
        return rooms;
    }

    public static void bookRoom(List<Room> rooms) 
    {
        PrintWriter writer = null;
        try 
        {
            writer = new PrintWriter(new FileWriter("roomsList.txt"));
            for (Room room : rooms) 
            {
                writer.println(room.addBooked());
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving rooms: " + e.getMessage());
        } 
        finally 
        {
            if (writer != null) 
            {
                writer.close();
            }
        }
    }

    public static Room findAvailableRoom(List<Room> rooms, int roomNumber)
    {
        for (Room room : rooms) 
        {
            if (room.getRoomNumber() == roomNumber && !room.isBooked()) 
            {
                return room;
            }
        }
        return null;
    }

    public static void displayAvailableRooms(List<Room> rooms) 
    {
        for (Room room : rooms) 
        {
            if (!room.isBooked()) 
            {
                System.out.println("Room " + room.getRoomNumber() + " - Beds: " + room.getBeds() + " - Price: $" + room.getPrice());
            }
        }
    }
}
