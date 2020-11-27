package use_cases;

import enums.EventType;
import exceptions.InvalidEventTypeException;
import entities.Event;
import entities.Talk;

import java.util.ArrayList;
import java.util.Calendar;

public class EventFactory {
    public Event CreateEvent(EventType type, Integer id, String topic, Calendar time, String location, String organizer, ArrayList<String> speakers, Integer capacity, Boolean vipOnly) throws InvalidEventTypeException {
        switch(type) {
            case GENERAL_EVENT:
                return new Event(id, topic, time, location, organizer, capacity, vipOnly);
            case TALK:
                return new Talk(id, topic, time, location, organizer, speakers.get(0), capacity, vipOnly);
            case NETWORKING_EVENT:
                // return new NetworkingEvent();
            case PANEL_DISCUSSION:
                // return new PanelDiscussion();
            default:
                throw new InvalidEventTypeException();
        }
    }
}
