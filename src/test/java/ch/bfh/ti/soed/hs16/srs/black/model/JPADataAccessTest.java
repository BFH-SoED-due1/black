/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import ch.bfh.ti.soed.hs16.srs.black.model.logic.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Room;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class JPADataAccessTest {
    DataModel dataModel;
    Customer testCustomer;
    Room testRoom;
    Date date1, date2;

    @Before
    public void setUp() throws Exception {
        dataModel = JPADataAccess.getInstance();
        testCustomer = new Customer("user1", "123");
        testRoom = new Room(1, "79m^");
        Calendar c = new GregorianCalendar();
        c.set(2016, 11, 9, 13, 22, 15);
        date1 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 9, 15, 22, 15);
        date2 = new Date(c.getTimeInMillis());
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
        // TODO ...
    }

    @Test
    public void testCancelReservation() throws Exception {
        // TODO ...
    }

    @Test
    public void testGetReservations() throws Exception {
        // TODO ...
    }

    @Test
    public void testGetReservations1() throws Exception {
        // TODO ...
    }

    @Test
    public void testAddCustomer() throws Exception {
        // TODO ...
    }

    @Test
    public void testRemoveCustomer() throws Exception {
        // TODO ...
    }

    @Test
    public void testGetCustomer() throws Exception {
        // TODO ...
    }

    @Test
    public void testAddRoom() throws Exception {
        // TODO ...
    }

    @Test
    public void testRemoveRoom() throws Exception {
        // TODO ...
    }

    @Test
    public void testGetRoom() throws Exception {
        // TODO ...
    }
}