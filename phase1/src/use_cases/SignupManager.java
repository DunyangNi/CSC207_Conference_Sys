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
    private EventManager eventManager;

    public SignupManager(EventManager em) {
        this.eventManager = em;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Adds given Attendee to Talk.
     * Does nothing if Talk is full or Attendee is already in Talk.
     * @param talk_id given EventTalk id
     * @param attendee given Attendee id
     */
    public void addAttendee(Integer talk_id, String attendee) {
        EventTalk talk = eventManager.getTalk(talk_id);
        if (!isFull(talk_id) && !isSignedUp(talk_id, attendee)) {
            ArrayList<String> eventAttendees = talk.getAttendees();
            eventAttendees.add(attendee);
        }
    }

    public void removeAttendee(Integer talk_id, String attendee) {
        EventTalk talk = eventManager.getTalk(talk_id);
        if (isSignedUp(talk_id, attendee)) {
            ArrayList<String> eventAttendees = talk.getAttendees();
            eventAttendees.remove(attendee);
        }
    }

    /**
     * Returns the given seat limit of an EventTalk.
     * @return seat limit
     */
    public int getSeatLimit() { return 2; }

    /**
     * Returns whether given EventTalk is full.
     * @param talk_id given EventTalk id
     * @return whether talk is full or not
     */
    public boolean isFull(Integer talk_id) {
        return eventManager.getTalk(talk_id).getAttendees().size() == getSeatLimit();
    }

    /**
     * Returns whether given EventTalk contains a given Attendee.
     * @param talk_id given EventTalk id
     * @param attendee given Attendee id
     * @return whether talk contains Attendee or not
     */
    public boolean isSignedUp(Integer talk_id, String attendee) {
        return eventManager.getTalk(talk_id).getAttendees().contains(attendee);
    }
}
