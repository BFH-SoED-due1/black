/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.reservationView;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Locale;


public class ReservationView extends CustomComponent implements View {

    public static final String NAME = "reservation";
    private DateField fromField;
    private DateField toField;
    private TextField roomNumberField;
    private Button makeReservationButton;
    private Button logoutButton;

    public ReservationView() {
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
        roomNumberField = new TextField("Room Number");
        roomNumberField.setIcon(FontAwesome.BUILDING_O);
        roomNumberField.addValidator(new RegexpValidator("^[0-9]{1,3}$", "Please enter a valid room number."));
        roomNumberField.setWidth(4, Unit.EM);
        makeReservationButton = new Button("Make Reservation");
        makeReservationButton.setWidth(12, Unit.EM);
        logoutButton = new Button("Logout");

        VerticalLayout layout = new VerticalLayout();
        Panel panel = new Panel("Reservation - Smart Reservation System");
        panel.setSizeUndefined();
        layout.addComponent(panel);

        FormLayout content = new FormLayout();
        content.addComponents(fromField, toField, roomNumberField, makeReservationButton);
        content.setSizeUndefined();
        content.setMargin(true);
        VerticalLayout formAndLogout = new VerticalLayout(content, logoutButton);
        formAndLogout.setMargin(true);
        panel.setContent(formAndLogout);

        setCompositionRoot(layout);

        layout.setSizeFull();
        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        layout.setMargin(new MarginInfo(true, false, false, false));
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

    public TextField getRoomNumberField() {
        return roomNumberField;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // placeholder
    }
}
