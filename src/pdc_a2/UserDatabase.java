/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.sql.*;

/**
 *
 * @author 64210
 */
public class UserDatabase {

    private final DBManager dbManager;
    private final Connection conn;

    public UserDatabase() {
        this.dbManager = new DBManager();
        this.conn = dbManager.getConnection();
    }

    //Creates the USERS table if it doesn't exist.
    public void setupUsersTable() {
        try {
            Statement stmt = conn.createStatement();
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "USERS", null);

            if (!rs.next()) {
                // Table doesn't exist — create and insert admin
                stmt.executeUpdate("CREATE TABLE USERS ("
                        + "USERNAME VARCHAR(20), "
                        + "PASSWORD VARCHAR(20), "
                        + "ROLE VARCHAR(15))");
                stmt.executeUpdate("INSERT INTO USERS VALUES ('admin', 'admin123', 'receptionist')");
            } else {
                // Table exists — ensure admin is present
                ResultSet checkAdmin = stmt.executeQuery("SELECT * FROM USERS WHERE USERNAME='admin'");
                if (!checkAdmin.next()) {
                    stmt.executeUpdate("INSERT INTO USERS VALUES ('admin', 'admin123', 'receptionist')");
                }
                checkAdmin.close();
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error creating or updating USERS table: " + ex.getMessage());
        }
    }

    //Validates login and returns role if correct, null otherwise.
    public String checkLogin(String username, String password) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ROLE FROM USERS WHERE "
                    + "USERNAME = '" + username + "' AND PASSWORD = '" + password + "'");
            if (rs.next()) {
                return rs.getString("ROLE");
            }
        } catch (SQLException ex) {
            System.out.println("Login check failed: " + ex.getMessage());
        }
        return null;
    }

    //Creates a new guest user (only guests can sign up).
    public boolean createUser(String username, String password) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO USERS VALUES ('" + username + "', '" + password + "', 'guest')");
            return true;
        } catch (SQLException ex) {
            System.out.println("Signup failed: " + ex.getMessage());
            return false;
        }
    }
}
