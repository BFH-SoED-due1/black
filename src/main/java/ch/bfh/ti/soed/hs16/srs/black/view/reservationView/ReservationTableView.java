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
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;


public class ReservationTableView extends CustomComponent implements View {

    private Table table;
    private VerticalLayout listReservations;

    public ReservationTableView() {
        Panel panel = new Panel("My Reservations");
        table = new Table();
        table.addContainerProperty("Room", Integer.class, null);
        table.addContainerProperty("Start Time", Date.class, null);
        table.addContainerProperty("End Time", Date.class, null);
        table.addContainerProperty("Cancel", Button.class, null);
        table.setPageLength(table.size());
        panel.setContent(table);

        listReservations = new VerticalLayout(panel);
        listReservations.setSizeUndefined();
        listReservations.setMargin(true);
    }

    public Table getTable() {
        return table;
    }

    public VerticalLayout getListReservations() {
        return listReservations;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // placeholder
    }
}
