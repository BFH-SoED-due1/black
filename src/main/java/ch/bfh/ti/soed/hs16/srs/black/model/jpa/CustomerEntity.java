/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.jpa;

import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.Reservation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The CustomerEntity object stores all Reservations of a CustomerEntity in an ArrayList.
 * Personal Information (Name and Password) are included too and can be changed after object creation.
 */
@Entity(name = "Customer")
public class CustomerEntity implements Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(targetEntity = ReservationEntity.class, cascade = CascadeType.PERSIST)
    private List<Reservation> reservations;
    @Column(unique = true)
    private String name;
    private String password;

    public CustomerEntity() {} // null constructor

    public CustomerEntity(String name, String password) {
        reservations = new ArrayList<>();
        this.name = name;
        this.password = password;
        if (name.isEmpty() || password.isEmpty())
            throw new IllegalArgumentException();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity customer = (CustomerEntity) o;

        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
