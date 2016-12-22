/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.jpa;

import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.Room;
import org.junit.Before;
import org.junit.Test;
import javax.persistence.NoResultException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class JPADataAccessTest {

    DataModel dataModel;
    Customer testCustomer;
    Room testRoom;
    Date date1, date2, date3, date4;

    @Before
    public void setUp() throws Exception {
        dataModel = JPADataAccess.getInstance();
        //testCustomer = new CustomerEntity("testUser1", "123");
        //testRoom = new RoomEntity(5, "testDescription");
        Calendar c = new GregorianCalendar();
        c.set(2016, 11, 9, 13, 22, 15);
        date1 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 10, 15, 22, 15);
        date2 = new Date(c.getTimeInMillis());
        c.set(2017, 12, 9, 13, 22, 15);
        date3 = new Date(c.getTimeInMillis());
        c.set(2017, 12, 10, 15, 22, 15);
        date4 = new Date(c.getTimeInMillis());
    }

    @Test
    public void testGetInstance() throws Exception {
        DataModel dataModel1 = JPADataAccess.getInstance();
        DataModel dataModel2 = JPADataAccess.getInstance();
        assertNotEquals(dataModel1, null);
        assertEquals(dataModel1, dataModel2);
    }

    @Test
    public void testAddReservation() throws Exception {
        Customer testCustomer = new CustomerEntity("testUser1", "123");
        Room testRoom = new RoomEntity(5, "testDescription1");
        Reservation testReservation = dataModel.addReservation(testCustomer, testRoom, date1, date2);
        assertTrue(dataModel.getReservations(testRoom).contains(testReservation));
        assertTrue(dataModel.getReservations(testCustomer).contains(testReservation));
        dataModel.cancelReservation(testReservation);
        dataModel.removeCustomer(testCustomer);
        dataModel.removeRoom(testRoom);
    }

    @Test
    public void testCancelReservation() throws Exception {
        Customer testCustomer = new CustomerEntity("testUser2", "123");
        Room testRoom = new RoomEntity(6, "testDescription2");
        Reservation testReservation = dataModel.addReservation(testCustomer, testRoom, date3, date4);
        dataModel.cancelReservation(testReservation);
        assertFalse(dataModel.getReservations(testRoom).contains(testReservation));
        assertFalse(dataModel.getReservations(testCustomer).contains(testReservation));
        dataModel.removeCustomer(testCustomer);
        dataModel.removeRoom(testRoom);
    }

    @Test
    public void testGetReservations() throws Exception {
        assertEquals(dataModel.getReservations(testCustomer), dataModel.getReservations(testRoom));
    }

    @Test
    public void testAddCustomer() throws Exception {
        Customer testCustomer1 = new CustomerEntity("testName1", "testPw");
        dataModel.addCustomer("testName1", "testPw");
        assertTrue(dataModel.getCustomer("testName1").equals(testCustomer1));
        dataModel.removeCustomer(testCustomer1);
    }

    @Test (expected = Exception.class)
    public void testRemoveCustomer() throws Exception {
        Customer testCustomer1 = new CustomerEntity("testName2", "testPw");
        dataModel.addCustomer("testName2", "testPw");
        assertTrue(dataModel.getCustomer("testName2").equals(testCustomer1));
        dataModel.removeCustomer(testCustomer1);
        dataModel.getCustomer("testName2");
    }

    @Test
    public void testGetCustomer() throws Exception {
        Customer testCustomer1 = new CustomerEntity("testName3", "testPw");
        dataModel.addCustomer("testName3", "testPw");
        assertEquals(testCustomer1, dataModel.getCustomer("testName3"));
        dataModel.removeCustomer(testCustomer1);
    }

    @Test
    public void testAddRoom() throws Exception {
        Room room1 = new RoomEntity(10, "testRoomDescription1");
        dataModel.addRoom(10, "testRoomDescription1");
        assertTrue(dataModel.getRoom(10).equals(room1));
        dataModel.removeRoom(room1);
    }

    @Test (expected = Exception.class)
    public void testRemoveRoom() throws Exception {
        //Room room1 = new RoomEntity(11, "testRoomDescription2");
        dataModel.addRoom(11, "testRoomDescription2");
        Room room = dataModel.getRoom(11);
        dataModel.removeRoom(room);
        dataModel.getRoom(11);
    }

    @Test
    public void testGetRoom() throws Exception {
        Room room1 = new RoomEntity(12, "testRoomDescription3");
        dataModel.addRoom(12, "testRoomDescription3");
        assertEquals(room1, dataModel.getRoom(12));
        dataModel.removeRoom(room1);
    }

    @Test
    public void testGetRooms() throws Exception {
        Room room1 = new RoomEntity(13, "testRoomDescription4");
        dataModel.addRoom(13, "testRoomDescription4");
        dataModel.getRooms().contains(room1);
        dataModel.removeRoom(room1);
    }

    @Test (expected = IllegalStateException.class)
    public void testRollback() throws Exception {
        Room room1 = new RoomEntity(14, "testRoomDescription5");
        Room room2 = new RoomEntity(14, "testRoomDescription5");
        try {
            dataModel.addRoom(14, "testRoomDescription5");
            dataModel.addRoom(14, "testRoomDescription5");
        } finally {
            dataModel.removeRoom(room1);
        }
    }
}
