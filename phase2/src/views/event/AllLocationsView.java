package views.event;

import controllers.event.LocationController;
import gateways.DataManager;
import presenters.event.LocationPresenter;


public class AllLocationsView {

    private final LocationPresenter presenter;
    private final LocationController controller;

    public AllLocationsView(LocationController controller, LocationPresenter presenter) {

        this.controller = controller;
        this.presenter = presenter;
    }

    public void runView() {
        presenter.displayRooms(controller.getLocations());
    }
}
