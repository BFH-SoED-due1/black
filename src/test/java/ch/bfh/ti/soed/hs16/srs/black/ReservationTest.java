package ch.bfh.ti.soed.hs16.srs.black;

import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.*;

public class ReservationTest {
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