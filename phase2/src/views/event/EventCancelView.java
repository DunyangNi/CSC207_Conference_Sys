package views.event;

import controllers.event.EventController;
import gateways.DataManager;
import presenters.event.EventCancelPresenter;
import exceptions.not_found.EventNotFoundException;

import java.util.Scanner;

public class EventCancelView {
    private final EventController eventController;
    private final EventCancelPresenter eventCancelPresenter;
    Scanner userInput = new Scanner(System.in);
    public EventCancelView(EventController controller, EventCancelPresenter presenter){
        eventController = controller;
        eventCancelPresenter = presenter;
    }

    public void runView(){
        eventCancelPresenter.startPrompt();
        boolean cancelled = false;
        while (!cancelled) {
            int id;
            try {
                eventCancelPresenter.IDPrompt();
                id = Integer.parseInt(userInput.nextLine());
                eventController.cancelEvent(id);
                eventCancelPresenter.exitPrompt();
                cancelled = true;
            } catch(NumberFormatException e) {
                eventCancelPresenter.invalidIDPrompt();
            } catch (EventNotFoundException e) {
                eventCancelPresenter.IDNotFoundPrompt();
            }
        }
    }
}
