/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.ti.soed.hs16.srs.black.model;

import ch.bfh.ti.soed.hs16.srs.black.model.logic.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Room;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;


public class JPADataAccess extends DataModel {
    private static final String PERSISTENCE_UNIT = "black";
    private static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.black.model.JPADataAccess";
    private static EntityManager entityManager;


    private JPADataAccess(){} // to create an instance of this class, you have to use getInstance()

    public static DataModel getInstance(){
        synchronized (DataModel.class) {
            if (instance == null) {
                try {
                    @SuppressWarnings("rawtypes")
                    Class clazz = Class.forName(DEFAULT_DATA_ACCESS_CLASS);
                    instance = (DataModel) clazz.newInstance();
                } catch (Exception ex) {
                    System.err.println("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);

                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
                }
                entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
            }
            return instance;
        }
    }

    @Override
    public void addReservation(String userName, int roomNumber, Date begin, Date end) throws Exception {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try {
            Customer customer = getCustomer(userName);
            Room room = getRoom(roomNumber);
            Reservation reservation = new Reservation(customer, room, begin, end);
            entityManager.persist(reservation);
            tx.commit();
        }
        catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void cancelReservation() {
        // TODO ...
    }

    @Override
    public void addCustomer(String customerName, String password) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Customer customer = new Customer(customerName, password);
            entityManager.persist(customer);
            tx.commit();
        }
        catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void removeCustomer(String customerName) {
        // TODO ...
    }

    @Override
    public boolean customerExists(String customerName) {
        return entityManager.createQuery("select count(o) from Customer as o where o.name = :customerName", Long.class).setParameter("customerName", customerName).getSingleResult() >= 1l;
    }

    @Override
    public String getPassword(String customerName) {
        return entityManager.createQuery("select o from Customer as o where o.name = :customerName", Customer.class).setParameter("customerName", customerName).getSingleResult().getPassword();
    }

    @Override
    public void addRoom(int roomNr, String description) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Room room = new Room(roomNr, description);
            entityManager.persist(room);
            tx.commit();
        }
        catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    @Override
    public void removeRoom() {
        // TODO ...
    }


    private Customer getCustomer(String customerName) {
        return entityManager.createQuery("select o from Customer as o where o.name = :customerName", Customer.class).setParameter("customerName", customerName).getSingleResult();
    }

    private Room getRoom(int roomNr) {
        return entityManager.createQuery("select o from Room as o where o.roomNr = :roomNr", Room.class).setParameter("roomNr", roomNr).getSingleResult();
    }
}
