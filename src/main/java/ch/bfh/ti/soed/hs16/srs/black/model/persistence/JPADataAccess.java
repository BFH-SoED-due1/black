/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.model.persistence;

import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JPADataAccess extends DataModel {

    private static final String PERSISTENCE_UNIT = "black";
    private static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.black.model.persistence.JPADataAccess";
    private static EntityManager entityManager;

    private JPADataAccess() {} // to create an instance of this class, you have to use getInstance()

    public static DataModel getInstance() {
        synchronized (DataModel.class) {
            if (instance == null) {
                try {
                    @SuppressWarnings("rawtypes")
                    Class aClass = Class.forName(DEFAULT_DATA_ACCESS_CLASS);
                    instance = (DataModel) aClass.newInstance();
                } catch (Exception ex) {
                    //System.err.println("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);

                    //System.out.println(ex.getMessage());
                    throw new RuntimeException("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
                }
                entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
            }
            return instance;
        }
    }

    @Override
    public Reservation addReservation(Customer customer, Room room, Date begin, Date end) throws Exception {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Reservation reservation = new ReservationEntity(customer, room, begin, end);
            entityManager.persist(reservation);
            tx.commit();
            return reservation;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Reservation reservation1 = entityManager.find(ReservationEntity.class, reservation.getId());
            entityManager.remove(reservation1);
            reservation1.cancelReservation();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Reservation> getReservations(Customer customer) throws NoResultException {
        return new ArrayList<Reservation>(entityManager.createQuery
                ("select r from Reservation as r where r.customer = :customer", ReservationEntity.class)
                .setParameter("customer", customer)
                .getResultList());
    }

    @Override
    public List<Reservation> getReservations(Room room) throws NoResultException {
        return new ArrayList<Reservation>(entityManager.createQuery
                ("select r from Reservation as r where r.room = :room", ReservationEntity.class)
                .setParameter("room", room)
                .getResultList());
    }

    @Override
    public Customer addCustomer(String username, String password) {
        Customer customer = new CustomerEntity(username, password);
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(customer);
            tx.commit();
            return customer;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void removeCustomer(Customer customer) {
        EntityTransaction tx = entityManager.getTransaction();
        Customer tobeRemoved = entityManager.merge(customer);
        try {
            tx.begin();
            entityManager.remove(tobeRemoved);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Customer getCustomer(String customerName) throws NoResultException {
        return entityManager.createQuery("select o from Customer as o where o.name = :customerName", CustomerEntity.class)
                .setParameter("customerName", customerName)
                .getSingleResult();
    }

    /*
    public boolean customerExists(String customerName) throws NoResultException {
        Query query = entityManager.createQuery("select o from CustomerEntity as o where o.name = :customerName", CustomerEntity.class)
                .setParameter("customerName", customerName);
        List results = query.getResultList();
        return !results.isEmpty();
    }
    */

    @Override
    public Room addRoom(int roomNr, String description) {
        Room room = new RoomEntity(roomNr, description);
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(room);
            tx.commit();
            return room;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void removeRoom(Room room) {
        EntityTransaction tx = entityManager.getTransaction();
        Room tobeRemoved = entityManager.merge(room);
        try {
            tx.begin();
            entityManager.remove(tobeRemoved);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Room getRoom(int roomNr) throws NoResultException {
        return entityManager.createQuery("select o from Room as o where o.roomNr = :roomNr", RoomEntity.class)
                .setParameter("roomNr", roomNr)
                .getSingleResult();
    }

    @Override
    public List<Room> getRooms() throws NoResultException {
        return entityManager.createQuery("select r from Room r")
                .getResultList();
    }
}
