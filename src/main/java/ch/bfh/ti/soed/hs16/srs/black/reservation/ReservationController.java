package ch.bfh.ti.soed.hs16.srs.black.reservation;

import ch.bfh.ti.soed.hs16.srs.black.DataModel;
import com.vaadin.navigator.Navigator;

public class ReservationController {

    private DataModel dataModel;
    private ReservationView reservationView;
    private Navigator navigator;

    public ReservationController(DataModel dataModel, ReservationView reservationView, Navigator navigator){
        this.dataModel = dataModel;
        this.reservationView = reservationView;
        this.navigator = navigator;

        reservationView.getLogoutButton().addClickListener(clickEvent -> {

            navigator.navigateTo("");

        });


    }


}
