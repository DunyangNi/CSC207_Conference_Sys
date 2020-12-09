package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventCancelPresenter;
import exceptions.not_found.EventNotFoundException;
import views.View;

import java.util.Scanner;

public class EventCancelView implements View {
    private final EventController eventController;
    private final EventCancelPresenter eventCancelPresenter;
    Scanner userInput = new Scanner(System.in);

    public EventCancelView(EventController controller, EventCancelPresenter presenter){
        eventController = controller;
        eventCancelPresenter = presenter;
    }

    public ViewEnum runView(){
        eventCancelPresenter.startPrompt();

        boolean chosenID = false;
        int id = 0;
        while (!chosenID) {
            try {
                eventCancelPresenter.eventIDPrompt();
                id = Integer.parseInt(userInput.nextLine());
                chosenID = true;
            } catch (NumberFormatException e) { eventCancelPresenter.invalidIDPrompt(); }
        }

        try {
            eventController.cancelEvent(id);
            eventCancelPresenter.exitPrompt();
            return ViewEnum.VOID;
        } catch (EventNotFoundException e) {
            eventCancelPresenter.eventNotFoundPrompt();
        }
        eventCancelPresenter.cancelExitPrompt();
        return ViewEnum.VOID;
    }
}
