/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.loginView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;


public class LoginController {

    private DataModel dataModel;
    private LoginView loginView;
    private Navigator navigator;

    public LoginController(DataModel dataModel, LoginView loginView, Navigator navigator) {
        this.dataModel = dataModel;
        this.loginView = loginView;
        this.navigator = navigator;

        loginView.getLoginButton().addClickListener(this::login);
    }

    public void login(Button.ClickEvent event) {
        String userName = loginView.getUsernameField().getValue();
        String password = loginView.getPasswordField().getValue();
        if (dataModel.customerExists(userName) && dataModel.getPassword(userName).equals(password)) {
            loginView.getErrorLbl().setValue("");
            loginView.getUsernameField().clear();
            loginView.getPasswordField().clear();
            navigator.navigateTo("reservation");
        } else {
            loginView.getErrorLbl().setValue("Access denied!");
        }
    }
}
