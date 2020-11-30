package use_cases;

import exceptions.*;
import entities.Event;
import entities.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a checking logic for events
 */
public class EventChecker implements Serializable {

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Validates information about an event
     *
     * @param time time for an event
     * @param location location for an event
     * @param locations a list of allowed event locations
     * @param events a list of scheduled events
     * @throws InvalidTimeException if an event time is past the current time or is not
     * between 9 A.M and 4 P.M inclusive, or the same event has been already scheduled
     * @throws LocationNotFoundException if the location for an event is not allowed
     * @throws PastTimeException if the time have past
     * @throws LocationConflictException if the location is being used at the time
     */
    public void checkValidEvent(Calendar time, String location, ArrayList<String> locations, ArrayList<Event> events) throws LocationNotFoundException, PastTimeException, InvalidTimeException, LocationConflictException {
        Calendar currTime = Calendar.getInstance();

        if (!locations.contains(location))  // is a valid location?
            throw new LocationNotFoundException();

        if (currTime.compareTo(time) >= 0)  // is time already past?
            throw new PastTimeException();

        // is time between 9 am to 4 pm inclusive?
        if (!(9 <= time.get(Calendar.HOUR_OF_DAY) && time.get(Calendar.HOUR_OF_DAY) <= 16))
            throw new InvalidTimeException();

        for (Event event : events) {    // is the same event already scheduled?
            if (event.getLocation().equals(location) && event.getTime().equals(time))
                throw new LocationConflictException();
        }
    }

    /**
     * Validates information about a talk event
     *
     * @param time time for a talk event
     * @param location location for a talk event
     * @param speaker speaker for a talk event
     * @param locations location for a talk event
     * @param talks a list of scheduled talks
     * @param events a list of scheduled events
     * @throws InvalidTimeException if an event time is past the current time or is not
     * between 9 A.M and 4 P.M inclusive, or the same event has been already scheduled
     * @throws LocationNotFoundException if the location for an event is not allowed
     * @throws PastTimeException if the time have past
     * @throws LocationConflictException if the location is being used at the time
     * @throws SpeakerConflictException if the speaker have another talk at the time
     */
    public void checkValidTalk(Calendar time, String location, String speaker, ArrayList<String> locations, ArrayList<Talk> talks, ArrayList<Event> events) throws LocationNotFoundException, PastTimeException, InvalidTimeException, LocationConflictException, SpeakerConflictException {
        // Check if the same talk is found from a list of scheduled talks
        for (Talk t : talks) {
            if (t.getSpeaker().equals(speaker) && t.getTime().equals(time))
                throw new SpeakerConflictException();
        }
        checkValidEvent(time, location, locations, events);
    }
}
