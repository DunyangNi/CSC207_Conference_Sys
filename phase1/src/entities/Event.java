package entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents any event in the system.
 */
public class Event implements Serializable, Comparable<Event> {
    private String topic;
    private String location;
    private Calendar time;
    private String organizer;
    private final int id;

    /**
     * Creates an instance of <code>Event</code> with an assigned integer ID and given information.
     *
     * @param id assigned ID
     * @param topic given topic
     * @param time given time
     * @param location given location
     * @param organizer given <code>Organizer</code> username
     */
    public Event(Integer id, String topic, Calendar time, String location, String organizer) {
        this.topic = topic;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        this.id = id;
    }

    /**
     * Compares this <code>Event</code> object with a given <code>Event</code> by time.
     * Used to sort Array of <code>Event</code> by time.
     *
     * @param event given <code>Event</code>
     * @return 0 iff times are the same; positive iff this event is after the given; negative iff this event is before the given
     */
    public int compareTo(Event event) {
        return this.time.compareTo(event.time);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Event</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Event</code>.
     *
     * @param other other <code>Object</code> presumed <code>Event</code> to compare
     * @return the given <code>Object</code> matches this <code>Event</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Event) {
            Event o = (Event) other;
            return getTopic().equals(o.getTopic()) &&
                    getTime().getTimeInMillis() == o.getTime().getTimeInMillis() &&
                    getLocation().equals(o.getLocation()) &&
                    getOrganizer().equals(o.getOrganizer());
        }
        return false;
    }

    /**
     * @return hash code of this <code>Event</code>
     */
    @Override
    public int hashCode(){
        return topic.hashCode() / 10 + getOrganizer().hashCode() % 1000;
    }

    /**
     * @return topic of this <code>Event</code>
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @return time of this <code>Event</code>
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * @return location of this <code>Event</code>
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return organizer username of this <code>Event</code>
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * @return ID of this <code>Event</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new topic for this <code>Event</code>.
     *
     * @param topic new topic of <code>Event</code>
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Sets a new time for this <code>Event</code>.
     *
     * @param time new starting time for <code>Event</code>
     */
    public void setTime(Calendar time) {
        this.time = time;
    }

    /**
     * Sets a new location for this <code>Event</code>.
     *
     * @param location new location of <code>Event</code>
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets a new organizer for this <code>Event</code>.
     *
     * @param organizer new organizer username for this <code>Event</code>
     */
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
}

