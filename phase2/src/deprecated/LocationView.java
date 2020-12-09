package deprecated;

import controllers.event.LocationController;
import gateways.DataManager;
import presenters.event.LocationPresenter;

import java.util.Scanner;

public class LocationView {

    private final LocationPresenter presenter = new LocationPresenter();
    private final LocationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public LocationView(DataManager dm) {

        this.controller = new LocationController(dm);
    }

    public void rooms() {
        presenter.displayLocations(controller.getLocationsAsString());
    }
}
