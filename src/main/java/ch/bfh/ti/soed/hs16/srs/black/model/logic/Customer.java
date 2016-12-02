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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;

/**
 * The Customer object stores all Reservations of a Customer in a TreeSet.
 * Personal Information (Name and Password) are included too and can be changed after object creation.
 */
@Entity(name = "Customer")
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(targetEntity = Reservation.class, cascade = CascadeType.PERSIST)
    private Set<Reservation> reservations;
    @Column(unique = true)
    private String name;
    private String password;

    public Customer() {} // null constructor

    public Customer(String name, String password) {
        reservations = new TreeSet<>();
        this.name = name;
        this.password = password;
        if (name.isEmpty() || password.isEmpty())
            throw new IllegalArgumentException();
    }

    void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    void removeReservation(Reservation reservation) {
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
