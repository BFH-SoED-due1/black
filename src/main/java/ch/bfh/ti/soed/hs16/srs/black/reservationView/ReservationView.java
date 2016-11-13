/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.reservationView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Alignment;


public class ReservationView extends CustomComponent implements View {
    private DateField fromField;
    private DateField toField;
    private TextField roomNumberField;
    private Button makeButton;
    private Button logoutButton;

    public ReservationView() {
        fromField = new DateField("Reservation from");
        toField = new DateField("Reservation to");
        roomNumberField = new TextField("Room Number");
        makeButton = new Button("Make reservation");
        logoutButton = new Button("Logout");

        VerticalLayout layout = new VerticalLayout();

        FormLayout content = new FormLayout();
        content.addComponents(fromField, toField, roomNumberField, makeButton, logoutButton);
        content.setSizeUndefined();
        content.setMargin(true);
        layout.addComponent(content);
        setCompositionRoot(layout);

        layout.setSizeFull();
        layout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getMakeButton() {
        return makeButton;
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

    }
}




