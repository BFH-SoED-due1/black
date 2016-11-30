package ch.bfh.ti.soed.hs16.srs.black.view.signUpView;

import ch.bfh.ti.soed.hs16.srs.black.model.DataModel;
import ch.bfh.ti.soed.hs16.srs.black.view.reservationView.ReservationView;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;

/**
 * Created by valentin on 30.11.16.
 */
public class SignUpController {

    private DataModel dataModel;
    private SignUpView signUpView;
    private Navigator navigator;

    public SignUpController(DataModel dataModel, SignUpView signUpView, Navigator navigator) {
        this.dataModel = dataModel;
        this.signUpView = signUpView;
        this.navigator = navigator;

        signUpView.getAddUserButton().addClickListener(this::addUser);
        signUpView.getClearButton().addClickListener(this::clearFields);
    }

    public void addUser(Button.ClickEvent event){
        // Check if Username is already in Use
        // Add New User to Database
    }

    public void clearFields(Button.ClickEvent event){
        signUpView.getUsernameField().clear();
        signUpView.getPasswordField().clear();
        signUpView.getPasswordFieldRepeat().clear();
    }
}
