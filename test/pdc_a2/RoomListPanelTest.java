/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_a2;

import pdc_a2.view.RoomListPanel;
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
public class RoomListPanelTest {

    public RoomListPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testValidGuestName() {
        assertTrue(RoomListPanel.isValidGuestName("Rose"));
        assertTrue(RoomListPanel.isValidGuestName("Rose May"));
    }

    @Test
    public void testInvalidGuestName_Numbers() {
        assertFalse(RoomListPanel.isValidGuestName("Rose123"));
    }

    @Test
    public void testInvalidGuestName_Symbols() {
        assertFalse(RoomListPanel.isValidGuestName("Rose@"));
    }

    @Test
    public void testInvalidGuestName_Empty() {
        assertFalse(RoomListPanel.isValidGuestName(""));
        assertFalse(RoomListPanel.isValidGuestName("   "));
        assertFalse(RoomListPanel.isValidGuestName(null));
    }

}
