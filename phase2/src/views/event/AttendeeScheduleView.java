package views.event;

import controllers.event.EventController;
import gateways.DataManager;
import presenters.event.EventPresenter;

public class AttendeeScheduleView {
    private final EventController controller;
    private final EventPresenter presenter = new EventPresenter();

    public AttendeeScheduleView(DataManager dm) {
        this.controller = new EventController(dm);
    }

    public void runView() {
        presenter.myEventsPrompt();
        presenter.displayTalkSchedule(controller.getAttendeeEvents());
    }
}
