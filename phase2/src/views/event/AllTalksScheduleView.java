package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.View;

import java.util.Scanner;

public class AllTalksScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public AllTalksScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        presenter.allEventsPrompt();
        presenter.displayTalkSchedule(controller.getAllEvents());
        return ViewEnum.VOID;
    }
}
