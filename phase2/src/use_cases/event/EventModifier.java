package use_cases.event;

import entities.event.Event;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a modifier for events
 */
public class EventModifier implements Serializable {
    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Changes an event's location
     *
     * @param eventToChange an event to change its location
     * @param newLocation a new location for the event
     */
    public void ChangeLocation(Event eventToChange, String newLocation) {
        eventToChange.setLocation(newLocation);
    }

    /**
     * Changes event's time
     *
     * @param selectedEvent an event to change its time
     * @param newTime new time for the event
     */
    public void ChangeTime(Event selectedEvent, Calendar newTime) {
        selectedEvent.setTime(newTime);
    }

    /**
     * Changes an event's topic
     *
     * @param eventToChange an event to change its topic
     * @param new_topic new topic for the event
     */
    public void ChangeTopic(Event eventToChange, String new_topic){
        eventToChange.setTopic(new_topic);
    }

    /**
     * Changes an event's organizer
     *
     * @param eventToChange an event to change its organizer
     * @param new_organizer new organizer for the event
     */
    public void ChangeOrganizer(Event eventToChange, String new_organizer){
        eventToChange.setOrganizer(new_organizer);
    }

}
