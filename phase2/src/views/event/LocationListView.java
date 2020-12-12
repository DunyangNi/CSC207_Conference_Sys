package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.factory.View;

/**
 * View responsible for viewing the location list
 */

public class LocationListView implements View {

    private final LocationPresenter locationPresenter;
    private final LocationController locationController;

    public LocationListView(LocationController locationController, LocationPresenter locationPresenter) {
        this.locationController = locationController;
        this.locationPresenter = locationPresenter;
    }

    public ViewEnum runView() {
        locationPresenter.displayLocations(locationController.getLocationsAsString());
        return ViewEnum.VOID;
    }
}
