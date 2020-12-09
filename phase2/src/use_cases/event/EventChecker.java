package use_cases.event;

import entities.event.Panel;
import entities.event.Event;
import entities.event.Talk;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;

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

    public void checkValidEvent(Calendar time, String location, ArrayList<String> speakers, ArrayList<Event> events) throws OutOfScheduleException, LocationInUseException, SpeakerIsBusyException {
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
