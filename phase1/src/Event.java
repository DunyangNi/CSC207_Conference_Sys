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
public class Event {
    private String topic;
    private Calendar time;
    private String location;
    private Account organizer;
    private ArrayList<Account> attendees = new ArrayList<>();
    private static int id = 0;

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
        id++;
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

        return "(" + topic + ") "+
                "(" + yyyy_mm_dd + " " + hh_mm + ")";

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

    public ArrayList<Account> getAttendees() {
        return attendees;
    }

    public static int getId() {
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

    public void setAttendees(ArrayList<Account> attendees) {
        this.attendees = attendees;
    }

    public static void setId(int id) {
        Event.id = id;
    }
}

