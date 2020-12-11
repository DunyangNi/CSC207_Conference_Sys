package use_cases.event;

import entities.event.Networking;
import entities.event.Panel;
import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import entities.event.Event;
import entities.event.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A factory class responsible for instantiating instances of the Event class
 * Part of the factory design pattern and a helper class for EventManager
 */

public class EventFactory implements Serializable {

    /**
     * @return An event based on the descriptions given
     * @param type An enum describing the type of the event. See the enum package
     * @param id Desired id
     * @param topic Desired topic
     * @param time Desired time
     * @param location Desired location string
     * @param organizer Desired organizer username
     * @param speakers Desired list of speaker usernames
     * @param capacity Desired capacity
     * @param tables Desired number of tables
     * @param chairs Desired number of chairs
     * @param hasInternet Desired internet requirement
     * @param hasSoundSystem Desired sound system requirement
     * @param hasPresentationScreen Desired presentation screen requirement
     * @param vipOnly Desired VIP restriction status
     *
     * @throws InvalidEventTypeException When the input type is not a valid event type
     */

    public Event getEvent(EventTypeEnum type, Integer id, String topic, Calendar time, String location,
                          String organizer, ArrayList<String> speakers, Integer capacity, int tables,
                          int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen,
                          Boolean vipOnly) throws InvalidEventTypeException {
        switch(type) {
            case GENERAL_EVENT:
                return new Event(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet,
                        hasSoundSystem, hasPresentationScreen, vipOnly);
            case TALK:
                return new Talk(id, topic, time, location, organizer, speakers.get(0), capacity, tables, chairs,
                        hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            case NETWORKING_EVENT:
                return new Networking(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet,
                        hasSoundSystem, hasPresentationScreen, vipOnly);
            case PANEL_DISCUSSION:
                return new Panel(id, topic, time, location, organizer, speakers, capacity, tables, chairs,
                        hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
            default:
                throw new InvalidEventTypeException();
        }
    }
}
