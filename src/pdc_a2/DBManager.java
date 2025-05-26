/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author 64210
 */
public class DBManager {
    private static final String URL = "jdbc:derby://localhost:1527/HotelDB";  // URL of the DB host
    private static final String USER_NAME = "pdc"; // username
    private static final String PASSWORD = "pdc"; // password

    private Connection conn;

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    // Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " connected successfully.");
            } catch (SQLException ex) {
                System.out.println("Connection failed: " + ex.getMessage());
            }
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error closing DB connection: " + ex.getMessage());
            }
        }
    }
    
}
