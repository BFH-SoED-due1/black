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


public class Customer {
    private Set<Reservation> reservations;
    private String name;
    private String password;
    // other personal information ...

    public Customer(String name, String password) {
        reservations = new TreeSet<>();
        this.name = name;
        this.password = password;
    }

    protected void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    protected void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Set<Reservation> getReservations() {
        return Collections.unmodifiableSet(reservations);
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }
}
