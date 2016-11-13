/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.Date;


public class Reservation implements Comparable<Reservation> {
    private final Customer customer;
    private final Room room;
    private final Date begin;
    private final Date end;
    // price class

    public Reservation(Customer customer, Room room, Date begin, Date end) throws Exception {
        this.customer = customer;
        this.room = room;
        this.begin = begin;
        this.end = end;
        if (customer == null || room == null || begin == null || end == null || begin.after(end) || begin.equals(end))
            throw new IllegalArgumentException();
        customer.addReservation(this);
        room.addReservation(this);
    }

    public void cancelReservation() {
        customer.removeReservation(this);
        room.removeReservation(this);
    }

    public boolean timeCollisionWith(Reservation o) {
        return begin.after(o.begin) && begin.before(o.end) || begin.before(o.begin) && end.after(o.begin)
                || begin.equals(o.begin) && end.equals(o.end);
    }

    @Override
    public int compareTo(Reservation o) {
        if (begin.before(o.begin))
            return -1;
        if (begin.after(o.begin))
            return 1;
        return 0;
    }
}
