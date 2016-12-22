/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.Date;
import java.util.List;

public abstract class DataModel {

    protected static DataModel instance = null;

    // for reservation handling ...
    public abstract Reservation addReservation(Customer customer, Room room, Date begin, Date end) throws Exception;
    public abstract void cancelReservation(Reservation reservation);
    public abstract List<Reservation> getReservations(Customer customer);
    public abstract List<Reservation> getReservations(Room room);

    // for customer handling ...
    public abstract Customer addCustomer(String username, String password);
    public abstract void removeCustomer(Customer customer);
    public abstract Customer getCustomer(String customerName) throws Exception;
    //public abstract boolean customerExists(String customerName);

    // for room handling ...
    public abstract Room addRoom(int roomNr, String description);
    public abstract void removeRoom(Room room);
    public abstract Room getRoom(int roomNr);
    public abstract List<Room> getRooms();
}
