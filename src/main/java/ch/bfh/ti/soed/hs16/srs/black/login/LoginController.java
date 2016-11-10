package ch.bfh.ti.soed.hs16.srs.black.login;

import ch.bfh.ti.soed.hs16.srs.black.DataModel;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;


public class LoginController {

    private DataModel dataModel;
    private LoginView loginView;
    private Navigator navigator;

    public LoginController(DataModel dataModel, LoginView loginView, Navigator navigator){
        this.dataModel = dataModel;
        this.loginView = loginView;
        this.navigator = navigator;

        loginView.getLoginButton().addClickListener(this::login);
    }


    public void login(Button.ClickEvent event){
        if (loginView.getUsernameField().getValue().equals(dataModel.getUsername()) && loginView.getPasswordField().getValue().equals(dataModel.getPassword())) {
            loginView.getErrorLbl().setValue("");
            loginView.getUsernameField().clear();
            loginView.getPasswordField().clear();
            navigator.navigateTo("reservation");
        } else {
            loginView.getErrorLbl().setValue("Access denied!");
        }
    }

}
