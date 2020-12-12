package entities.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents any talk in the system, where a talk is a type of event.
 */
public class Talk extends Event implements Serializable, EventAcceptor {
    private String speaker;


    public Talk(Integer id, String topic, Calendar time, String location, String organizer, String speaker, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly) {
        super(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
        this.speaker = speaker;
    }

    /**
     * Compares a given <code>Object</code> with this <code>Talk</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Talk</code>.
     *
     * @param other other <code>Object</code> presumed <code>Talk</code> to compare
     * @return the given <code>Object</code> matches this <code>Talk</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Talk) {
            Talk o = (Talk)other;
            return super.equals(other) && getSpeaker().equals(o.getSpeaker());
        }
        return false;
    }

    /**
     * @return a string representation of the Talk
     */
    @Override
    public String toString() {
        return "Event ID: " + getId() + "\n" +
                "Event Type: Talk\n" +
                "VIPs Only: " + getVipOnly() + "\n" +
                "Topic: " + getTopic() + "\n" +
                "Speaker: " + getSpeaker() + "\n" +
                "Location: " + getLocation() + "\n" +
                "Time: " + getTime().getTime().toString() + "\n" +
                "Organizer: " + getOrganizer();
    }

    /**
     * @return speaker of this <code>Talk</code>
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Sets a new speaker for this <code>Talk</code>.
     *
     * @param speaker the username of new speaker for this <code>Talk</code>
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    /**
     * Returns an <code>ArrayList</code> of speaker usernames of this <code>Event</code>.
     *
     * @param e given <code>EventVisitor</code>
     * @return an <code>ArrayList</code> of speaker usernames
     */
    @Override
    public ArrayList<String> acceptSpeakers(EventVisitor e) {
        return e.visitSpeakers(this);
    }

    /**
     * Returns the event type of this <code>Event</code> as a String.
     *
     * @param e given <code>EventVisitor</code>
     * @return type of this <code>Event</code> in string form
     */
    @Override
    public String acceptType(EventVisitor e) {
        return e.visitType(this);
    }
}
