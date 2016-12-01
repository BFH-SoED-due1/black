/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.reservationView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.JPADataAccess;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.Navigator;
import java.util.Date;


public class ReservationController {

    private static final Logger LOG = LoggerFactory.getLogger(ReservationController.class);

    private DataModel dataModel;
    private ReservationView reservationView;
    private Navigator navigator;

    public ReservationController(DataModel dataModel, ReservationView reservationView, Navigator navigator) {
        this.dataModel = dataModel;
        this.reservationView = reservationView;
        this.navigator = navigator;

        reservationView.getLogoutButton().addClickListener(clickEvent -> {
            navigator.navigateTo("");
        });

        reservationView.getMakeButton().addClickListener(clickEvent -> {
            Date begin = reservationView.getFromField().getValue();
            Date end = reservationView.getToField().getValue();
            int roomNumber = Integer.parseInt(reservationView.getRoomNumberField().getValue());
            String username = "user1"; // this will be replaced with user of current session
            try {
                dataModel.addReservation(username, roomNumber, begin, end);
            } catch (Exception e) {
                LOG.error("", e);
            }
        });
    }
}
