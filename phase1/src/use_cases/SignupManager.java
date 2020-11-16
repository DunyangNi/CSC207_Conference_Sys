package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;

/**
 * SignupManager adds given Attendee to a given Talk.
 *
 * <pre>
 * Use Case SignupManager
 * Responsibilities:
 *      Can add given Attendee to given Talk
 *      Can check whether given Talk is full or not
 *      Can check whether given Attendee is already in given Talk or not
 *
 * Collaborators:
 *      Attendee, Talk
 * </pre>
 */
public class SignupManager implements Serializable {
    private HashMap<Integer, ArrayList<String>> signups;

    public SignupManager(HashMap<Integer, ArrayList<String>> signups) {
        this.signups = signups;
    }

    public SignupManager() { this(new HashMap<>()); }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    // (NEW!)
    public void addEventKey(Integer id) { signups.put(id, new ArrayList<>()); }

    public void removeEventKey(Integer id) { signups.remove(id); }

    /**
     * Adds given Attendee to Talk.
     * Does nothing if Talk is full or Attendee is already in Talk.
     * @param talk_id given Talk id
     * @param attendee given Attendee id
     */
    public void addAttendee(Integer talk_id, String attendee) throws ConflictException{
        if (!isFull(talk_id) && !isSignedUp(talk_id, attendee)) { signups.get(talk_id).add(attendee); }
        else{
            throw new ConflictException("Cannot sign attendee up");
        }
    }

    public void removeAttendee(Integer talk_id, String attendee) throws ObjectNotFoundException{
        if (isSignedUp(talk_id, attendee)) { signups.get(talk_id).remove(attendee); }
        else{
            throw new ObjectNotFoundException();
        }
    }

    /**
     * Returns the given seat limit of an Talk.
     * @return seat limit
     */
    public int getSeatLimit() { return 2; }

    /**
     * Returns whether given Talk is full.
     * @param talk_id given Talk id
     * @return whether talk is full or not
     */
    public boolean isFull(Integer talk_id) {
        return signups.get(talk_id).size() == getSeatLimit();
    }

    /**
     * Returns whether given Talk contains a given Attendee.
     * @param talk_id given Talk id
     * @param attendee given Attendee id
     * @return whether talk contains Attendee or not
     */
    public boolean isSignedUp(Integer talk_id, String attendee) {
        return signups.get(talk_id).contains(attendee);
    }
}
