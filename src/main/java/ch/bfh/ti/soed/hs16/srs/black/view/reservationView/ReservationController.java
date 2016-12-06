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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;


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
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Notification exceptionNf = new Notification("");
            exceptionNf.setDelayMsec(2000);
            String username = String.valueOf(VaadinSession.getCurrent().getAttribute("user"));

            try {
                int roomNumber = Integer.parseInt(reservationView.getRoomNumberField().getValue());
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
                exceptionNf.setCaption("Error!");
                exceptionNf.setDescription("Please check the entries you made for your reservation.");
                exceptionNf.show(Page.getCurrent());
            } catch(RuntimeException re) {
                exceptionNf.setCaption("Error!");
                exceptionNf.setDescription("Room couldn't be found.\nCurrently available rooms: " +
                        dataModel.getRooms().stream().map(Object::toString)
                                .collect(Collectors.joining(", "))
                                .replaceAll("[^\\d , ][^\\@]*\\@", ""));
                exceptionNf.show(Page.getCurrent());
            } catch (Exception e) {
                exceptionNf.setCaption("Error!");
                exceptionNf.setDescription("There already exists a reservation for the chosen time range.");
                exceptionNf.show(Page.getCurrent());
            }
        });
    }
}
