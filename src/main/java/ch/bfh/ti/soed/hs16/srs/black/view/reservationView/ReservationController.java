/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.reservationView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Room;
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
            navigator.navigateTo(LoginView.NAME);
        });

        reservationView.getMakeReservationButton().addClickListener(clickEvent -> {
            Date begin = reservationView.getFromField().getValue();
            Date end = reservationView.getToField().getValue();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            Notification exceptionNf = new Notification("","");
            exceptionNf.setDelayMsec(2000);
            int roomNumber = Integer.parseInt(reservationView.getRoomNumberField().getValue());
            String username = String.valueOf(VaadinSession.getCurrent().getAttribute("user"));
            try {
                Customer customer = dataModel.getCustomer(username);
                Room room = dataModel.getRoom(roomNumber);
                dataModel.addReservation(customer, room, begin, end);
                exceptionNf.setCaption("Success");
                exceptionNf.setDescription("Added reservation for: " + username +
                        ", Room Nr.: " + roomNumber +
                        ", From: " + df.format(begin) +
                        " until " + df.format(end));
                exceptionNf.show(Page.getCurrent());
            } catch (IllegalArgumentException iae) {
                exceptionNf.setCaption("Illegal Argument Exception");
                exceptionNf.setDescription("Please check the dates you set for your reservation.");
                exceptionNf.show(Page.getCurrent());
            } catch(NoResultException nre) {
                exceptionNf.setCaption("No Result Exeption");
                exceptionNf.setDescription("Room not found in database.");
                exceptionNf.show(Page.getCurrent());
            } catch (Exception e) {
                exceptionNf.setCaption("Time Collision Exception");
                exceptionNf.setDescription("There already exists a reservation for the chosen time range.");
                exceptionNf.show(Page.getCurrent());
            }
        });
    }
}
