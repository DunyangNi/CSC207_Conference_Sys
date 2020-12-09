package controllers.event;

import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.OutOfScheduleException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import gateways.DataManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class EventController {
    private final String username;
    private final EventManager eventManager;

    public EventController(DataManager dm) {
        this.username = dm.getUsername();
        this.eventManager = dm.getEventManager();
    }

    public void createEvent(EventTypeEnum type, String topic, Calendar time, String location, ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly)
            throws OutOfScheduleException, SpeakerIsBusyException, InvalidEventTypeException, LocationInUseException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
    }

    public void rescheduleTalk(Integer id, Calendar newTime) throws OutOfScheduleException, SpeakerIsBusyException, LocationInUseException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }

    public void cancelEvent(Integer id) throws EventNotFoundException { this.eventManager.cancelEvent(id); }

    public HashMap<String[], Calendar> getAllEvents() {
        return eventManager.fetchSortedTalks();
    }

    public HashMap<String[], Calendar> getAttendeeEvents() { //only for attendee
        HashMap<String[], Calendar> allTalks = eventManager.fetchSortedTalks();
        HashMap<String[], Calendar> attendeeTalks = new HashMap<>();

        for(String[] eventInfo : allTalks.keySet()) {
            if(eventManager.isSignedUp(Integer.parseInt(eventInfo[4]), username)) {
                attendeeTalks.put(eventInfo, allTalks.get(eventInfo));
            }
        }
        return attendeeTalks;
    }
}