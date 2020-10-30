import java.util.ArrayList;
import java.util.Calendar;

public class EventManager {
    private ArrayList<Event> eventlist;

    public EventManager(){
        this.eventlist = new ArrayList<>();
    }

    /**
     * Note: these methods can be changed to reject actions that create conflicts
     * Overloading is used; two versions of AddNewEvent
     */

    public void AddNewEvent(String topic, Calendar time, String location, Account organizer){
        eventlist.add(new Event(topic, time, location, organizer));
    }

    public void AddNewEvent(String topic, Calendar time, String location, Account organizer, Account speaker){
        eventlist.add(new EventTalk(topic, time, location, organizer, speaker));
    }

    public void ChangeTopic(Event event_to_change, String new_topic){
        event_to_change.setTopic(new_topic);
    }

    public void ChangeTime(Event event_to_change, Calendar new_time){
        event_to_change.setTime(new_time);
    }

    public void ChangeLocation(Event event_to_change, String new_location) {
        event_to_change.setLocation(new_location);
    }

    public void ChangeOrganizer(Event event_to_change, Organizer new_organizer){
        event_to_change.setOrganizer(new_organizer);
    }

    public void ChangeSpeaker(EventTalk talk_to_change, Speaker new_speaker){
        talk_to_change.setSpeaker(new_speaker);
    }

}
