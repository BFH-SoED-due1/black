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

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Date;


public class JPADataAccess extends DataModel {

    private Set<Customer> customers = new HashSet<>();
    private Set<Room> rooms = new HashSet<>();
    private Set<Reservation> reservations = new TreeSet<>();

    public JPADataAccess() {
        // set test users
        addCustomer("user1", "123");
        addCustomer("user2", "234");

        // set test Rooms
        addRoom(1, "30m^");
        addRoom(2, "50m^2");
    }

    public boolean customerExists(String customerName) {
        for (Customer customer : customers)
            if (customer.getName().equals(customerName))
                return true;
        return false;
    }

    public boolean roomExists(int roomNumber) {
        for (Room room : rooms)
            if (room.getRoomNr() == roomNumber)
                return true;
        return false;
    }

    protected Customer getCustomer(String customerName) {
        for (Customer customer : customers)
            if (customer.getName().equals(customerName))
                return customer;
        return null;
    }

    protected Room getRoom(int roomNr) {
        for (Room room : rooms)
            if (room.getRoomNr() == roomNr)
                return room;
        return null;
    }

    public String getPassword(String customerName) {
        for (Customer customer : customers)
            if (customer.getName().equals(customerName))
                return customer.getPassword();
        return null;
    }

    public void addCustomer(String customerName, String password) {
        customers.add(new Customer(customerName, password));
    }

    public void addRoom(int roomNumber, String description) {
        rooms.add(new Room(roomNumber, description));
    }

    public void addReservation(String userName, int roomNumber, Date begin, Date end) throws Exception {
        if (!customerExists(userName) || !roomExists(roomNumber))
            throw new IllegalArgumentException();
        reservations.add(new Reservation(getCustomer(userName), getRoom(roomNumber), begin, end));
        System.out.println("Added new Reservation");
    }
}
