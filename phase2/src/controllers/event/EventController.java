package controllers.event;

import enums.EventType;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import gateways.DataManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Calendar;

public class EventController {
    private final String username;
    private final EventManager eventManager;
    public EventController(DataManager dm) {
        this.username = dm.getUsername();
        this.eventManager = dm.getEventManager();
    }

    public void createEvent(EventType type, String topic, Calendar time, String location, ArrayList<String> speakers, Integer capacity, Boolean vipOnly)
            throws LocationNotFoundException, PastTimeException, InvalidTimeException, LocationInUseException, InvalidEventTypeException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers, capacity, vipOnly);
    }

    public void rescheduleTalk(Integer id, Calendar newTime) throws LocationInUseException, PastTimeException, SpeakerIsBusyException, LocationNotFoundException, InvalidTimeException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }

    public void cancelEvent(Integer id) throws EventNotFoundException{
        this.eventManager.cancelEvent(id);
    }
}