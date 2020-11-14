package use_cases;

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

    public boolean ChangeTime(Event selectedEvent, Calendar newTime, ArrayList<Event> events){
        Calendar curr_time = Calendar.getInstance();
        if (curr_time.compareTo(selectedEvent.getTime()) >= 0 || curr_time.compareTo(newTime) >= 0) {
            return false;
        }
        if (!(9 < newTime.get(Calendar.HOUR_OF_DAY) && newTime.get(Calendar.HOUR_OF_DAY) < 4)) {
            return false;
        }
        for (Event event: events) {
            String eventLocation = event.getLocation(); Calendar eventTime = event.getTime();
            if (!event.equals(selectedEvent) && eventLocation.equals(selectedEvent.getLocation()) && (eventTime.compareTo(newTime) == 0)) {
                return false;
            }
        }
        selectedEvent.setTime(newTime);
        return true;
    }

    public void ChangeTopic(Event event_to_change, String new_topic){
        event_to_change.setTopic(new_topic);
    }

    public void ChangeOrganizer(Event event_to_change, String new_organizer){
        event_to_change.setOrganizer(new_organizer);
    }

    public boolean ChangeSpeaker(EventTalk talk_to_change, String new_speaker, ArrayList<EventTalk> talks){
        for(EventTalk talk: talks) {
            Calendar time1 = talk.getTime();
            Calendar time2 = talk_to_change.getTime();

            if (talk != talk_to_change && time1.equals(time2) && talk.getSpeaker().equals(talk_to_change.getSpeaker())){
                return false;
            }
        }
        talk_to_change.setSpeaker(new_speaker);
        return true;
    }

    public ArrayList<String> getAttendees(Event event){
        return event.getAttendees();
    }
}
