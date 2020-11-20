package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;

/**
 * Represents the entire system of Event and Attendee relationships.
 */
public class SignupManager implements Serializable {
    private final HashMap<Integer, ArrayList<String>> signups;

    /**
     * Creates a SignupManager with a given <code>HashMap</code>
     * of <code>Event</code> ids to <code>ArrayList</code> of Attendee usernames.
     * @param signups given <code>HashMap</code> of <code>Event</code> ids to <code>ArrayList</code> of Attendee usernames.
     */
    public SignupManager(HashMap<Integer, ArrayList<String>> signups) {
        this.signups = signups;
    }

    /**
     * Creates a SignupManager with an empty HashMap.
     */
    public SignupManager() { this(new HashMap<>()); }

    /**
     * Attempts to return an ArrayList of usernames of Attendees associated with a Talk.
     * @param id given ID of a Talk
     * @return ArrayList of usernames of Attendees associated with a Talk
     * @throws ObjectNotFoundException upon Talk not being found.
     */
    public ArrayList<String> fetchTalkAttendeeList(Integer id) throws ObjectNotFoundException {
        if (!signups.containsKey(id))
            throw new ObjectNotFoundException("Talk");
        return signups.get(id);
    }

    /**
     * Returns true iff given Talk is full.
     * @param talk_id given Talk id
     * @return given Talk is full
     */
    public boolean isFull(Integer talk_id) {
        return signups.get(talk_id).size() == getSeatLimit();
    }

    private int getSeatLimit() { return 2; }

    /**
     * Returns true iff given Talk contains a given Attendee.
     * @param talk_id given Talk id
     * @param attendee given Attendee id
     * @return talk contains Attendee
     */
    public boolean isSignedUp(Integer talk_id, String attendee) {
        return signups.get(talk_id).contains(attendee);
    }

    /**
     * Add a new key a ID of an associated Event.
     *
     * @param id of associated Event
     */
    public void addEventKey(Integer id) { signups.put(id, new ArrayList<>()); }

    /**
     * Removes a new key a ID of an associated Event.
     *
     * @param id of associated Event
     */
    public void removeEventKey(Integer id) { signups.remove(id); }

    /**
     * Attempts to add given Attendee to attend Talk.
     *
     * @param talk_id given ID of an associated Talk
     * @param attendee given username of Attendee
     * @throws ConflictException upon Talk being full of Attendees or Attendee is already signed up for Talk.
     * @throws ObjectNotFoundException upon Talk not being found
     */
    public void addAttendee(Integer talk_id, String attendee) throws ConflictException, ObjectNotFoundException {
        if (!signups.containsKey(talk_id))
            throw new ObjectNotFoundException("Talk");
        if (isFull(talk_id))
            throw new ConflictException("Talk is full.");
        if (isSignedUp(talk_id, attendee))
            throw new ConflictException("You are already signed up for this Talk.");
        signups.get(talk_id).add(attendee);
    }

    /**
     * Attempts to remove given Attendee from given Talk.
     *
     * @param talk_id given ID of an associated Talk
     * @param attendee given username of Attendee
     * @throws ObjectNotFoundException upon Talk or User not being found
     */
    public void removeAttendee(Integer talk_id, String attendee) throws ObjectNotFoundException {
        if (!signups.containsKey(talk_id))
            throw new ObjectNotFoundException("Talk");
        if (!isSignedUp(talk_id, attendee))
            throw new ObjectNotFoundException("User");
        signups.get(talk_id).remove(attendee);
    }
}
