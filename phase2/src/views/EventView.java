package views;

import controller.EventController;
import enums.EventType;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import presenter.EventPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventView {
    private final String username;
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final EventPresenter presenter = new EventPresenter();
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    public EventView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.controller = new EventController(username, em);
    }

    public void eventCreation() {
        presenter.eventCreationPrompt("topic");
        String topic = userInput.nextLine();

        presenter.eventCreationPrompt("speaker");
        ArrayList<String> speakers = new ArrayList<>();
        speakers.add(userInput.nextLine());

        presenter.eventCreationPrompt("room");
        String room = userInput.nextLine();

        Calendar time = collectTimeInfo();

        presenter.eventCreationPrompt("capacity");
        int capacity = userInput.nextInt();

        presenter.eventCreationPrompt("vip");
        boolean vip = userInput.nextInt() != 0;

        try {
            controller.createEvent(EventType.TALK, topic, time, room, speakers, capacity, vip);
        } catch (LocationNotFoundException | PastTimeException | InvalidTimeException | LocationInUseException | InvalidEventTypeException e) {
            e.printStackTrace();
        }
    }

    public void eventReschedule() {
        presenter.eventIdPrompt();
        int id = userInput.nextInt();
        Calendar time = collectTimeInfo();

        try {
            controller.rescheduleTalk(id, time);
        } catch (LocationInUseException | PastTimeException | SpeakerIsBusyException | LocationNotFoundException | InvalidTimeException | EventNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void eventCancellation() {
        presenter.eventIdPrompt();
        int id = userInput.nextInt();
        try {
            controller.cancelEvent(id);
        } catch (EventNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Calendar collectTimeInfo() {
        presenter.timePrompt("day");
        int day = userInput.nextInt();
        presenter.timePrompt("month");
        int month = userInput.nextInt()-1;
        presenter.timePrompt("year");
        int year = userInput.nextInt();
        presenter.timePrompt("hour");
        int hour = userInput.nextInt();

        Calendar time = Calendar.getInstance();
        try{
            time.set(year, month, day, hour, 0, 0);
            time.clear(Calendar.MILLISECOND);}
        catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
