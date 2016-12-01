/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;


/**
 * The room Object stores all reservations(Reservation) in a TreeSet. reservations can be added and removed afterwards.
 */
@Entity(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = Reservation.class)
    private Set<Reservation> reservations;
    @Column(unique = true)
    private int roomNr;
    private String description;

    public Room(){} // null constructor

    public Room(int roomNr, String description) {
        reservations = new TreeSet<>();
        this.roomNr = roomNr;
        this.description = description;
    }

    void addReservation(Reservation reservation) throws Exception {
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
