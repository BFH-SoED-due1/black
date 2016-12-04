/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import ch.bfh.ti.soed.hs16.srs.black.model.logic.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Room;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.junit.Before;
import org.junit.Test;
import org.sqlite.SQLiteException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        //testCustomer = new Customer("testUser1", "123");
        //testRoom = new Room(5, "testDescription");
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
        Customer testCustomer = new Customer("testUser1", "123");
        Room testRoom = new Room(5, "testDescription1");
        Reservation testReservation = dataModel.addReservation(testCustomer, testRoom, date1, date2);
        assertTrue(dataModel.getReservations(testRoom).contains(testReservation));
        assertTrue(dataModel.getReservations(testCustomer).contains(testReservation));
        dataModel.cancelReservation(testReservation);
        dataModel.removeCustomer(testCustomer);
        dataModel.removeRoom(testRoom);
    }

    @Test
    public void testCancelReservation() throws Exception {
        Customer testCustomer = new Customer("testUser2", "123");
        Room testRoom = new Room(6, "testDescription2");
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
        Customer testCustomer1 = new Customer("testName", "testPw");
        dataModel.addCustomer(testCustomer1);
        assertTrue(dataModel.getCustomer("testName").equals(testCustomer1));
        dataModel.removeCustomer(testCustomer1);
    }

    @Test (expected = NoResultException.class)
    public void testRemoveCustomer() throws Exception {
        Customer testCustomer1 = new Customer("testName", "testPw");
        dataModel.addCustomer(testCustomer1);
        assertTrue(dataModel.getCustomer("testName").equals(testCustomer1));
        dataModel.removeCustomer(testCustomer1);
        dataModel.getCustomer("testName");
    }

    @Test
    public void testGetCustomer() throws Exception {
        Customer testCustomer1 = new Customer("testName", "testPw");
        dataModel.addCustomer(testCustomer1);
        assertEquals(testCustomer1, dataModel.getCustomer("testName"));
        dataModel.removeCustomer(testCustomer1);
    }

    @Test
    public void testAddRoom() throws Exception {
        Room room1 = new Room(10, "testRoomDescription");
        dataModel.addRoom(room1);
        assertTrue(dataModel.getRoom(10).equals(room1));
        dataModel.removeRoom(room1);
    }

    @Test (expected = NoResultException.class)
    public void testRemoveRoom() throws Exception {
        Room room1 = new Room(10, "testRoomDescription");
        dataModel.addRoom(room1);
        dataModel.removeRoom(room1);
        dataModel.getRoom(10);
    }

    @Test
    public void testGetRoom() throws Exception {
        Room room1 = new Room(10, "testRoomDescription");
        dataModel.addRoom(room1);
        assertEquals(room1, dataModel.getRoom(10));
        dataModel.removeRoom(room1);
    }

    @Test
    public void testGetRooms() throws Exception {
        Room room1 = new Room(11, "testRoomDescription1");
        dataModel.addRoom(room1);
        dataModel.getRooms().contains(room1);
        dataModel.removeRoom(room1);
    }

    @Test (expected = PersistenceException.class)
    public void testRollback() throws Exception {
        Room room1 = new Room(11, "testRoomDescription1");
        Room room2 = new Room(11, "testRoomDescription1");
        try {
            dataModel.addRoom(room1);
            dataModel.addRoom(room2);
        } finally {
            dataModel.removeRoom(room1);
        }
    }
}
