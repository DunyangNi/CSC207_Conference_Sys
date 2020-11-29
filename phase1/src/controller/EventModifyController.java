package controller;

import Throwables.*;
import use_cases.EventManager;

import java.util.Calendar;

public class EventModifyController {
    private EventManager eventManager;
    public EventModifyController(EventManager eventManager){
        this.eventManager = eventManager;
    }


    /**
     * reschedules a talk with the given id to time newTime
     * @param id talk id
     * @param newTime time to reschedule talk to
     */
    public void rescheduleTalk(Integer id, Calendar newTime) throws ConflictException, LocationConflictException, PastTimeException, EventNotFoundException, LocationNotFoundException, TimeConflictException {
        this.eventManager.changeTime(id, newTime);
    }
}
