package ch.bfh.ti.soed.hs16.srs.black;


import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;

public class ReservationTest {
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

    @Test
    public void testCompareReservations() throws Exception {
        int returnValue, expected;
        Room testRoom1 = new Room(1, "50m^2");
        Customer testCustomer1 = new Customer("Albert");
        Reservation reservation1;
        Reservation reservation2;

        // Test if Reservation1 and Reservation2 begin at the same time
        reservation1 = new Reservation(testCustomer1, testRoom1, date1, date2);
        reservation2 = new Reservation(testCustomer1, testRoom1, date1, date2);
        returnValue = reservation1.compareTo(reservation2);
        expected = 0; // both starts with date1
        assertEquals(returnValue, expected);

        // Test if Reservation1 begins later then Reservation2
        reservation1 = new Reservation(testCustomer1, testRoom1, date2, date3);
        reservation2 = new Reservation(testCustomer1, testRoom1, date1, date4);
        returnValue = reservation1.compareTo(reservation2);
        expected = 1;
        assertEquals(returnValue, expected);

        // Test if Reservation2 begins later then Reservation1
        reservation1 = new Reservation(testCustomer1, testRoom1, date1, date3);
        reservation2 = new Reservation(testCustomer1, testRoom1, date2, date4);
        returnValue = reservation1.compareTo(reservation2);
        expected = -1;
        assertEquals(returnValue, expected);
    }

    @Test
    public void testCreateReservation() throws Exception {
        Room testRoom1 = new Room(1,"50m^2");
        Customer testCustomer1 = new Customer("Albert");
        Reservation testReservation1 = new Reservation(testCustomer1, testRoom1, date1, date2);
        assertTrue(testRoom1.getReservations().contains(testReservation1));
        assertTrue(testCustomer1.getReservations().contains(testReservation1));
    }

    @Test
    public void testCancelReservation() throws Exception {
        Room testRoom1 = new Room(1,"50m^2");
        Customer testCustomer1 = new Customer("Albert");
        Reservation testReservation1 = new Reservation(testCustomer1, testRoom1, date1, date2);
        assertTrue(testRoom1.getReservations().contains(testReservation1));
        assertTrue(testCustomer1.getReservations().contains(testReservation1));
        testReservation1.cancelReservation();
        assertFalse(testRoom1.getReservations().contains(testReservation1));
        assertFalse(testCustomer1.getReservations().contains(testReservation1));
    }
}