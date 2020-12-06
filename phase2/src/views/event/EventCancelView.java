package views.event;

import controllers.event.EventController;
import controllers.event.EventCreationController;
import presenters.event.EventCancelPresenter;
import exceptions.not_found.EventNotFoundException;

import java.util.Scanner;

public class EventCancelView {

    private EventController eventController;
    private EventCancelPresenter p = new EventCancelPresenter();
    Scanner userInput = new Scanner(System.in);
    public EventCancelView(EventController ec){
        eventController = ec;
    }

    public void runView(){
        p.startPrompt();
        int id;
        try{
            String input = userInput.nextLine();
            id = Integer.parseInt(input);
        } catch(Exception e){
            System.out.println("Invalid input.");
            return;
        }
        try{
            eventController.cancelEvent(id);
            p.exitPrompt();
        } catch (EventNotFoundException e) {
            System.out.println("This id is not found.");
        }
    }
}
