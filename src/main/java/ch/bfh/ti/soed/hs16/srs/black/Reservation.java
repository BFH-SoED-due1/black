package ch.bfh.ti.soed.hs16.srs.black;

public class Reservation implements Comparable<Reservation>{
    private final Customer customer;
    private final Room room;
    private final long startTime;
    private final long endTime;
    // price class

    public Reservation(Customer customer, Room room, long startTime, long endTime){
        customer.addReservation(this);
        room.addReservation(this);
        this.customer = customer;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void cancelReservation(){
        customer.removeReservation(this);
        room.removeReservation(this);
    }

    @Override
    public int compareTo(Reservation o) {
        if (startTime == o.startTime)
            return 0;
        if (startTime - o.startTime > 0)
            return 1;
        return -1;
    }
}
