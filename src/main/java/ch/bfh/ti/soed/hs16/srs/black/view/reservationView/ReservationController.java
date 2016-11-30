/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.reservationView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginView.NAME;


public class ReservationController {

    private DataModel dataModel;
    private ReservationView reservationView;
    private Navigator navigator;

    public ReservationController(DataModel dataModel, ReservationView reservationView, Navigator navigator) {
        this.dataModel = dataModel;
        this.reservationView = reservationView;
        this.navigator = navigator;

        reservationView.getLogoutButton().addClickListener(clickEvent -> {
            // Logout the user / end the session
            VaadinSession.getCurrent().setAttribute("user", null);

            // Refresh this view, the navigator should redirect to login view
            navigator.navigateTo(NAME);
        });

        reservationView.getMakeReservationButton().addClickListener(clickEvent -> {
            Date begin = reservationView.getFromField().getValue();
            Date end = reservationView.getToField().getValue();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            int roomNumber = Integer.parseInt(reservationView.getRoomNumberField().getValue());
            String username = String.valueOf(VaadinSession.getCurrent().getAttribute("user"));
            try {
                dataModel.addReservation(username, roomNumber, begin, end);
                new Notification("Success",
                        "Added reservation for: " + username +
                                ", Room Nr.: " + roomNumber +
                                ", From: " + df.format(begin) +
                                " until " + df.format(end))
                        .show(Page.getCurrent());
            } catch (IllegalArgumentException iae) {
                new Notification("Illegal Argument Exception",
                        "Please check the dates you set for your reservation.")
                        .show(Page.getCurrent());
            } catch (Exception e) {
                new Notification("Time Collision Exception",
                        "There already exists a reservation for the chosen time range.")
                        .show(Page.getCurrent());
            }
        });
    }
}
