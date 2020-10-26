import java.util.Calendar;

/**
 * EventTalk represents Talk in an Event
 * <pre>
 *
 * Entity EventTalk
 *
 * Responsibilities:
 * Stores information about the Event.
 * Stores time of Event
 * Can return this information
 * Stores the SpeakerAccounts of the talk
 * Stores the AttendeeAccounts of the talk
 * Stores the OrganizerAccounts of the talk
 * Can return this information
 *
 * Collaborators:
 * Accounts
 * </pre>
 */
public class EventTalk extends Event{
    private Account speaker;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Create an EventTalk with topic, time, speaker, and organizer.
     *
     * <pre>
     * Example
     *
     * Calendar ev_date = Calendar.getInstance();
     * ev_date.set(2020, 03, 23, 10, 12);
     * Account organizer = new Account();
     * Account speaker = new Account();
     *
     * // Create an event
     * EventTalk event1 =
     *         new EventTalk("topic1", ev_date,speaker,organizer);
     * System.out.println(event1);
     * </pre>
     * @param topic topic for the talk
     * @param time time for the talk
     * @param speaker speaker for the talk
     * @param organizer organizer for the talk
     */
    public EventTalk(String topic, Calendar time, String location, Account organizer, Account speaker) {
        super(topic, time, location, organizer);
        this.speaker = speaker;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Gets TalkEvent info
     * @return talk event info
     */
    @Override
    public String toString() {
        return "EventTalk ID: " + getId() + " " +
                super.toString() + "\n"+
                " > " +
                "speaker=" + speaker +
                ", attendees=" + this.getAttendees() +
                ", organizer=" + this.getOrganizer();
    }

    /**
     * Compares events for equality.
     *
     * @param other other message to compare
     * @return True if events are same. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof EventTalk){
            return getTopic().equals(((EventTalk) other).getTopic()) &&
                    getTime().getTimeInMillis() ==
                            ((EventTalk) other).getTime().getTimeInMillis();
        }
        return false;
    }

    //------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------

    /**
     * @return speaker
     */
    public Account getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Account speaker) {
        this.speaker = speaker;
    }

/*
    //------------------------------------------------------------
    // test
    //------------------------------------------------------------
    public static void main(String[] args){
        // Set a date
        Calendar ev_date = Calendar.getInstance();
        ev_date.set(2020, 03, 23, 10, 12);
        Account organizer = new Account();
        Account speaker = new Account();

        // Create an event
        EventTalk event1 =
                new EventTalk("topic1", ev_date,speaker,organizer);
        System.out.println(event1);

        // Set a new date
        Calendar new_date = Calendar.getInstance();
        new_date.set(2020, Calendar.September, 11, 10, 12);
        event1.setTime(new_date);

        // Add attendees
        Account attendee1 = new Account();
        Account attendee2 = new Account();
        event1.addAttendee(attendee1);
        event1.addAttendee(attendee2);
        System.out.println(event1);

        EventTalk event2 =
                new EventTalk("topic1", new_date,speaker,organizer);
        EventTalk event3 =
                new EventTalk("topic1", ev_date,speaker,organizer);

        System.out.println(event1.equals((event2))); // true
        System.out.println(event1.equals((event3))); // false
    }
     */
}
