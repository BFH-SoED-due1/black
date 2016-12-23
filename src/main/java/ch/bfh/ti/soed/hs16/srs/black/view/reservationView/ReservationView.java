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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;


public class ReservationView extends VerticalLayout implements View {

    public static final String NAME = "reservation";
    private ReservationMakeView reservationMakeView;
    private ReservationTableView reservationTableView;

    public ReservationView() {
        reservationMakeView = new ReservationMakeView();
        reservationTableView = new ReservationTableView();

        VerticalLayout makeViewLayoutVL = reservationMakeView.getMakeViewLayout();
        VerticalLayout listReservationsVL = reservationTableView.getListReservations();

        GridLayout grid = new GridLayout(2, 1);
        grid.addComponent(makeViewLayoutVL, 0, 0);
        grid.addComponent(listReservationsVL, 1, 0);
        grid.setComponentAlignment(makeViewLayoutVL, Alignment.TOP_CENTER);
        grid.setComponentAlignment(listReservationsVL, Alignment.TOP_CENTER);
        grid.setSizeUndefined();

        setSizeFull();
        addComponent(grid);
        setComponentAlignment(grid, Alignment.TOP_CENTER);
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

    public NativeSelect getRoomSelect() {
        return reservationMakeView.getRoomSelect();
    }

    public Table getReservationList(){
        return reservationTableView.getTable();
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // placeholder
    }
}
