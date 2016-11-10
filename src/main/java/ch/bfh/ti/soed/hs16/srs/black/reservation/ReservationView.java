package ch.bfh.ti.soed.hs16.srs.black.reservation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;


public class ReservationView extends CustomComponent implements View{
    private TextField fromField;
    private TextField toField;
    private Button makeButton;
    private Button logoutButton;

    public ReservationView() {

        fromField = new TextField("Reservation from");
        toField = new TextField("Reservation to");
        makeButton = new Button("Make reservation");
        logoutButton = new Button("Logout");

        VerticalLayout layout = new VerticalLayout();

        FormLayout content = new FormLayout();
        content.addComponents(fromField, toField, makeButton, logoutButton);
        content.setSizeUndefined();
        content.setMargin(true);
        layout.addComponent(content);
        setCompositionRoot(layout);

        layout.setSizeFull();


        layout.setComponentAlignment(content, Alignment.MIDDLE_CENTER);

    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}





