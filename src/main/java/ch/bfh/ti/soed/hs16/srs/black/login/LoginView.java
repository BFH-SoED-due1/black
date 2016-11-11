package ch.bfh.ti.soed.hs16.srs.black.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends CustomComponent implements View {

    private Label errorLbl;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public LoginView(){

        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        loginButton = new Button("Login");
        errorLbl = new Label("");

        VerticalLayout layout = new VerticalLayout();
        Panel panel = new Panel("SRS");
        panel.setSizeUndefined();
        layout.addComponent(panel);

        FormLayout content = new FormLayout();
        content.addComponents(usernameField, passwordField, loginButton, errorLbl);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);

        setCompositionRoot(layout);

        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);


    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Label getErrorLbl() {
        return errorLbl;
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }


}



