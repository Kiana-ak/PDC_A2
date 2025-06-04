/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import pdc_a2.model.Person;
import pdc_a2.model.Room;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author 64210
 */
public class Invoice {
    
    public static void writeInvoice(Person person, Room room) {
        String filename = "invoice_" + person.getName().replaceAll(" ", "_") + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Hotel Booking Invoice");
            writer.println("----------------------");
            writer.println("Customer: " + person.getName());
            writer.println("Role: " + person.getRole());
            writer.println("Room Number: " + room.getRoomNumber());
            writer.println("Beds: " + room.getBeds());
            writer.println("Price: $" + room.getPrice());
            writer.println();
            writer.println("Thank you for booking with us!");
        } catch (IOException e) {
            System.out.println("Error writing invoice: " + e.getMessage());
        }
    }
    
}
