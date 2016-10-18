package ch.bfh.ti.soed.hs16.srs.black;

import java.util.*;

public class Customer {
    private Set<Reservation> reservations;
    private String name;
    // other personal information ...

    Customer(String name){
        reservations = new TreeSet<>();
        this.name = name;
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public Set<Reservation> getReservations(){
        return Collections.unmodifiableSet(reservations);
    }
}
