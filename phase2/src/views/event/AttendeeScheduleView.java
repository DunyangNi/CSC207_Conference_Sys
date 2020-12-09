package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.View;

public class AttendeeScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;

    public AttendeeScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.myEventsPrompt();
        presenter.displayEventSchedule(controller.getAttendeeEvents());
        return ViewEnum.VOID;
    }
}
