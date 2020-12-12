package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.View;

/**
 * View responsible for speaker event schedule functionality
 */

public class SpeakerScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;

    public SpeakerScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.myEventsHeader();
        presenter.displayEventSchedule(controller.getSpeakerEvents());
        return ViewEnum.VOID;
    }
}
