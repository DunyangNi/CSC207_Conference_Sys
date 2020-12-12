package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.factory.View;

/**
 * View responsible for viewing the location list
 *
 * Fields:
 * locationPresenter: LocationPresenter responsible for displaying relevant prompts
 * locationController: LocationController responsible for location functionality
 */

public class LocationListView implements View {

    private final LocationPresenter locationPresenter;
    private final LocationController locationController;

    /**
     * Constructs an instance of <code>LocationListView</code> based on the following parameters
     * @param locationController The given LocationController
     * @param locationPresenter The given LocationPresenter
     */

    public LocationListView(LocationController locationController, LocationPresenter locationPresenter) {
        this.locationController = locationController;
        this.locationPresenter = locationPresenter;
    }

    /**
     * Run the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        locationPresenter.displayLocations(locationController.getLocationsAsString());
        return ViewEnum.VOID;
    }
}
