package entities.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a Networking Event, a type without a speaker, in the system.
 */
public class Networking extends Event implements Serializable, EventAcceptor {
    /**
     * Constructs an instance of <code>Networking</code> based on Strings of information and requirements.
     *
     * @param id                         assigned ID of this <code>Networking</code> event
     * @param topic                      given topic
     * @param time                       given time
     * @param location                   given location
     * @param organizer                  given <code>Organizer</code> username
     * @param capacity                   given capacity
     * @param tables                     given number of tables
     * @param chairs                     given number of chairs
     * @param requiresInternet           this <code>Networking</code> event requires internet
     * @param requiresSoundSystem        this <code>Networking</code> event requires sound system
     * @param requiresPresentationScreen this <code>Networking</code> event requires presentation screen
     * @param vipOnly                    this <code>Networking</code> event is accessible to VIPs only
     */
    public Networking(Integer id, String topic, Calendar time, String location, String organizer, Integer capacity,
                      int tables, int chairs, boolean requiresInternet, boolean requiresSoundSystem,
                      boolean requiresPresentationScreen, Boolean vipOnly) {
        super(id, topic, time, location, organizer, capacity, tables, chairs, requiresInternet, requiresSoundSystem,
                requiresPresentationScreen, vipOnly);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Networking</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Networking</code>.
     *
     * @param other other <code>Object</code> presumed <code>Networking</code> to compare
     * @return the given <code>Object</code> matches this <code>Networking</code>
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Networking) return super.equals(other);
        else return false;
    }

    /**
     * @return a string representation of this <code>Networking</code> event.
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

    /**
     * Returns an <code>ArrayList</code> of speaker usernames of this <code>Networking</code> event.
     *
     * @param e given <code>EventVisitor</code>
     * @return an <code>ArrayList</code> of speaker usernames
     */
    @Override
    public ArrayList<String> acceptSpeakers(EventVisitor e) {
        return e.visitSpeakers(this);
    }

    /**
     * Returns the event type of this <code>Networking</code> event as a String.
     *
     * @param e given <code>EventVisitor</code>
     * @return type of this <code>Networking</code> event in string form
     */
    @Override
    public String acceptType(EventVisitor e) {
        return e.visitType(this);
    }
}
