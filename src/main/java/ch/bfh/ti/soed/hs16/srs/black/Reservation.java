package ch.bfh.ti.soed.hs16.srs.black;

import java.util.Date;

public class Reservation implements Comparable<Reservation>{
    private final Customer customer;
    private final Room room;
    private final Date begin;
    private final Date end;
    // price class

    public Reservation(Customer customer, Room room, Date begin, Date end) {
        this.customer = customer;
        this.room = room;
        this.begin = begin;
        this.end = end;
        customer.addReservation(this);
        room.addReservation(this);
    }

    public void cancelReservation(){
        customer.removeReservation(this);
        room.removeReservation(this);
    }

    @Override
    public int compareTo(Reservation o) {
        if (begin.before(o.begin))
            return -1;
        if (begin.after(o.begin))
            return 1;
        return 0;
    }
}
