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
import ch.bfh.ti.soed.hs16.srs.black.model.JPADataAccess;
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginController;
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginView;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationController;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationView;
import ch.bfh.ti.soed.hs16.srs.black.view.signUpView.SignUpController;
import ch.bfh.ti.soed.hs16.srs.black.view.signUpView.SignUpView;
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
    private ReservationController reservationController;

    // Create Room and Customer for test purposes
    // Commented out because this data is now stored in the SQLite DB
    /*static {
        DataModel testData = JPADataAccess.getInstance();
        Room room1 = new Room(1,"20m^2");
        Room room2 = new Room(2,"20m^2");
        Customer customer1 = new Customer("user1", "123");
        Customer customer2 = new Customer("user2", "234");

        testData.addRoom(room1);
        testData.addRoom(room2);
        testData.addCustomer(customer1);
        testData.addCustomer(customer2);
    }*/

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Set the title of the site in the browser
        Page.getCurrent().setTitle("SRS - Smart Reservation System");

        dataModel = JPADataAccess.getInstance();
        loginView = new LoginView();
        reservationView = new ReservationView();
        signUpView = new SignUpView();
        navigator = new Navigator(this, this);

        // Adding the login view to the navigator
        navigator.addView(LoginView.NAME, loginView);

        // Setting the error view of the navigator to the login view
        // This way the navigator will always default to the login view
        navigator.setErrorView(loginView);

        // Adding the reservation view to the navigator
        navigator.addView(ReservationView.NAME, reservationView);

        // Adding the sign up view to the navigator
        navigator.addView(SignUpView.NAME, signUpView);

        // Instantiating the controllers for the two views
        reservationController = new ReservationController(dataModel, reservationView, navigator);
        new LoginController(dataModel, loginView, navigator);
        new SignUpController(dataModel, signUpView, navigator);

        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in
        navigator.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                // Check if a user has logged in
                boolean isLoggedIn = VaadinSession.getCurrent().getAttribute("user") != null;

                boolean isLoginView = event.getNewView() instanceof LoginView;
                boolean isSignUpView = event.getNewView() instanceof SignUpView;
                boolean isReservationView = event.getNewView() instanceof ReservationView;

                if (!isLoggedIn && isReservationView) {
                    // Always redirect to login view if a user has not yet logged in
                    navigator.navigateTo(LoginView.NAME);
                    return false;
                } else if (isLoggedIn && (isLoginView || isSignUpView)) {
                    // Access attempt to the login or signup view while already logged in gets cancelled
                    return false;
                }
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                try {
                    reservationController.createList((String) VaadinSession.getCurrent().getAttribute("user"));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @WebServlet(urlPatterns = "/*", asyncSupported = true)
    @VaadinServletConfiguration(ui = SupervisingController.class, productionMode = false, widgetset = "com.vaadin.DefaultWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
}
