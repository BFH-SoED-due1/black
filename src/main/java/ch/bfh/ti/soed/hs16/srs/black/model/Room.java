/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.List;

public interface Room {

    List<Reservation> getReservations();

    int getRoomNr();

    String getDescription();

    void addReservation(Reservation reservation) throws Exception;

    void removeReservation(Reservation reservation);
}
