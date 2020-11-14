package use_cases;

import entities.Event;
import entities.EventTalk;

import java.util.ArrayList;
import java.util.Calendar;

public class EventChecker {

    public boolean CheckTimeOverlap(Calendar time_1, Calendar time_2){
        return time_1.compareTo(time_2) == 0;
    }

    /*
     * NOTE: THESE METHODS WILL CHECK IF TIME CONFLICTS ARE CREATED:
     * THEY WILL REJECT (AND GIVE SIGNAL OF FAILURE) WHEN CONFLICTS
     * WOULD BE CREATED
     * Overloading is used; two versions of AddNewEvent() and validEvent() (helper)
     */

    /**
     * (NEW!) (Helper) Returns true iff EventTalk is valid: no conflicting time or existing events and talks.
     * @param topic given topic
     * @param time given time
     * @param location given location
     * @param speaker given speaker
     * @param locations the current location list
     * @param talks the current talk list
     * @param events the current event list
     * @return true iff EventTalk is valid: no conflicting time or existing events and talks.
     */
    public boolean validEvent(String topic, Calendar time, String location, String speaker,
                              ArrayList<String> locations, ArrayList<EventTalk> talks, ArrayList<Event> events) {
        // call general helper
        if (validEvent(topic, time, location, locations, events)) {
            // check talks
            for(EventTalk talk: talks) {
                if (talk.getSpeaker().equals(speaker) && CheckTimeOverlap(time, talk.getTime())) { return false; }
                if (talk.getTopic().equals(topic)) { return false; }
                // possible extension (double booking speaker in two locations)
            }
            return true;
        }
        return false;
    }

    /**
     * (NEW!) (Helper) Returns true iff Event is valid: no conflicting time or existing events.
     * @param topic given topic
     * @param time given time
     * @param location given location
     * @param locations the current location list
     * @param events the current event list
     * @return true iff Event is valid: no conflicting time or existing events.
     */
    public boolean validEvent(String topic, Calendar time, String location,
                              ArrayList<String> locations, ArrayList<Event> events) {
        // check if location is valid
        if (!locations.contains(location)) { return false; }
        // check if any conflicting events or events already existing
        for(Event event: events) {
            if (event.getLocation().equals(location) && CheckTimeOverlap(time, event.getTime())){ return false; }
            if (event.getTopic().equals(topic)) { return false; }
        }
        return true;
    }
}
