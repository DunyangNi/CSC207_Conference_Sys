package views.event;

import controllers.event.LocationController;
import gateways.DataManager;
import presenters.event.LocationPresenter;


public class AllLocationsView {

    private final LocationPresenter presenter = new LocationPresenter();
    private final LocationController controller;

    public AllLocationsView(DataManager dm) {

        this.controller = new LocationController(dm);
    }

    public void runView() {
        presenter.displayRooms(controller.getLocations());
    }
}
