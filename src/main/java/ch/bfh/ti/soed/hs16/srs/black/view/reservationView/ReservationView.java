/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.reservationView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;


public class ReservationView extends CustomComponent implements View {

    public static final String NAME = "reservation";
    private ReservationMakeView reservationMakeView;
    private ReservationTableView reservationTableView;

    public ReservationView() {
        reservationMakeView = new ReservationMakeView();
        reservationTableView = new ReservationTableView();


        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(reservationMakeView.getFormAndLogout());
        layout.addComponent(reservationTableView.getListReservations());


        setCompositionRoot(layout);


    }

    public Button getLogoutButton() {
        return reservationMakeView.getLogoutButton();
    }

    public Button getMakeReservationButton() {
        return reservationMakeView.getMakeReservationButton();
    }

    public DateField getFromField() {
        return reservationMakeView.getFromField();
    }

    public DateField getToField() {
        return reservationMakeView.getToField();
    }

    public TextField getRoomNumberField() {
        return reservationMakeView.getRoomNumberField();
    }

    public Table getReservationList(){
        return reservationTableView.getTable();
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // placeholder
    }
}
