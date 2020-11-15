package use_cases;

import Throwables.ConflictException;
import entities.Event;
import entities.EventTalk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class EventModifier implements Serializable {
    public boolean ChangeLocation(Event event_to_change, String new_location,
                                  ArrayList<String> locations, ArrayList<Event> events) {
        if(!locations.contains(new_location)) {
            return false;
        }

        Calendar time2 = event_to_change.getTime();
        for(Event event: events) {
            Calendar time1 = event.getTime();
            String location1 = event.getLocation();
            if (event.equals(event_to_change) && location1.equals(new_location) &&
                    (time1.compareTo(time2) == 0)) {
                return false;
            }
        }
        event_to_change.setLocation(new_location);
        return true;
    }

    public void ChangeTime(Event selectedEvent, Calendar newTime, ArrayList<Event> events) throws ConflictException{
        Calendar curr_time = Calendar.getInstance();
        if (curr_time.compareTo(selectedEvent.getTime()) >= 0 || curr_time.compareTo(newTime) >= 0) {
            throw new ConflictException("Event to be reschedules takes place in the past");
        }
        for (Event event: events) {
            String eventLocation = event.getLocation(); Calendar eventTime = event.getTime();
            if (!event.equals(selectedEvent) && eventLocation.equals(selectedEvent.getLocation()) &&
                    (eventTime.compareTo(newTime) == 0)) {
                throw new ConflictException("Conflicts with event at location");
            }
            if (!event.equals(selectedEvent) && (event instanceof  EventTalk) && (selectedEvent instanceof  EventTalk) && ((EventTalk) event).getSpeaker().equals(((EventTalk) selectedEvent).getSpeaker()) && (eventTime.compareTo(newTime) == 0)) {
                throw new ConflictException("Speaker scheduled at 2 places at the same time");
            }
        }
        selectedEvent.setTime(newTime);
    }

    public void ChangeTopic(Event event_to_change, String new_topic){
        event_to_change.setTopic(new_topic);
    }

    public void ChangeOrganizer(Event event_to_change, String new_organizer){
        event_to_change.setOrganizer(new_organizer);
    }

    public ArrayList<String> getAttendees(Event event){
        return event.getAttendees();
    }

}
