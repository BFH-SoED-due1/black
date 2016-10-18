package ch.bfh.ti.soed.hs16.srs.black;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Room {
    private Set<Reservation> reservations;
    private int roomNr;
    private String description;

    Room(int roomNr, String description){
        reservations = new TreeSet<Reservation>();
        this.roomNr = roomNr;
        this.description = description;
    }

    public void addReservation(Reservation reservation){
        // throw exception if time slot is not empty
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public Set<Reservation> getReservations(){
        return Collections.unmodifiableSet(reservations);
    }
}
