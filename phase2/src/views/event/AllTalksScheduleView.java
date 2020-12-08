package views.event;

import controllers.event.EventController;
import gateways.DataManager;
import presenters.event.EventPresenter;

import java.util.Scanner;

public class AllTalksScheduleView {
    private final EventController controller;
    private final EventPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public AllTalksScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public void runView() {
        presenter.allEventsPrompt();
        presenter.displayTalkSchedule(controller.getAllEvents());
    }
}
