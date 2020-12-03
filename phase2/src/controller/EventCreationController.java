package controller;

import enums.EventType;
import exceptions.*;
import use_cases.EventManager;

import java.util.ArrayList;
import java.util.Calendar;

public class EventCreationController {
    private String username;
    private EventManager eventManager;
    public EventCreationController(String username, EventManager eventManager){
        this.username =username;
        this.eventManager = eventManager;
    }

    /**
     * Create a event
     * @param type the event type
     * @param topic the topic of this event
     * @param time the time of event
     * @param location location of event
     * @param speakers speaker(s) of event
     * @param capacity capacity of event
     * @param vipOnly event is VIP only
     * @throws LocationNotFoundException If the location is not valid
     * @throws PastTimeException If the time has past
     * @throws InvalidTimeException If the time is not valid
     * @throws LocationConflictException If the location is being used at the time
     * @throws InvalidEventTypeException If the type is not valid
     */
    public void createEvent(EventType type, String topic, Calendar time, String location, ArrayList<String> speakers, Integer capacity, Boolean vipOnly) throws LocationNotFoundException, PastTimeException, InvalidTimeException, LocationConflictException, InvalidEventTypeException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers, capacity, vipOnly);
    }

    /**
     * cancels a talk with the given id
     * @param id id of talk to cancel
     * @throws EventNotFoundException if the id is invalid
     * @throws TypeConflictException if the event is associated with non-talk
     */
    public void cancelEvent(Integer id) throws EventNotFoundException, TypeConflictException {
        this.eventManager.cancelEvent(id);

    }
}
