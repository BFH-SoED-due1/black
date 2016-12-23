/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.reservationView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.black.model.Room;
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginView;
import com.vaadin.data.Item;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class ReservationController {

    private DataModel dataModel;
    private ReservationView reservationView;
    private Navigator navigator;
    private int roomNumber;

    public ReservationController(DataModel dataModel, ReservationView reservationView, Navigator navigator) {
        this.dataModel = dataModel;
        this.reservationView = reservationView;
        this.navigator = navigator;

        reservationView.getLogoutButton().addClickListener(clickEvent -> {
            // Logout the user / end the session
            VaadinSession.getCurrent().setAttribute("user", null);

            // Refresh this view, the navigator should redirect to login view$
            navigator.navigateTo(LoginView.NAME);
        });

        reservationView.getRoomSelect().addValueChangeListener(e ->
                roomNumber = Integer.parseInt(e.getProperty().getValue().toString()));

        for (int i = 1; i < dataModel.getRooms().size() + 1; i++) {
            reservationView.getRoomSelect().addItem(i);
            reservationView.getRoomSelect().setItemCaption(i, "Room " + i);
        }

        reservationView.getMakeReservationButton().addClickListener(clickEvent -> {
            Date begin = reservationView.getFromField().getValue();
            Date end = reservationView.getToField().getValue();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Notification notification = new Notification("");
            notification.setDelayMsec(2000);
            String username = String.valueOf(VaadinSession.getCurrent().getAttribute("user"));

            try {
                Customer customer = dataModel.getCustomer(username);
                Room room = dataModel.getRoom(roomNumber);
                dataModel.addReservation(customer, room, begin, end);
                notification.setCaption("Success");
                notification.setDescription("Added reservation for: " + username +
                        ", Room Nr.: " + roomNumber +
                        ", From: " + df.format(begin) +
                        " until " + df.format(end));
                notification.show(Page.getCurrent());
                try {
                    createList(username);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IllegalArgumentException iae) {
                notification.setCaption("Error!");
                notification.setDescription("Please check the entries you made for your reservation.");
                notification.show(Page.getCurrent());
            } catch (RuntimeException re) {
                notification.setCaption("Error!");
                notification.setDescription("Room couldn't be found.\nCurrently available rooms: " +
                        dataModel.getRooms().stream().map(Object::toString)
                                .collect(Collectors.joining(", "))
                                .replaceAll("[^\\d , ][^\\@]*\\@", ""));
                notification.show(Page.getCurrent());
            } catch (Exception e) {
                notification.setCaption("Error!");
                notification.setDescription("There already exists a reservation for the chosen time range.");
                notification.show(Page.getCurrent());
            }
        });

        reservationView.getLogoutButton().addClickListener(clickEvent -> {
            reservationView.getFromField().clear();
            reservationView.getToField().clear();
            reservationView.getRoomSelect().clear();
        });
    }

    public void createList(String customer) throws Exception {
        reservationView.getReservationList().removeAllItems();
        List<Reservation> reservationList = dataModel.getReservations(dataModel.getCustomer(customer));

        for (Reservation reservation : reservationList) {
            Button button = new Button("X");
            Object newItemId = reservationView.getReservationList().addItem();

            button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    dataModel.cancelReservation(reservation);
                    reservationView.getReservationList().removeItem(newItemId);
                }
            });

            Item row1 = reservationView.getReservationList().getItem(newItemId);
            row1.getItemProperty("Room").setValue((Integer) reservation.getRoom().getRoomNr());
            row1.getItemProperty("Start Time").setValue(reservation.getBegin());
            row1.getItemProperty("End Time").setValue(reservation.getEnd());
            row1.getItemProperty("Cancel").setValue(button);
        }
    }
}
