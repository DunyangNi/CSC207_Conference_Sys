package entities;

import java.io.Serializable;
import java.util.ArrayList;
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
public class EventTalk extends Event implements Serializable {
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
        String uname = getSpeaker() == null ? "" : getSpeaker().getUsername();
        return super.toString() + " speaker (" + uname + ")";
    }

    /**
     * Compares for equality.
     *
     * @param other other message to compare
     * @return True if the same Event and speaker are matched.
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof EventTalk){
            EventTalk o = (EventTalk)other;
            return super.equals(other) &&
                    getSpeaker().getUsername().equals(o.getSpeaker().getUsername());
        }
        return false;
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode(){
        return super.hashCode() / 10 + getSpeaker().getUsername().hashCode() % 100;
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
}
