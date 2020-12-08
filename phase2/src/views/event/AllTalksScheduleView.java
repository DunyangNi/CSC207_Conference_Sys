package views.event;

import controllers.event.EventController;
import gateways.DataManager;
import presenters.event.EventPresenter;

import java.util.Scanner;

public class AllTalksScheduleView {
    private final String username;
    private final EventController controller;
    private final EventPresenter presenter = new EventPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public AllTalksScheduleView(DataManager dm) {
        this.username = dm.getUsername();
        this.controller = new EventController(dm);
    }

    public void runView() {
        presenter.allEventsPrompt();
        presenter.displayTalkSchedule(controller.getAllEvents());
    }
}
