/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.loginView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class LoginView extends CustomComponent implements View {

    public static final String NAME = "login";
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button signUpButton;

    public LoginView() {
        usernameField = new TextField("Username");
        usernameField.setIcon(FontAwesome.USER);
        usernameField.setWidth(12, Unit.EM);
        passwordField = new PasswordField("Password");
        passwordField.setIcon(FontAwesome.KEY);
        passwordField.setWidth(12, Unit.EM);
        loginButton = new Button("Login");
        loginButton.setWidth(5, Unit.EM);
        signUpButton = new Button("Sign Up");
        signUpButton.setWidth(6, Unit.EM);

        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout layoutButtons = new HorizontalLayout(loginButton, signUpButton);
        layoutButtons.setSpacing(true);
        Panel panel = new Panel("Login - Smart Reservation System");
        panel.setSizeUndefined();
        layout.addComponent(panel);

        FormLayout content = new FormLayout();
        content.addComponents(usernameField, passwordField, layoutButtons);
        content.setSizeFull();
        content.setMargin(true);
        panel.setContent(content);

        setCompositionRoot(layout);

        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        layout.setMargin(new MarginInfo(true, false, false, false));
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getSignUpButton() {
        return signUpButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // focus the username field when user arrives on the login view
        usernameField.focus();
    }
}
