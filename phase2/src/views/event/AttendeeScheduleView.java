package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.factory.View;

/**
 * View responsible for viewing an attendee's event schedule.
 */
public class AttendeeScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;

    /**
     * Constructs an instance of <code>AttendeeScheduleView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given EventPresenter
     */
    public AttendeeScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.myEventsHeader();
        presenter.displayEventSchedule(controller.getAttendeeEvents());
        return ViewEnum.VOID;
    }
}
