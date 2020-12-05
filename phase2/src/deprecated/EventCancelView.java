package deprecated;

import exceptions.not_found.EventNotFoundException;

import java.util.Scanner;

public class EventCancelView {

    private EventCreationController eventCreationController;
    private EventCancelPresenter p = new EventCancelPresenter();
    Scanner userInput = new Scanner(System.in);
    public EventCancelView(EventCreationController ecc){
        eventCreationController = ecc;
    }

    public void runInteraction(){
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
            eventCreationController.cancelEvent(id);
            p.exitPrompt();
        } catch (EventNotFoundException e) {
            System.out.println("This id is not found.");
        }
    }
}
