package controllers.event;

import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import gateways.DataManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class EventController {
    private final String username;
    private final EventManager eventManager;
    private final AccountManager accountManager;

    public EventController(DataManager dm) {
        this.username = dm.getUsername();
        this.eventManager = dm.getEventManager();
        this.accountManager = dm.getAccountManager();
    }

    public void createEvent(EventTypeEnum type, String topic, Calendar time, String location, ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly)
            throws OutOfScheduleException, SpeakerIsBusyException, InvalidEventTypeException, LocationInUseException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
    }

    public void rescheduleTalk(Integer id, Calendar newTime) throws OutOfScheduleException, SpeakerIsBusyException, LocationInUseException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }

    public void cancelEvent(Integer id) throws EventNotFoundException { this.eventManager.cancelEvent(id); }

    public ArrayList<String> getAllEvents() {
        return eventManager.getAllSortedEvents();
    }

    public ArrayList<String> getAttendeeEvents() {
        ArrayList<Integer> selectedEventIDs = accountManager.getAttendeeEvents(username);
        return eventManager.getSortedEventsByID(selectedEventIDs);
    }

    public ArrayList<String> getSpeakerEvents() { return eventManager.getSpeakerSortedEvents(username); }
}