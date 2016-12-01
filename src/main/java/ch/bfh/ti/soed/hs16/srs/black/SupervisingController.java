/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.black;

import javax.servlet.annotation.WebServlet;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.backend.loginView.LoginController;
import ch.bfh.ti.soed.hs16.srs.black.backend.loginView.LoginView;
import ch.bfh.ti.soed.hs16.srs.black.backend.reservationView.ReservationController;
import ch.bfh.ti.soed.hs16.srs.black.backend.reservationView.ReservationView;
import ch.bfh.ti.soed.hs16.srs.black.backend.signUpView.SignUpController;
import ch.bfh.ti.soed.hs16.srs.black.backend.signUpView.SignUpView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@PreserveOnRefresh  // Preserve the session when the site gets reloaded
public class SupervisingController extends UI {

    private Navigator navigator;
    private DataModel dataModel;
    private LoginView loginView;
    private ReservationView reservationView;
    private SignUpView signUpView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Set Title for Tab of Browser
        Page.getCurrent().setTitle("SRS - Smart Reservation System");
        dataModel = new DataModel();
        loginView = new LoginView();
        reservationView = new ReservationView();
        signUpView = new SignUpView();
        navigator = new Navigator(this, this);

        // Adding the login backend to the navigator
        navigator.addView(LoginView.NAME, loginView);

        // Setting the error backend of the navigator to the login backend
        // This way the navigator will always default to the login backend
        navigator.setErrorView(loginView);

        // Adding the reservation backend to the navigator
        navigator.addView(ReservationView.NAME, reservationView);

        // Adding the sign up backend to the navigator
        navigator.addView(SignUpView.NAME, signUpView);

        // Instantiating the controllers for the two views
        new LoginController(dataModel, loginView, navigator);
        new ReservationController(dataModel, reservationView, navigator);
        new SignUpController(dataModel, signUpView, navigator);

        // We use a backend change handler to ensure the user is always redirected
        // to the login backend if the user is not logged in
        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                // Check if a user has logged in
                boolean isLoggedIn = VaadinSession.getCurrent().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof LoginView;
/*
                if (!isLoggedIn && !isLoginView) {
                    // Always redirect to login backend if a user has not yet logged in
                    //navigator.navigateTo(LoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // Access attempt to the login backend while already logged in gets cancelled
                    return false;
                }*/
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                // placeholder
            }
        });
    }


    @WebServlet(urlPatterns = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = SupervisingController.class, productionMode = false)
    public static class Servlet extends VaadinServlet {
    }
}
