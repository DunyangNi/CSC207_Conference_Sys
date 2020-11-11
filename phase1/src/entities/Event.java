package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Event represents any events such as talk, presentation, and so on
 *
 *<pre>
 * Entity Event
 *
 * Responsibilities:
 * Stores information about the Event.
 * Stores time of Event
 * Can return this information
 *
 * Collaborators:
 * None
 *</pre>
 */
public class Event implements Serializable, Comparable<Event> {
    private String topic;
    private String location;
    private Calendar time;
    private Account organizer;
    private ArrayList<Attendee> attendees = new ArrayList<>();
    private static int sid = 0;
    private int id;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Creates an event with topic and time.
     * @param topic event topic.
     * @param time event time.
     */
    public Event(String topic, Calendar time, String location, Account organizer) {
        this.topic = topic;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        sid++;
        id = sid;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Gets the Event info.
     * @return  Event info.
     */
    @Override
    public String toString(){
        int yyyy = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH);
        int date = time.get(Calendar.DAY_OF_MONTH);
        int hh = time.get(Calendar.HOUR_OF_DAY);
        int mm = time.get(Calendar.MINUTE);

        String yyyy_mm_dd = yyyy + "-" + month + "-" + date;
        String hh_mm = hh + ":" + mm;
        String uname = getOrganizer() == null ? "" : getOrganizer().getUsername();
        String atd = "";
        if (getAttendees().size() != 0){
            for (Account atd1: getAttendees()){
                atd += atd1.getUsername() + " ";
            }
            atd = atd.substring(0, atd.length()-1);
        }
        return id + ": (" + topic + ") "+
                "(" + yyyy_mm_dd + " " + hh_mm +
                ") organizer (" + uname + ") Attendees (" + atd + ")";

    }

    public int compareTo(Event event) {
        return this.time.compareTo(event.time);
    }

    /**
     * Reset the ID counter for testing purpose.
     */
    public static void resetSid(){
        sid = 0;
    }

    /**
     * Compares for equality.
     *
     * @param other other message to compare
     * @return True if the same topic, time, location, and organizer are matched.
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof Event){
            Event o = (Event)other;
            return
                    getTopic().equals(o.getTopic()) &&
                            getTime().getTimeInMillis() == o.getTime().getTimeInMillis() &&
                            getLocation().equals(o.getLocation()) &&
                            getOrganizer().getUsername().equals(o.getOrganizer().getUsername());
        }
        return false;
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode(){
        return topic.hashCode() / 10 + getOrganizer().getUsername().hashCode() % 1000;
    }

    //------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------

    /**
     * @return topic
     */
    public String getTopic() {
        return topic;
    }
    /**
     * @return time
     */
    public Calendar getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public Account getOrganizer() {
        return organizer;
    }

    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }

    public int getId() {
        return id;
    }

    /**
     * @param topic event topic
     */

    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @param time event start time
     */
    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOrganizer(Account organizer) {
        this.organizer = organizer;
    }

    public void setAttendees(ArrayList<Attendee> attendees) {
        this.attendees = attendees;
    }

    public void setId(int id) {
        this.id = id;
    }

}

