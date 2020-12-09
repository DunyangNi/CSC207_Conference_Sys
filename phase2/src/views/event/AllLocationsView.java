package views.event;

import controllers.event.LocationController;
import presenters.event.LocationPresenter;


public class AllLocationsView {

    private final LocationPresenter locationPresenter;
    private final LocationController locationController;

    public AllLocationsView(LocationController locationController, LocationPresenter locationPresenter) {
        this.locationController = locationController;
        this.locationPresenter = locationPresenter;
    }

    public void runView() {
        locationPresenter.displayLocations(locationController.getLocationsAsString());
    }
}
