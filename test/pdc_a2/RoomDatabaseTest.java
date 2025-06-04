/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_a2;

import pdc_a2.model.HotelRoomManager;
import pdc_a2.model.RoomDatabase;
import pdc_a2.model.Room;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 64210
 */
public class RoomDatabaseTest {
    
    private RoomDatabase db;
    
    public RoomDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // Setup a fresh database state (recreate the tables if needed)
        HotelRoomManager manager = new HotelRoomManager();
        manager.setupHotelDB(); // resets DB
        manager.closeConnection();
        db = new RoomDatabase();
    }
    
    @After
    public void tearDown() {
        db.closeConnection();
    }

    /**
     * Test of getAvailableRooms method, of class RoomDatabase.
     */
    @Test
    public void testGetAvailableRooms() {
        List<Room> available = db.getAvailableRooms();
        assertEquals(6, available.size()); // initially 6 rooms
    }

    /**
     * Test of cancelBooking method, of class RoomDatabase.
     */
    @Test
    public void testCancelBookingUpdatesDatabase() {
        db.bookRoom(102, "Nora");
        db.cancelBooking(102);
        List<Room> bookings = db.getBookingsForGuest("Nora");
        assertTrue(bookings.isEmpty());
    }

    /**
     * Test of bookRoom method, of class RoomDatabase.
     */
    @Test
    public void testBookRoomUpdatesDatabase() {
        db.bookRoom(101, "Alice");
        List<Room> bookings = db.getBookingsForGuest("Alice");
        assertFalse(bookings.isEmpty());
        assertEquals(101, bookings.get(0).getRoomNumber());
    }
    
    @Test
    public void testGetAllRoomsReturnsAllRooms() {
        List<Room> all = db.getAllRooms();
        assertEquals(6, all.size());
    }

}
