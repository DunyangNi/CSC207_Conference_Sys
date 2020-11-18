package use_cases;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import entities.Event;
import entities.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class EventChecker implements Serializable {

    public void checkValidEvent(Calendar time, String location, ArrayList<String> locations, ArrayList<Event> events) throws ConflictException, ObjectNotFoundException {
        Calendar currTime = Calendar.getInstance();
        if (!locations.contains(location))
            throw new ObjectNotFoundException("Location " + location);
        if (currTime.compareTo(time) >= 0)
            throw new ConflictException("Event to be scheduled takes place in the past.");
        if (!(9 <= time.get(Calendar.HOUR_OF_DAY) && time.get(Calendar.HOUR_OF_DAY) <= 16))
            throw new ConflictException("Time must start between 9 AM and 5 PM.");
        for (Event event : events) {
            if (event.getLocation().equals(location) && event.getTime().equals(time))
                throw new ConflictException("Location " + location + " is busy at scheduled time.");
        }
    }

    public void checkValidTalk(Calendar time, String location, String speaker, ArrayList<String> locations, ArrayList<Talk> talks, ArrayList<Event> events) throws ConflictException, ObjectNotFoundException {
        for (Talk t : talks) {
            if (t.getSpeaker().equals(speaker) && t.getTime().equals(time))
                throw new ConflictException("Speaker " + speaker + " cannot be in multiple Talks at the same time.");
        }
        checkValidEvent(time, location, locations, events);
    }
}
