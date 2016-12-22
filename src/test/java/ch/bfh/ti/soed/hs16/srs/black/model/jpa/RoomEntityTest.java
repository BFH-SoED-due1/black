/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.jpa;

import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.Room;
import ch.bfh.ti.soed.hs16.srs.black.model.jpa.CustomerEntity;
import ch.bfh.ti.soed.hs16.srs.black.model.jpa.ReservationEntity;
import ch.bfh.ti.soed.hs16.srs.black.model.jpa.RoomEntity;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RoomEntityTest {

    Customer testCustomer;
    Room testRoom;
    Date date1, date2, date3;
    int roomNr;
    String description;

    @Before
    public void setUp() throws Exception {
        testCustomer = new CustomerEntity("user1", "123");
        roomNr = 1;
        description = "79m^";
        testRoom = new RoomEntity(roomNr, description);
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
        Reservation testReservation1 = new ReservationEntity(testCustomer, testRoom, date1, date2);
        Reservation testReservation2 = new ReservationEntity(testCustomer, testRoom, date2, date3);
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
        Room testRoom1 = new RoomEntity(2, "test1");
        Room testRoom2 = new RoomEntity(2, "test1");
        Room testRoom3 = new RoomEntity(3, "test2");

        assertTrue(testRoom1.equals(testRoom2));
        assertFalse(testRoom1.equals(testRoom3));
        assertFalse(testRoom1.equals(testCustomer));
        assertFalse(testRoom1.equals(null));
        assertTrue(testRoom1.equals(testRoom1));

    }
}
