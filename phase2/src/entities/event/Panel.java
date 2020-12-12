package entities.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Represents a Panel Discussion, a type with two or more speakers, in the system.
 */
public class Panel extends Event implements Serializable, EventAcceptor {
    private ArrayList<String> speakers;

    /**
     * Constructs an instance of <code>Panel</code> based on Strings of information and requirements.
     *
     * @param id                         assigned ID of this <code>Panel</code> event
     * @param topic                      given topic
     * @param time                       given time
     * @param location                   given location
     * @param organizer                  given <code>Organizer</code> username
     * @param speakers                   given <code>ArrayList</code> of speaker usernames
     * @param capacity                   given capacity
     * @param tables                     given number of tables
     * @param chairs                     given number of chairs
     * @param requiresInternet           this <code>Panel</code> event requires internet
     * @param requiresSoundSystem        this <code>Panel</code> event requires sound system
     * @param requiresPresentationScreen this <code>Panel</code> event requires presentation screen
     * @param vipOnly                    this <code>Panel</code> event is accessible to VIPs only
     */
    public Panel(Integer id, String topic, Calendar time, String location, String organizer, ArrayList<String> speakers,
                 Integer capacity, int tables, int chairs, boolean requiresInternet, boolean requiresSoundSystem,
                 boolean requiresPresentationScreen, Boolean vipOnly) {
        super(id, topic, time, location, organizer, capacity, tables, chairs, requiresInternet, requiresSoundSystem,
                requiresPresentationScreen, vipOnly);
        setSpeakers(speakers);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Panel</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Panel</code>.
     *
     * @param other other <code>Object</code> presumed <code>Panel</code> to compare
     * @return the given <code>Object</code> matches this <code>Panel</code>
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Panel) {
            Panel o = (Panel) other;
            if (speakers.size() == o.getSpeakers().size()) {
                ArrayList<String> speakersClone1 = new ArrayList<>(getSpeakers());
                ArrayList<String> speakersClone2 = new ArrayList<>(o.getSpeakers());
                Collections.sort(speakersClone1);
                Collections.sort(speakersClone2);
                return super.equals(other) && speakersClone1.equals(speakersClone2);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @return a string representation of the Panel
     */
    @Override
    public String toString() {
        return "Event ID: " + getId() + "\n" +
                "Event Type: Panel Discussion\n" +
                "VIPs Only: " + getVipOnly() + "\n" +
                "Topic: " + getTopic() + "\n" +
                "Speakers: " + String.join(", ", getSpeakers()) + "\n" +
                "Location: " + getLocation() + "\n" +
                "Time: " + getTime().getTime().toString() + "\n" +
                "Organizer: " + getOrganizer();
    }

    /**
     * @return speakers of this <code>Panel</code>
     */
    public ArrayList<String> getSpeakers() {
        return speakers;
    }

    /**
     * Sets new speakers for this <code>Panel</code>.
     *
     * @param speakers the username of speakers for this <code>Panel</code>
     */
    public void setSpeakers(ArrayList<String> speakers) {
        this.speakers = speakers;
    }

    /**
     * Append a new speaker for this <code>Panel</code>.
     *
     * @param speaker the username of speaker to append for this <code>Panel</code>
     */
    public void addSpeaker(String speaker) {
        if (!speakers.contains(speaker))
            this.speakers.add(speaker);
    }

    /**
     * Removes a speaker
     *
     * @param speaker a speaker to remove
     */
    public void removeSpeaker(String speaker) {
        speakers.remove(speaker);
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
