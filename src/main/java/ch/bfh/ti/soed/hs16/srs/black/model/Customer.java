/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.List;


public interface Customer {

    List<Reservation> getReservations();

    String getPassword();

    String getName();

    void addReservation(Reservation reservation);

    void removeReservation(Reservation reservation);
}
