package use_cases.event;

import entities.event.Panel;
import entities.event.Event;
import entities.event.Talk;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a checking logic for events. Helper class of EventManager
 */
public class EventChecker implements Serializable {

    /**
     * Check if the hypothetical event with time and location is valid and does not create conflicts in the given list
     * of speakers and and events
     *
     * @param time Hypothetical event time
     * @param location Hypothetical event location
     * @param speakers List of speakers
     * @param events List of events
     * @throws OutOfScheduleException if the hypothetical time is not a valid event time
     * @throws LocationInUseException if the hypothetical time and location would create a double booking of a location
     * @throws SpeakerIsBusyException if the hypothetical time and location would create a double booking of a speaker
     */

    public void checkValidEvent(Calendar time, String location, ArrayList<String> speakers, ArrayList<Event> events)
            throws OutOfScheduleException, LocationInUseException, SpeakerIsBusyException {
        if (!(9 <= time.get(Calendar.HOUR_OF_DAY) && time.get(Calendar.HOUR_OF_DAY) <= 16)) throw new OutOfScheduleException();
        for (Event event : events) {
            if (event.getTime().equals(time)) {
                if (event.getLocation().equals(location)) throw new LocationInUseException();
                if (event instanceof Talk && speakers.contains(((Talk) event).getSpeaker())) throw new SpeakerIsBusyException();
                else if (event instanceof Panel) {
                    ArrayList<String> selectedSpeakers = new ArrayList<>(((Panel) event).getSpeakers());
                    selectedSpeakers.retainAll(speakers);
                    if (!selectedSpeakers.isEmpty()) throw new SpeakerIsBusyException();
                }
            }
        }
    }
}
