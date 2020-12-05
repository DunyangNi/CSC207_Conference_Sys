package controllers.event;

import exceptions.*;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import use_cases.event.EventManager;

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
     * @throws InvalidTimeException if an event time is past the current time or is not
     * between 9 A.M and 4 P.M inclusive, or the same event has been already scheduled
     * @throws LocationNotFoundException if the location for an event is not allowed
     * @throws PastTimeException if the time have past
     * @throws LocationInUseException if the location is being used at the time
     * @throws SpeakerIsBusyException if the speaker is not available at the time
     * @throws EventNotFoundException if the given event id is invalid
     */
    public void rescheduleTalk(Integer id, Calendar newTime) throws LocationInUseException, PastTimeException, SpeakerIsBusyException, LocationNotFoundException, InvalidTimeException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }
}
