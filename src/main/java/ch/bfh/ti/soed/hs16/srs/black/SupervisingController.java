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
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginController;
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginView;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationController;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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
public class SupervisingController extends UI {

    //Adding new views here and in init() method
    //private static final String NAMEOFVIEW = "nameofview";
    private static final String RESERVATION_VIEW = "reservation";
    private Navigator navigator;
    private DataModel dataModel;
    private LoginView loginView;
    private ReservationView reservationView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        dataModel = new DataModel();
        loginView = new LoginView();
        reservationView = new ReservationView();
        navigator = new Navigator(this, this);
        navigator.addView("", loginView);
        navigator.addView(RESERVATION_VIEW, reservationView);
        new LoginController(dataModel, loginView, navigator);
        new ReservationController(dataModel, reservationView, navigator);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SupervisingController.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
