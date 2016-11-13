/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;


public class Room {
    private Set<Reservation> reservations;
    private int roomNr;
    private String description;

    public Room(int roomNr, String description) {
        reservations = new TreeSet<Reservation>();
        this.roomNr = roomNr;
        this.description = description;
    }

    protected void addReservation(Reservation reservation) throws Exception {
        for (Reservation res : reservations)
            if (res.timeCollisionWith(reservation))
                throw new Exception("time collision");
        reservations.add(reservation);
    }

    protected void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Set<Reservation> getReservations() {
        return Collections.unmodifiableSet(reservations);
    }

    public int getRoomNr(){
        return roomNr;
    }
}
