package views.event;

import controllers.event.EventController;
import presenters.event.EventCancelPresenter;
import exceptions.not_found.EventNotFoundException;

import java.util.Scanner;

public class EventCancelView {

    private final EventController eventController;
    private EventCancelPresenter eventCancelPresenter = new EventCancelPresenter();
    Scanner userInput = new Scanner(System.in);
    public EventCancelView(EventController ec){
        eventController = ec;
    }

    public void runView(){
        eventCancelPresenter.startPrompt();
        int id;
        try {
            String input = userInput.nextLine();
            id = Integer.parseInt(input);
        } catch(Exception e){
            System.out.println("Invalid input.");
            return;
        }
        try {
            eventController.cancelEvent(id);
            eventCancelPresenter.exitPrompt();
        } catch (EventNotFoundException e) {
            System.out.println("This id is not found.");
        }
    }
}
