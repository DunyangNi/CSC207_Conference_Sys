package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.factory.View;

/**
 * View responsible for speaker event schedule functionality
 */
public class SpeakerScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;

    /**
     * Constructs an instance of <code>SpeakerScheduleView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given EventPresenter
     */
    public SpeakerScheduleView(EventController controller, EventPresenter presenter) {
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
        presenter.displayEventSchedule(controller.getSpeakerEvents());
        return ViewEnum.VOID;
    }
}
