package views.event;

import controllers.event.EventController;
import controllers.event.EventCreationController;
import enums.EventType;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.not_found.LocationNotFoundException;
import presenters.event.EventRegistrationPresenter;
import presenters.event.TimePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventCreationView {

    private EventController eventController;
    private EventRegistrationPresenter er = new EventRegistrationPresenter();
    private TimeGetterView tg = new TimeGetterView(new TimePresenter());
    private EventType type;
    Scanner userInput = new Scanner(System.in);
    public EventCreationView(EventController ec){
        eventController = ec;
    }

    public void runInteraction(){
        er.startPrompt();

        ArrayList<String> usernames = new ArrayList<>();
        er.usernamePrompt();
        boolean finished = false;
        while (!finished) {
            String username = userInput.nextLine();
            usernames.add(username);
            er.moreUsernamePrompt();
            String input = userInput.nextLine();
            if (!input.equals("1")){finished = true;}
        }

        er.roomPrompt();
        String room = userInput.nextLine();

        er.topicPrompt();
        String topic = userInput.nextLine();

        er.timePrompt();
        boolean valid = false;
        Calendar time = Calendar.getInstance();
        while (!valid){
            try{
                time = tg.collectTimeInfo();
                valid = true;
            } catch (InstantiationException e) {
                er.invalidTimePrompt();
            } catch (InputMismatchException e) {
                er.invalidInputPrompt();
            }
        }

        er.capacityPrompt();
        valid = false;
        while (!valid){
            try{
                int capacity = userInput.nextInt();} catch (Exception e) {
                er.invalidInputPrompt();
            }
        }

        try{
            eventController.createEvent(type, topic, time, room, usernames, 2, false);
        } catch (LocationNotFoundException e) {
            er.ErrorMessage("LocationNot");
        } catch (InvalidEventTypeException e) {
            er.ErrorMessage("InvalidEventType");
        } catch (LocationInUseException e) {
            er.ErrorMessage("LocationInUse");
        } catch (PastTimeException e) {
            er.ErrorMessage("PastTime");
        } catch (InvalidTimeException e) {
            er.ErrorMessage("InvalidTime");
        }
    }

}
