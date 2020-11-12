package use_cases;

import java.util.ArrayList;
import entities.*;

/**
 * CancelManager removes given Attendee from given EventTalk.
 *
 * <pre>
 * Use Case CancelManager
 * Responsibilities:
 *      Can remove given Attendee to given EventTalk
 *      Can check whether given Attendee is already in given EventTalk or not
 *
 * Collaborators:
 *      Attendee, EventTalk
 * </pre>
 */
public class CancelManager {
    public EventTalk talk;

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Removes given Attendee from Talk.
     * Does nothing if Attendee is not in Talk.
     * @param attendee given Attendee
     */
    public static void removeAttendee(EventTalk talk, Attendee attendee) {
        if (isSignedUp(talk, attendee)) {
            // Get and copy list of Attendees from EventTalk and list of EventTalks from Attendee
            ArrayList<Attendee> eventAttendees = new ArrayList<>(talk.getAttendees());
            ArrayList<EventTalk> attendeeEvents = new ArrayList<>(attendee.getAttendeeTalks());
            // Modify each list
            eventAttendees.remove(attendee);
            attendeeEvents.remove(talk);
            // Set new list of Attendees to EventTalk and new list of EventTalks to Attendee
            attendee.setAttendeeTalks(attendeeEvents);
            talk.setAttendees(eventAttendees);
        }
    }

    /**
     * Returns whether given EventTalk contains a given Attendee.
     * @param talk given EventTalk
     * @param attendee given Attendee
     * @return whether talk contains Attendee or not
     */
    public static boolean isSignedUp(EventTalk talk, Attendee attendee) { return talk.getAttendees().contains(attendee); }
}
