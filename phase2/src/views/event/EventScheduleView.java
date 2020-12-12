package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.factory.View;

/**
 * View responsible for viewing global event schedule
 * Fields:
 * controller: EventController responsible for event functionality
 * presenter: EventSchedulePresenter responsible for displaying relevant prompts
 */

public class EventScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;

    /**
     * Constructs an instance of <code>EventScheduleView</code> based on the following parameters
     * @param controller The given EventController
     * @param presenter The given EventSchedulePresenter
     */

    public EventScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Run the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        presenter.allEventsHeader();
        presenter.displayEventSchedule(controller.getAllEvents());
        return ViewEnum.VOID;
    }
}
