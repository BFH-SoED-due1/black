package ch.bfh.ti.soed.hs16.srs.black.view.signUpView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.model.logic.Customer;
import ch.bfh.ti.soed.hs16.srs.black.view.loginView.LoginView;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;


public class SignUpController {

    private DataModel dataModel;
    private SignUpView signUpView;
    private Navigator navigator;

    public SignUpController(DataModel dataModel, SignUpView signUpView, Navigator navigator) {
        this.dataModel = dataModel;
        this.signUpView = signUpView;
        this.navigator = navigator;

        signUpView.getAddUserButton().addClickListener(this::addUser);
        signUpView.getGoBackButton().addClickListener(this::goBack);
    }

    public void addUser(Button.ClickEvent event) {
        String username = signUpView.getUsernameField().getValue();
        String password = signUpView.getPasswordField().getValue();
        String passwordRepeat = signUpView.getPasswordFieldRepeat().getValue();
        Notification alertNf = new Notification("");
        alertNf.setDelayMsec(2000);

        if (username.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {
            alertNf.setCaption("Please fill out the complete form!");
            alertNf.show(Page.getCurrent());
        } else if (!password.equals(passwordRepeat)) {
            alertNf.setCaption("The given passwords aren't identical!");
            alertNf.show(Page.getCurrent());
            signUpView.getPasswordFieldRepeat().clear();
            signUpView.getPasswordFieldRepeat().focus();
        } else {
            try {
                dataModel.addCustomer(new Customer(username, password));
                navigator.navigateTo(ReservationView.NAME);
                alertNf.setCaption("Successfully added new User: " + username);
                alertNf.show(Page.getCurrent());
            } catch (IllegalStateException e) {
                alertNf.setCaption("User already exists!");
                alertNf.show(Page.getCurrent());
                signUpView.getUsernameField().focus();
            } finally {
                signUpView.getUsernameField().clear();
                signUpView.getPasswordField().clear();
                signUpView.getPasswordFieldRepeat().clear();
            }
        }
    }

    public void goBack(Button.ClickEvent event) {
        signUpView.getUsernameField().clear();
        signUpView.getPasswordField().clear();
        signUpView.getPasswordFieldRepeat().clear();
        navigator.navigateTo(LoginView.NAME);
    }
}
