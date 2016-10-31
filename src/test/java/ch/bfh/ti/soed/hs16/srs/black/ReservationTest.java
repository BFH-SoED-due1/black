package ch.bfh.ti.soed.hs16.srs.black;

import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.*;

public class ReservationTest {
    @Test
    public void testCompareReservations() throws Exception {
        int returnValue, expected;
        long startTimeReservation1;
        long startTimeReservation2;
        Room testRoom1 = new Room(1, "50m^2");
        Customer testCustomer1 = new Customer("Albert");
        Reservation reservation1;
        Reservation reservation2;

        // Test if Reservation1 and Reservation2 begin at the same time
        startTimeReservation1 = 100;
        startTimeReservation2 = startTimeReservation1;
        reservation1 = new Reservation(testCustomer1, testRoom1, startTimeReservation1, 200);
        reservation2 = new Reservation(testCustomer1, testRoom1, startTimeReservation2, 200);
        returnValue = reservation1.compareTo(reservation2);
        expected = (startTimeReservation1 == startTimeReservation2)?0:1;
        assertEquals(returnValue, expected);

        // Test if Reservation1 begins later then Reservation2
        startTimeReservation1 = 100;
        startTimeReservation2 = 99;
        reservation1 = new Reservation(testCustomer1, testRoom1, startTimeReservation1, 200);
        reservation2 = new Reservation(testCustomer1, testRoom1, startTimeReservation2, 200);
        returnValue = reservation1.compareTo(reservation2);
        expected = (startTimeReservation1 > startTimeReservation2)?1:-1;
        assertEquals(returnValue, expected);

        // Test if Reservation2 begins later then Reservation1
        startTimeReservation1 = 100;
        startTimeReservation2 = 101;
        reservation1 = new Reservation(testCustomer1, testRoom1, startTimeReservation1, 200);
        reservation2 = new Reservation(testCustomer1, testRoom1, startTimeReservation2, 200);
        returnValue = reservation1.compareTo(reservation2);
        expected = (startTimeReservation1 < startTimeReservation2)?-1:1;
        assertEquals(returnValue, expected);
    }

    @Test
    public void testCreateReservation() throws Exception {
        Room testRoom1 = new Room(1,"50m^2");
        Customer testCustomer1 = new Customer("Albert");

        Reservation testReservation1 = new Reservation(testCustomer1, testRoom1, 100, 200);
        Set<Reservation> test = testRoom1.getReservations();
        assertTrue(testRoom1.getReservations().contains(testReservation1));
        assertTrue(testCustomer1.getReservations().contains(testReservation1));
    }

    @Test
    public void testCancelReservation() throws Exception {
        Room testRoom1 = new Room(1,"50m^2");
        Customer testCustomer1 = new Customer("Albert");
        Reservation testReservation1 = new Reservation(testCustomer1, testRoom1, 100, 200);
        Set<Reservation> test = testRoom1.getReservations();
        assertTrue(testRoom1.getReservations().contains(testReservation1));
        assertTrue(testCustomer1.getReservations().contains(testReservation1));

        testReservation1.cancelReservation();
        assertFalse(testRoom1.getReservations().contains(testReservation1));
        assertFalse(testCustomer1.getReservations().contains(testReservation1));
    }
}