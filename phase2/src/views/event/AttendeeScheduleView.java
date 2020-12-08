package views.event;

import controllers.event.EventController;
import gateways.DataManager;
import presenters.event.EventPresenter;

public class AttendeeScheduleView {
    private final EventController controller;
    private final EventPresenter presenter;

    public AttendeeScheduleView(EventController controller, EventPresenter presenter) {

        this.controller = controller;
        this.presenter = presenter;
    }

    public void runView() {
        presenter.myEventsPrompt();
        presenter.displayTalkSchedule(controller.getAttendeeEvents());
    }
}
