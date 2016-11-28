package ch.bfh.ti.soed.hs16.srs.black.model;

import java.util.Date;

/**
 * Created by davide on 28/11/16.
 */
public abstract class DataModel {
    public static final String DEFAULT_DATA_ACCESS_CLASS = "ch.bfh.ti.soed.hs16.srs.black.model.JPADataAccess";

    private static DataModel instance = null;

    public static DataModel getInstance() {
        // The following is NOT thread safe:
        if (instance == null) {
            try {
                @SuppressWarnings("rawtypes")
                Class clazz = Class.forName(DEFAULT_DATA_ACCESS_CLASS);
                instance = (DataModel) clazz.newInstance();
            } catch (Exception ex) {
                System.err.println("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
                throw new RuntimeException("Could not load class: " + DEFAULT_DATA_ACCESS_CLASS);
            }
        }
        return instance;
    }
    public abstract void addReservation(String userName, int roomNumber, Date begin, Date end) throws Exception;
    public abstract boolean customerExists(String customerName);
    public abstract String getPassword(String customerName);
    }
