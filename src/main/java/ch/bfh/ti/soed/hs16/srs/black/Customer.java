package ch.bfh.ti.soed.hs16.srs.black;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Customer {
    private Set<Reservation> reservations;
    private String name;
    // other personal information ...

    public Customer(String name){
        reservations = new TreeSet<>();
        this.name = name;
    }

    protected void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    protected void removeReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public Set<Reservation> getReservations(){
        return Collections.unmodifiableSet(reservations);
    }
}
