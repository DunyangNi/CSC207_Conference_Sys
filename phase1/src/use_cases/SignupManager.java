package use_cases;

import java.util.ArrayList;
import java.util.Calendar;
import entities.*;

/**
 * SignupManager adds given Attendee to a given EventTalk.
 *
 * <pre>
 * Use Case SignupManager
 * Responsibilities:
 *      Can add given Attendee to given EventTalk
 *      Can check whether given EventTalk is full or not
 *      Can check whether given Attendee is already in given EventTalk or not
 *
 * Collaborators:
 *      Attendee, EventTalk
 * </pre>
 */
public class SignupManager {

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Adds given Attendee to Talk.
     * Does nothing if Talk is full or Attendee is already in Talk.
     * @param talk given EventTalk
     * @param attendee given Attendee
     */
    public static void addAttendee(EventTalk talk, Attendee attendee) {
        if (!isFull(talk) && !isSignedUp(talk, attendee)) {
            // Get and copy list of Attendees from EventTalk and list of EventTalks from Attendee
            ArrayList<Attendee> eventAttendees = new ArrayList<>(talk.getAttendees());
            ArrayList<EventTalk> attendeeEvents = new ArrayList<>(attendee.getAttendeeTalks());
            // Modify each list
            eventAttendees.add(attendee);
            attendeeEvents.add(talk);
            // Set new list of Attendees to EventTalk and new list of EventTalks to Attendee
            attendee.setAttendeeTalks(attendeeEvents);
            talk.setAttendees(eventAttendees);
        }
    }

    /**
     * Returns the given seat limit of an EventTalk.
     * @return seat limit
     */
    public static int getSeatLimit() { return 2; }

    /**
     * Returns whether given EventTalk is full.
     * @param talk given EventTalk
     * @return whether talk is full or not
     */
    public static boolean isFull(EventTalk talk) { return talk.getAttendees().size() == getSeatLimit(); }

    /**
     * Returns whether given EventTalk contains a given Attendee.
     * @param talk given EventTalk
     * @param attendee given Attendee
     * @return whether talk contains Attendee or not
     */
    public static boolean isSignedUp(EventTalk talk, Attendee attendee) { return talk.getAttendees().contains(attendee); }
}
