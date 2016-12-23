/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.signUpView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class SignUpView extends CustomComponent implements View {

    public static final String NAME = "signup";
    private TextField usernameField;
    private PasswordField passwordField;
    private PasswordField passwordFieldRepeat;
    private Button addUserButton;
    private Button goBackButton;

    public SignUpView() {
        usernameField = new TextField("Username");
        usernameField.setIcon(FontAwesome.USER);
        usernameField.setWidth(12, Unit.EM);
        passwordField = new PasswordField("Password");
        passwordField.setIcon(FontAwesome.KEY);
        passwordField.setWidth(12, Unit.EM);
        passwordFieldRepeat = new PasswordField("Repeat Password");
        passwordFieldRepeat.setIcon(FontAwesome.KEY);
        passwordFieldRepeat.setWidth(12, Unit.EM);
        addUserButton = new Button("Add New User");
        addUserButton.setWidth(12, Unit.EM);
        goBackButton = new Button("Back");

        VerticalLayout layout = new VerticalLayout();
        Panel panel = new Panel("Sign Up - Smart ReservationEntity System");
        panel.setSizeUndefined();
        layout.addComponent(panel);

        FormLayout content = new FormLayout();
        content.addComponents(usernameField, passwordField, passwordFieldRepeat, addUserButton);
        content.setSizeUndefined();
        content.setMargin(true);
        VerticalLayout formAndBack = new VerticalLayout(content, goBackButton);
        formAndBack.setMargin(true);
        panel.setContent(formAndBack);

        setCompositionRoot(layout);

        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        layout.setMargin(new MarginInfo(true, false, false, false));
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public PasswordField getPasswordFieldRepeat() {
        return passwordFieldRepeat;
    }

    public Button getAddUserButton() {
        return addUserButton;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        usernameField.focus();
    }
}
