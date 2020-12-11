package entities.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/*
    Represents any Networking event in the system, where a Networking event
    is a type of event without a speaker.
 */
public class Networking extends Event implements Serializable, EventAcceptor {

    public Networking(
            Integer id, String topic, Calendar time, String location,
            String organizer, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly)
    {
        super(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Networking</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Networking</code>.
     *
     * @param other other <code>Object</code> presumed <code>Networking</code> to compare
     * @return the given <code>Object</code> matches this <code>Networking</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Networking) return super.equals(other);
        else return false;
    }

    /**
     * @return a string representation of the Networking
     */
    @Override
    public String toString() {
        return "Event ID: " + getId() + "\n" +
                "Event Type: Networking Event\n" +
                "VIPs Only: " + getVipOnly() + "\n" +
                "Topic: " + getTopic() + "\n" +
                "Location: " + getLocation() + "\n" +
                "Time: " + getTime().getTime().toString() + "\n" +
                "Organizer: " + getOrganizer();
    }

    @Override
    public ArrayList<String> acceptSpeakers(EventVisitor e) {
        return e.visitSpeakers(this);
    }

    @Override
    public String acceptType(EventVisitor e) {
        return e.visitType(this);
    }
}
