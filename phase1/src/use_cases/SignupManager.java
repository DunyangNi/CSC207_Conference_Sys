package use_cases;

import java.util.ArrayList;
import entities.Attendee;
import entities.EventTalk;

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
            ArrayList<String> eventAttendees = talk.getAttendees();
            eventAttendees.add(attendee.getUsername());
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
    public static boolean isSignedUp(EventTalk talk, Attendee attendee) {
        return talk.getAttendees().contains(attendee.getUsername());
    }
}
