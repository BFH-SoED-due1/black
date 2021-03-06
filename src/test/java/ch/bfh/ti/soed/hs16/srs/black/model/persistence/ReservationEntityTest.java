/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.persistence;

import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.Room;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class ReservationEntityTest {

    // some test dates: date1 < date2 < date3 < date4
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;

    @Before
    public void setUpTestDates(){
        Calendar c = new GregorianCalendar();
        c.set(2016, 11, 9, 13, 22, 15);
        date1 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 9, 15, 22, 15);
        date2 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 9, 16, 22, 15);
        date3 = new Date(c.getTimeInMillis());
        c.set(2016, 11, 9, 17, 22, 15);
        date4 = new Date(c.getTimeInMillis());
    }
/*
    @Test
    public void testCompareTo() throws Exception {
        int returnValue, expected;
        RoomEntity testRoom1 = new RoomEntity(1, "50m^2");
        RoomEntity testRoom2 = new RoomEntity(2, "70m^");
        CustomerEntity testCustomer1 = new CustomerEntity("Albert", "123");
        ReservationEntity reservation1;
        ReservationEntity reservation2;

        // Test if Reservation1 and Reservation2 begin at the same time
        reservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        reservation2 = new ReservationEntity(testCustomer1, testRoom2, date1, date2);
        returnValue = reservation1.compareTo(reservation2);
        expected = 0; // both starts with date1
        assertEquals(returnValue, expected);

        // Test if Reservation1 begins later then Reservation2
        reservation1 = new ReservationEntity(testCustomer1, testRoom1, date2, date3);
        reservation2 = new ReservationEntity(testCustomer1, testRoom2, date1, date4);
        returnValue = reservation1.compareTo(reservation2);
        expected = 1;
        assertEquals(returnValue, expected);

        // Test if Reservation2 begins later then Reservation1
        reservation1 = new ReservationEntity(testCustomer1, testRoom2, date1, date3);
        reservation2 = new ReservationEntity(testCustomer1, testRoom1, date2, date4);
        returnValue = reservation1.compareTo(reservation2);
        expected = -1;
        assertEquals(returnValue, expected);
    }
*/
    @Test
    public void testCreateReservation() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        assertTrue(testRoom1.getReservations().contains(testReservation1));
        assertTrue(testCustomer1.getReservations().contains(testReservation1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateReservationWithBeginEqualsEnd() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCreateReservationWithEndBeforeBegin() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date2, date1);
    }

    @Test
    public void testCancelReservation() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        assertTrue(testRoom1.getReservations().contains(testReservation1));
        assertTrue(testCustomer1.getReservations().contains(testReservation1));
        testReservation1.cancelReservation();
        assertFalse(testRoom1.getReservations().contains(testReservation1));
        assertFalse(testCustomer1.getReservations().contains(testReservation1));
    }

    @Test
    public void testTimeCollisionWith() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Room testRoom2 = new RoomEntity(2,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");

        // case begin1 = begin2, end1 = end2
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        Reservation testReservation2 = new ReservationEntity(testCustomer1, testRoom2, date1, date2);
        boolean result = testReservation1.timeCollisionWith(testReservation2);
        assertTrue(result);
        testReservation1.cancelReservation();
        testReservation2.cancelReservation();

        // case begin1 < begin2 < end1 < end2
        testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date3);
        testReservation2 = new ReservationEntity(testCustomer1, testRoom2, date2, date4);
        result = testReservation1.timeCollisionWith(testReservation2);
        assertTrue(result);
        testReservation1.cancelReservation();
        testReservation2.cancelReservation();

        // case begin2 < begin1 < end2 < end1
        testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date2, date4);
        testReservation2 = new ReservationEntity(testCustomer1, testRoom2, date1, date3);
        result = testReservation1.timeCollisionWith(testReservation2);
        assertTrue(result);
        testReservation1.cancelReservation();
        testReservation2.cancelReservation();

        // case begin1 < begin2 < end2 < end1
        testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date4);
        testReservation2 = new ReservationEntity(testCustomer1, testRoom2, date2, date3);
        result = testReservation1.timeCollisionWith(testReservation2);
        assertTrue(result);
        testReservation1.cancelReservation();
        testReservation2.cancelReservation();

        // case begin2 < begin1 < end1 < end2
        testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date2, date3);
        testReservation2 = new ReservationEntity(testCustomer1, testRoom2, date1, date4);
        result = testReservation1.timeCollisionWith(testReservation2);
        assertTrue(result);
        testReservation1.cancelReservation();
        testReservation2.cancelReservation();

        // case begin1 < end1 < begin2 < end2
        testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        testReservation2 = new ReservationEntity(testCustomer1, testRoom2, date3, date4);
        result = testReservation1.timeCollisionWith(testReservation2);
        assertFalse(result);
        testReservation1.cancelReservation();
        testReservation2.cancelReservation();
    }

    @Test(expected = Exception.class)
    public void testAvoidDoubleBookingRoom() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        new ReservationEntity(testCustomer1, testRoom1, date2, date3);
        new ReservationEntity(testCustomer1, testRoom1, date1, date4);
    }

    @Test
    public void testGetCustomer() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        assertEquals(testReservation1.getCustomer(), testCustomer1);
    }

    @Test
    public void testGetRoom() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        assertEquals(testReservation1.getRoom(), testRoom1);
    }

    @Test
    public void testGetBegin() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        assertEquals(testReservation1.getBegin(), date1);
    }

    @Test
    public void testGetEnd() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        assertEquals(testReservation1.getEnd(), date2);
    }

    @Test
    public void testEquals() throws Exception {
        Room testRoom1 = new RoomEntity(1,"50m^2");
        Customer testCustomer1 = new CustomerEntity("Albert", "123");
        Reservation testReservation1 = new ReservationEntity(testCustomer1, testRoom1, date1, date2);
        Reservation testReservation2 = new ReservationEntity(testCustomer1, testRoom1, date3, date4);

        assertTrue(date1.equals(testReservation1.getBegin()));
        assertTrue(date2.equals(testReservation1.getEnd()));

        assertTrue(testReservation1.equals(testReservation1));
        assertFalse(testReservation1.equals(null));
        assertFalse(testReservation1.equals(testRoom1));
        assertFalse(testReservation1.equals(testReservation2));
    }
}
