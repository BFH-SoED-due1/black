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
import ch.bfh.ti.soed.hs16.srs.black.model.Room;
import com.vaadin.data.Property;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * The ReservationEntity object stores a specific ReservationEntity made by a CustomerEntity and contains a RoomEntity.
 * The Comparable class is overwritten. We use the data object to compare time ranges of made Reservations.
 */
@Entity(name = "Reservation")
public class ReservationEntity implements Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = CustomerEntity.class, cascade = CascadeType.PERSIST)
    private Customer customer;
    @ManyToOne(targetEntity = RoomEntity.class, cascade = CascadeType.PERSIST)
    private Room room;
    @Temporal(TemporalType.DATE)
    private Date begin;
    @Temporal(TemporalType.DATE)
    private Date end;

    public ReservationEntity() {} // null constructor

    public ReservationEntity(Customer customer, Room room, Date begin, Date end) throws Exception {
        this.customer = customer;
        this.room = room;
        this.begin = begin;
        this.end = end;
        if (customer == null || room == null || begin == null || end == null || begin.after(end) || begin.equals(end))
            throw new IllegalArgumentException();
        customer.addReservation(this);
        room.addReservation(this);
    }

    public void cancelReservation() {
        customer.removeReservation(this);
        room.removeReservation(this);
    }

    public boolean timeCollisionWith(Reservation o) {
        return begin.after(o.getBegin()) && begin.before(o.getEnd()) || begin.before(o.getBegin()) && end.after(o.getBegin())
                || begin.equals(o.getBegin()) && end.equals(o.getEnd());
    }
/*
    @Override
    public int compareTo(ReservationEntity o) {
        if (begin.before(o.begin))
            return -1;
        if (begin.after(o.begin))
            return 1;
        return 0;
    }
*/

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        /*
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (!customer.equals(that.customer)) return false;
        if (!room.equals(that.room)) return false;
        if (!begin.equals(that.begin)) return false;
        return end.equals(that.end);
        */
        return this == o;
    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + begin.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
