package use_cases.event;

import entities.event.NetworkingEvent;
import entities.event.PanelDiscussion;
import enums.EventType;
import exceptions.InvalidEventTypeException;
import entities.event.Event;
import entities.event.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class EventFactory implements Serializable {
    public Event CreateEvent(EventType type, Integer id, String topic, Calendar time, String location, String organizer, ArrayList<String> speakers, Integer capacity, Boolean vipOnly) throws InvalidEventTypeException {
        switch(type) {
            case GENERAL_EVENT:
                return new Event(id, topic, time, location, organizer, capacity, vipOnly);
            case TALK:
                return new Talk(id, topic, time, location, organizer, speakers.get(0), capacity, vipOnly);
            case NETWORKING_EVENT:
                return new NetworkingEvent(id, topic, time, location, organizer, capacity, vipOnly);
            case PANEL_DISCUSSION:
                return new PanelDiscussion(id, topic, time, location, organizer, speakers, capacity, vipOnly);
            default:
                throw new InvalidEventTypeException();
        }
    }
}
