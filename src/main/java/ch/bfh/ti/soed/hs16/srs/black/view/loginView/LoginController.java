/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.loginView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;


public class LoginController extends CustomComponent {

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
            // Store the current user in the service session
            VaadinSession.getCurrent().setAttribute("user", userName);

            // Navigate to the reservation view
            navigator.navigateTo(ReservationView.NAME);

            // Clear the fields of the Login View
            loginView.getUsernameField().clear();
            loginView.getPasswordField().clear();
        } else {
            new Notification("Access Denied!",
                    "Please enter a valid username/password combination.")
                        .show(Page.getCurrent());
            loginView.getPasswordField().clear();
            loginView.getPasswordField().focus();
        }
    }
}
