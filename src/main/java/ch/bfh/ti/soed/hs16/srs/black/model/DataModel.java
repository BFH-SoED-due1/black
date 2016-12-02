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
import java.util.Date;


public abstract class DataModel {

    protected static DataModel instance = null;

    // for reservation handling ...
    public abstract Reservation addReservation(Customer customer, Room room, Date begin, Date end) throws Exception;
    public abstract void cancelReservation(Reservation reservation);
    public abstract Set<Reservation> getReservations(Customer customer);
    public abstract Set<Reservation> getReservations(Room room);

    // for customer handling ...
    public abstract void addCustomer(Customer customer);
    public abstract void removeCustomer(Customer customer);
    public abstract Customer getCustomer(String customerName);

    // for room handling ...
    public abstract void addRoom(Room room);
    public abstract void removeRoom(Room room);
    public abstract Room getRoom(int roomNr);
}
