package views.event;

import controllers.event.EventCreationController;
import presenters.event.EventRegistrationPresenter;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventCreationView {

    private EventCreationController eventCreationController;
    private EventRegistrationPresenter er = new EventRegistrationPresenter();
    private TimeGetterView tg = new TimeGetterView();
    Scanner userInput = new Scanner(System.in);
    public EventCreationView(EventCreationController ecc){
        eventCreationController = ecc;
    }

    public void runInteraction(){
        er.startPrompt();
        er.usernamePrompt();
        String username = userInput.nextLine();
        er.roomPrompt();
        String room = userInput.nextLine();
        er.topicPrompt();
        String topic = userInput.nextLine();
        er.timePrompt();
        boolean valid = false;
        Calendar time;
        while (!valid){
            try{
                time = tg.collectTimeInfo();
                valid = true;
            } catch (InstantiationException e) {
                System.out.println("Invalid time.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }
        //try{
            //eventCreationController.createEvent(new EventType("1"), topic, time, room, username, 2, false);
        //}
    }

}
