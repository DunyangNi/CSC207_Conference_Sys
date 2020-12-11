package entities.event;

import entities.event.Networking;
import entities.event.Panel;
import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import entities.event.Event;
import entities.event.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class EventFactory implements Serializable {
    public Event getEvent(EventTypeEnum type, Integer id, String topic, Calendar time, String location, String organizer, ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly) throws InvalidEventTypeException {
        switch(type) {
            case GENERAL_EVENT:
                return new Event(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            case TALK:
                return new Talk(id, topic, time, location, organizer, speakers.get(0), capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            case NETWORKING_EVENT:
                return new Networking(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            case PANEL_DISCUSSION:
                return new Panel(id, topic, time, location, organizer, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            default:
                throw new InvalidEventTypeException();
        }
    }
}
