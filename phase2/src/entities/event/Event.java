package entities.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents an event in the system.
 *
 * Fields:
 * topic: the topic of the event
 * location: the location of the event
 * time: the time of the event
 * organizer: the organizer of the event
 * vipOnly: represents whether or not the event is restricted to VIP attendee accounts
 * capacity: the maximum allowed attendees to sign up to this event
 * tables: the tables used in the event
 * chairs: the chairs used in the event
 * requiresInternet: represents if internet is required in the event
 * requiresSoundSystem: represents if sound system is required
 * requiresPresentationScreen: represents if a presentation screen is required
 *
 * attendees: list of usernames of attendees attending the event
 * id: the ID identifer of the event and cannot be changed
 */
public class Event implements Serializable, Comparable<Event>, EventAcceptor {
    private String topic;
    private String location;
    private Calendar time;
    private String organizer;
    private Boolean vipOnly;
    private Integer capacity;
    private Integer tables;
    private Integer chairs;
    private Boolean requiresInternet;
    private Boolean requiresSoundSystem;
    private Boolean requiresPresentationScreen;

    private final ArrayList<String> attendees = new ArrayList<>();
    private final int id;



    public Event(Integer id, String topic, Calendar time, String location, String organizer,
                 int capacity, int tables, int chairs, boolean requiresInternet, boolean requiresSoundSystem,
                 boolean requiresPresentationScreen, Boolean vipOnly) {
        this.topic = topic;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        this.id = id;
        this.vipOnly = vipOnly;
        this.capacity = capacity;
        this.tables = tables;
        this.chairs = chairs;
        this.requiresInternet = requiresInternet;
        this.requiresSoundSystem = requiresSoundSystem;
        this.requiresPresentationScreen = requiresPresentationScreen;
    }

    /**
     * Compares this <code>Event</code> object with a given <code>Event</code> by time.
     * Used to sort Array of <code>Event</code> by time.
     *
     * @param event given <code>Event</code>
     * @return 0 iff times are the same; positive iff this event is after the given;
     * negative iff this event is before the given
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
                    (getLocation().equals(o.getLocation())) &&
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
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "Event ID: " + this.id + "\n" +
                "Event Type: Generic Event\n" +
                "Topic: " + this.topic + "\n" +
                "Location: " + this.location + "\n" +
                "Time: " + this.time.getTime().toString() + "\n" +
                "Organizer: " + this.organizer;
    }

    //------------------------------------------------------------
    // Getters
    //------------------------------------------------------------

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
     * @return locationID of this <code>Event</code>
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
     * @return attendee usernames of this <code>Event</code>
     */
    public ArrayList<String> getAttendees() { return attendees; }

    /**
     * @return VIP restriction status of the event
     */
    public Boolean getVipOnly() { return vipOnly; }

    /**
     * @return capacity of event
     */
    public Integer getCapacity() { return capacity; }

    /**
     * @return number of tables
     */
    public Integer getTables(){
        return this.tables;
    }

    /**
     * @return number chairs
     */
    public Integer getChairs(){
        return this.chairs;
    }

    /**
     * @return internet requirement
     */
    public Boolean getRequiresInternet(){
        return this.requiresInternet;
    }

    /**
     * @return sound system requirement
     */
    public Boolean getRequiresSoundSystem(){
        return this.requiresSoundSystem;
    }

    /**
     * @return presentation screen requirement
     */
    public Boolean getRequiresPresentationScreen(){
        return this.requiresPresentationScreen;
    }

    //------------------------------------------------------------
    // Setters
    //------------------------------------------------------------

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
     * Sets a new location string for this <code>Event</code>.
     *
     * @param location new location string for <code>Event</code>
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

    /**
     * Sets a new VIP restriction status for this <code>Event</code>.
     *
     * @param newBoolean new VIP restriction for this <code>Event</code>
     */
    public void setVipOnly(Boolean newBoolean) { this.vipOnly = newBoolean; }

    /**
     * Sets a capacity for this <code>Event</code>.
     *
     * @param newCapacity new capacityfor this <code>Event</code>
     */
    public void setCapacity(Integer newCapacity) { this.capacity = newCapacity; }

    /**
     * Sets number of tables for this <code>Event</code>.
     *
     * @param newTables new number of tables for this <code>Event</code>
     */
    public void setTables(int newTables) {
        this.tables = newTables;
    }

    /**
     * Sets number of chairs for this <code>Event</code>.
     *
     * @param newChairs new number of chairs for this <code>Event</code>
     */
    public void setChairs(int newChairs) {
        this.chairs = newChairs;
    }

    /**
     * Sets internet requirement for this <code>Event</code>.
     *
     * @param newHasInternet new internet requirement for this <code>Event</code>
     */
    public void setRequiresInternet(boolean newHasInternet) {
        this.requiresInternet = newHasInternet;
    }

    /**
     * Sets sound system requirement for this <code>Event</code>.
     *
     * @param newHasSoundSystem new sound system requirement for this <code>Event</code>
     */
    public void setRequiresSoundSystem(boolean newHasSoundSystem) {
        this.requiresSoundSystem = newHasSoundSystem;
    }

    /**
     * Sets presentation screen requirement for this <code>Event</code>.
     *
     * @param newHasPresentationScreen new presentation screen requirement for this <code>Event</code>
     */
    public void setRequiresPresentationScreen(boolean newHasPresentationScreen) {
        this.requiresPresentationScreen = newHasPresentationScreen;
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

