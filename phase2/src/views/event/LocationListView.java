package views.event;

import controllers.event.LocationController;
import enums.ViewEnum;
import presenters.event.LocationPresenter;
import views.factory.View;

/**
 * View responsible for viewing the location list.
 */
public class LocationListView implements View {
    private final LocationPresenter presenter;
    private final LocationController controller;

    /**
     * Constructs an instance of <code>LocationListView</code> based on the following parameters
     *
     * @param controller The given LocationController
     * @param presenter  The given LocationPresenter
     */
    public LocationListView(LocationController controller, LocationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.displayLocations(controller.getLocationsAsString());
        return ViewEnum.VOID;
    }
}
