/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.logic;

import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RoomTest {

    Customer testCustomer;
    Room testRoom;
    Date date1, date2, date3;
    int roomNr;
    String description;

    @Before
    public void setUp() throws Exception {
        testCustomer = new Customer("user1", "123");
        roomNr = 1;
        description = "79m^";
        testRoom = new Room(roomNr, description);
        Calendar c = new GregorianCalendar();
        c.set(2016, 11, 9, 13, 22, 15);
        date1 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 9, 15, 22, 15);
        date2 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 9, 17, 22, 15);
        date3 = new Date(c.getTimeInMillis());
    }
    @Test
    public void testGetReservations() throws Exception {
        Reservation testReservation1 = new Reservation(testCustomer, testRoom, date1, date2);
        Reservation testReservation2 = new Reservation(testCustomer, testRoom, date2, date3);
        assertTrue(testRoom.getReservations().contains(testReservation1));
        assertTrue(testRoom.getReservations().contains(testReservation2));
    }

    @Test
    public void testGetRoomNr() throws Exception {
        assertEquals(testRoom.getRoomNr(), roomNr);
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals(testRoom.getDescription(), description);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(roomNr, testRoom.getRoomNr());
    }
}
