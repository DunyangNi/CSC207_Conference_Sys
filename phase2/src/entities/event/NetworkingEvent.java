package entities.event;

import java.io.Serializable;
import java.util.Calendar;

/*
    Represents any Networking event in the system, where a Networking event
    is a type of event without a speaker.
 */
public class NetworkingEvent extends Event implements Serializable {
    public NetworkingEvent(
            Integer id, String topic, Calendar time, String location,
            String organizer, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly)
    {
        super(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
    }

    /**
     * Compares a given <code>Object</code> with this <code>NetworkingEvent</code>. Returns
     * true iff the given <code>Object</code> matches this <code>NetworkingEvent</code>.
     *
     * @param other other <code>Object</code> presumed <code>NetworkingEvent</code> to compare
     * @return the given <code>Object</code> matches this <code>NetworkingEvent</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof NetworkingEvent) return super.equals(other);
        else return false;
    }

    @Override
    public String toString() {
        return "Event ID: " + getId() + "\n" +
                "Event Type: Networking Event\n" +
                "Topic: " + getTopic() + "\n" +
                "Location: " + getLocation() + "\n" +
                "Time: " + getTime().getTime().toString() + "\n" +
                "Organizer: " + getOrganizer();
    }
}
