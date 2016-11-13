/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.loginView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class LoginView extends CustomComponent implements View {

    private Label errorLbl;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView() {

        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        loginButton = new Button("Login");
        errorLbl = new Label("");

        VerticalLayout layout = new VerticalLayout();
        Panel panel = new Panel("Smart Reservation System Login");
        panel.setSizeUndefined();
        layout.addComponent(panel);

        FormLayout content = new FormLayout();
        content.addComponents(usernameField, passwordField, loginButton, errorLbl);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);

        setCompositionRoot(layout);

        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        layout.setMargin(new MarginInfo(true, false, false, false));
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getErrorLbl() {
        return errorLbl;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
