/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black.view.loginView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.Customer;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationView;
import ch.bfh.ti.soed.hs16.srs.black.view.signUpView.SignUpView;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import javax.naming.AuthenticationException;


public class LoginController {

    private DataModel dataModel;
    private LoginView loginView;
    private Navigator navigator;

    public LoginController(DataModel dataModel, LoginView loginView, Navigator navigator) {
        this.dataModel = dataModel;
        this.loginView = loginView;
        this.navigator = navigator;

        loginView.getLoginButton().addClickListener(this::login);
        loginView.getLoginButton().setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginView.getSignUpButton().addClickListener(this::signUp);
    }

    public void login(Button.ClickEvent event) {
        String userName = loginView.getUsernameField().getValue();
        String password = loginView.getPasswordField().getValue();

        try {
            Customer customer = dataModel.getCustomer(userName);
            if (customer.getPassword().equals(password)) {
                // Store the current user in the service session
                VaadinSession.getCurrent().setAttribute("user", userName);

                // Navigate to the reservation view
                navigator.navigateTo(ReservationView.NAME);

                // Clear the fields of the login view
                loginView.getUsernameField().clear();
                loginView.getPasswordField().clear();
            } else {
                throw new AuthenticationException();
            }
        } catch (Exception e) {
            Notification accessDeniedNf = new Notification("Access Denied!",
                    "Please enter a valid username/password combination.");
            accessDeniedNf.show(Page.getCurrent());
            accessDeniedNf.setDelayMsec(2000);
            loginView.getPasswordField().clear();
            loginView.getPasswordField().focus();
        }
    }

    public void signUp(Button.ClickEvent event){
        navigator.navigateTo(SignUpView.NAME);
    }
}
