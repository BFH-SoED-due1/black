/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.Date;

public abstract class DataModel {
    protected static DataModel instance = null;

    // for reservation handling ...
    public abstract void addReservation(String userName, int roomNumber, Date begin, Date end) throws Exception;
    public abstract void cancelReservation();

    // for customer handling ...
    public abstract void addCustomer(String customerName, String password);
    public abstract void removeCustomer(String customerName);
    public abstract boolean customerExists(String customerName);
    public abstract String getPassword(String customerName);


    // for room handling ..
    public abstract void addRoom(int roomNr, String description);
    public abstract void removeRoom();
}
