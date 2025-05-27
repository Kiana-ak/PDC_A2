/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_a2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 64210
 */
public class HotelRoomManager {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public HotelRoomManager() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    public void setupHotelDB() {
        try {
            this.statement = conn.createStatement();
            this.checkIfExists("ROOMS");

            this.statement.addBatch("CREATE TABLE ROOMS (ROOMNUMBER INT, BEDS INT, BOOKED BOOLEAN)");
            this.statement.addBatch("INSERT INTO ROOMS VALUES (101, 1, false)");
            this.statement.addBatch("INSERT INTO ROOMS VALUES (102, 1, false)");
            this.statement.addBatch("INSERT INTO ROOMS VALUES (201, 2, false)");
            this.statement.addBatch("INSERT INTO ROOMS VALUES (202, 2, false)");
            this.statement.addBatch("INSERT INTO ROOMS VALUES (301, 3, false)");
            this.statement.addBatch("INSERT INTO ROOMS VALUES (302, 3, false)");

            this.statement.executeBatch();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void checkIfExists(String name) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(name)) {
                    this.statement.executeUpdate("DROP TABLE " + name);
                    System.out.println("Dropped existing table: " + tableName);
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void closeConnection() {
        this.dbManager.closeConnection();
    }
}
