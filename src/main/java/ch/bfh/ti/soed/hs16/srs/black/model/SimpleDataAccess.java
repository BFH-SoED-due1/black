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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class SimpleDataAccess extends DataModel {
    private static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.black.model.SimpleDataAccess";
    private Set<Customer> customers = new HashSet<>();
    private Set<Room> rooms = new HashSet<>();
    private Set<Reservation> reservations = new TreeSet<>();

    private SimpleDataAccess() {}  // to create an instance of this class, you have to use getInstance()

    public static DataModel getInstance(){
        synchronized (DataModel.class) {
            if (instance == null) {
                try {
                    @SuppressWarnings("rawtypes")
                    Class clazz = Class.forName(DEFAULT_DATA_ACCESS_CLASS);
                    instance = (DataModel) clazz.newInstance();
                } catch (Exception ex) {
                    System.err.println("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);

                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
                }
            }
            return instance;
        }
    }

    @Override
    public boolean customerExists(String customerName) {
        for (Customer customer : customers)
            if (customer.getName().equals(customerName))
                return true;
        return false;
    }

    @Override
    public String getPassword(String customerName) {
        for (Customer customer : customers)
            if (customer.getName().equals(customerName))
                return customer.getPassword();
        return null;
    }

    @Override
    public void addCustomer(String customerName, String password) {
        customers.add(new Customer(customerName, password));
    }

    @Override
    public void removeCustomer(String customerName) {
        // TODO ...
    }

    @Override
    public void addRoom(int roomNumber, String description) {
        rooms.add(new Room(roomNumber, description));
    }

    @Override
    public void removeRoom() {
        // TODO ...
    }

    @Override
    public void addReservation(String userName, int roomNumber, Date begin, Date end) throws Exception {
        if (!customerExists(userName) || !roomExists(roomNumber))
            throw new IllegalArgumentException();
        reservations.add(new Reservation(getCustomer(userName), getRoom(roomNumber), begin, end));
        System.out.println("Added new Reservation");
    }

    @Override
    public void cancelReservation() {
        // TODO ...
    }

    private boolean roomExists(int roomNumber) {
        for (Room room : rooms)
            if (room.getRoomNr() == roomNumber)
                return true;
        return false;
    }

    private Customer getCustomer(String customerName) {
        for (Customer customer : customers)
            if (customer.getName().equals(customerName))
                return customer;
        return null;
    }

    private Room getRoom(int roomNr) {
        for (Room room : rooms)
            if (room.getRoomNr() == roomNr)
                return room;
        return null;
    }
}
