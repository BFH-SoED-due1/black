/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.logic;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Room object stores all Reservations in a an ArrayList.
 * Reservations can be added and removed afterwards.
 */
@Entity(name = "Room")
public class Room {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.PERSIST)
    private List<Reservation> reservations;
    @Column(unique = true)
    private int roomNr;
    private String description;

    public Room() {} // null constructor

    public Room(int roomNr, String description) {
        reservations = new ArrayList();
        this.roomNr = roomNr;
        this.description = description;
    }

    void addReservation(Reservation reservation) throws Exception {
        for (Reservation res : reservations)
            if (res.timeCollisionWith(reservation))
                throw new Exception("time collision");
        reservations.add(reservation);
    }

    void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public int getRoomNr(){
        return roomNr;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return roomNr == room.roomNr;

    }

    @Override
    public int hashCode() {
        return roomNr;
    }
}
