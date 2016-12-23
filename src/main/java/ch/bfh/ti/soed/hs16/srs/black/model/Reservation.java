/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.Date;


public interface Reservation {

    void cancelReservation();

    Long getId();

    Customer getCustomer();

    Room getRoom();

    Date getBegin();

    Date getEnd();

    boolean timeCollisionWith(Reservation o);
}
