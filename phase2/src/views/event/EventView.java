package views.event;

import controllers.event.EventController;
import enums.EventType;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import gateways.DataManager;
import presenters.event.EventPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class EventView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final EventPresenter presenter = new EventPresenter();
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    public EventView(Event) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
        this.controller = new EventController(dm);
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

    public void attendeeSchedule() {
        presenter.myEventsPrompt();
        presenter.displayTalkSchedule(controller.getAttendeeEvents(username));
    }

    public void allTalksSchedule() {
        presenter.allEventsPrompt();
        presenter.displayTalkSchedule(controller.getAllEvents());
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
