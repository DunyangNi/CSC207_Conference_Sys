package use_cases;

import entities.Event;
import java.io.Serializable;
import java.util.Calendar;

public class EventModifier implements Serializable {
    public void ChangeLocation(Event eventToChange, String newLocation) {
        eventToChange.setLocation(newLocation);
    }

    public void ChangeTime(Event selectedEvent, Calendar newTime) {
        selectedEvent.setTime(newTime);
    }

    public void ChangeTopic(Event eventToChange, String new_topic){
        eventToChange.setTopic(new_topic);
    }

    public void ChangeOrganizer(Event eventToChange, String new_organizer){
        eventToChange.setOrganizer(new_organizer);
    }

}
