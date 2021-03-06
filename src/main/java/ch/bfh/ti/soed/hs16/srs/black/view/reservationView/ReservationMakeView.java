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
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.Locale;


public class ReservationMakeView extends CustomComponent implements View {

    private DateField fromField;
    private DateField toField;
    private NativeSelect roomSelect;
    private Button makeReservationButton;
    private Button logoutButton;
    private VerticalLayout makeViewLayout;

    public ReservationMakeView() {
        fromField = new DateField("Start Date");
        fromField.setLocale(new Locale("de", "DE"));
        fromField.setResolution(Resolution.MINUTE);
        fromField.setDateFormat("dd.MM.yyyy HH:mm");
        fromField.setIcon(FontAwesome.CALENDAR);
        fromField.setWidth(12, Unit.EM);
        toField = new DateField("End Date");
        toField.setLocale(new Locale("de", "DE"));
        toField.setResolution(Resolution.MINUTE);
        toField.setDateFormat("dd.MM.yyyy HH:mm");
        toField.setIcon(FontAwesome.CALENDAR);
        toField.setWidth(12, Unit.EM);
        roomSelect = new NativeSelect("Room Number");
        roomSelect.setIcon(FontAwesome.BED);
        roomSelect.setNullSelectionAllowed(true);
        roomSelect.setImmediate(true);
        makeReservationButton = new Button("Make Reservation", FontAwesome.CHECK);
        makeReservationButton.setWidth(12, Unit.EM);
        logoutButton = new Button("Logout");

        Panel panel = new Panel("Create New Reservation");
        FormLayout content = new FormLayout();
        content.addComponents(fromField, toField, roomSelect, makeReservationButton);
        content.setSizeUndefined();
        content.setMargin(true);
        VerticalLayout formAndLogout = new VerticalLayout(content, logoutButton);
        formAndLogout.setMargin(true);
        panel.setContent(formAndLogout);
        makeViewLayout = new VerticalLayout(panel);
        makeViewLayout.setSizeUndefined();
        makeViewLayout.setMargin(true);
    }

    public VerticalLayout getMakeViewLayout() {
        return makeViewLayout;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getMakeReservationButton() {
        return makeReservationButton;
    }

    public DateField getFromField() {
        return fromField;
    }

    public DateField getToField() {
        return toField;
    }

    public NativeSelect getRoomSelect() {
        return roomSelect;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // placeholder
    }
}
