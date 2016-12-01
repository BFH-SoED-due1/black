package ch.bfh.ti.soed.hs16.srs.black.backend.signUpView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.backend.loginView.LoginView;
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

    public void addUser(Button.ClickEvent event){
        String username = signUpView.getUsernameField().getValue();
        String password = signUpView.getPasswordField().getValue();
        String passwordRepeat = signUpView.getPasswordFieldRepeat().getValue();

        if (username.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()){
            new Notification("Please fill out the complete form!").show(Page.getCurrent());
        } else if (!password.equals(passwordRepeat)){
            new Notification("The given passwords aren't identical!").show(Page.getCurrent());
            signUpView.getPasswordFieldRepeat().clear();
            signUpView.getPasswordFieldRepeat().focus();
        } else if (true) {
            // Check if User is already in database
            new Notification("User already exists!").show(Page.getCurrent());
        } else {
            // Add New User-Credentials to Database
        }
    }

    public void goBack(Button.ClickEvent event){
        signUpView.getUsernameField().clear();
        signUpView.getPasswordField().clear();
        signUpView.getPasswordFieldRepeat().clear();
        navigator.navigateTo(LoginView.NAME);
    }
}
