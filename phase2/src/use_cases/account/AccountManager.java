package use_cases.account;

import entities.account.*;
import exceptions.already_exists.AccountAlreadyExistsException;
import exceptions.already_exists.ObjectAlreadyExistsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Represents the entire system of Accounts and their categorizations.
 */
public class AccountManager implements Serializable {
    private final HashMap<String, Attendee> attendeeHashMap;
    private final HashMap<String, Organizer> organizerHashMap;
    private final HashMap<String, Speaker> speakerHashMap;

    /**
     * Creates a empty <code>AccountManager</code> with empty attendee, organizer, and speaker hashmaps.
     */
    public AccountManager() {
        this(new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    /**
     * Creates a <code>AccountManager</code> with given hashmaps of attendees, organizers, and speakers.
     *
     * @param attendeeHashMap given <code>Attendee HashMap</code>
     * @param organizerHashMap given <code>Organizer HashMap</code>
     * @param speakerHashMap given <code>Speaker HashMap</code>
     */
    public AccountManager(HashMap<String, Attendee> attendeeHashMap, HashMap<String, Organizer> organizerHashMap,
                          HashMap<String, Speaker> speakerHashMap) {
        this.attendeeHashMap = attendeeHashMap;
        this.organizerHashMap = organizerHashMap;
        this.speakerHashMap = speakerHashMap;
    }

    /**
     * @return <code>speakerHashMap</code>
     */
    public HashMap<String, Speaker> getSpeakerHashMap() {
        return this.speakerHashMap;
    }

    /**
     * @return <code>attendeeHashMap</code>
     */
    public HashMap<String, Attendee> getAttendeeHashMap() {
        return this.attendeeHashMap;
    }

    /**
     * @return <code>organizerHashMap</code>
     */
    public HashMap<String, Organizer> getOrganizerHashMap() {
        return this.organizerHashMap;
    }

    /**
     * @return combined HashMaps of attendee, organizer, and speaker
     */
    public HashMap<String, Account> getAccountHashMap() {
        HashMap<String, Account> accountHashMap = new HashMap<>();
        accountHashMap.putAll(this.getAttendeeHashMap());
        accountHashMap.putAll(this.getSpeakerHashMap());
        accountHashMap.putAll(this.getOrganizerHashMap());
        return accountHashMap;
    }

    /**
     * @return a <code>Iterator</code> object containing all speaker usernames.
     */
    public Iterator<String> speakerUsernameIterator() {
        return speakerHashMap.keySet().iterator();
    }

    /**
     * @return a <code>Iterator</code> object containing all attendee usernames.
     */
    public Iterator<String> attendeeUsernameIterator() {
        return attendeeHashMap.keySet().iterator();
    }

    /**
     * Returns true iff password of an associated <code>Account</code> matches given password.
     *
     * @param username given username of associated <code>Account</code>
     * @param password given password to match
     * @return given password matches associated <code>Account</code> password
     */
    public boolean isCorrectPassword(String username, String password) {
        return password.equals(getAccountHashMap().get(username).getPassword());
    }

    /**
     * Returns true iff username is associated with a <code>Attendee Account</code>.
     *
     * @param username given username
     * @return username is associated with a <code>Attendee Account</code>.
     */
    public boolean containsAttendee(String username) {
        return attendeeHashMap.containsKey(username);
    }

    /**
     * Returns true iff username is associated with a <code>Speaker Account</code>.
     *
     * @param username given username
     * @return username is associated with a <code>Speaker Account</code>.
     */
    public boolean containsSpeaker(String username) {
        return speakerHashMap.containsKey(username);
    }

    /**
     * Returns true iff username is associated with a <code>Organizer Account</code>.
     *
     * @param username given username
     * @return username is associated with a <code>Organizer Account</code>.
     */
    public boolean containsOrganizer(String username) {
        return organizerHashMap.containsKey(username);
    }

    /**
     * Returns true iff username is associated with a <code>Account</code>.
     *
     * @param username given username
     * @return username is associated with a <code>Account</code>.
     */

    public boolean containsAccount(String username) {
        return getAccountHashMap().containsKey(username);
    }

    /**
     * @param username username of account
     * @return True iff the accound with the given username is a VIP attendee
     */

    public boolean isVipAttendee(String username) {
        VipVisitor visitor = new VipHelper();
        return attendeeHashMap.get(username).accept(visitor);
    }

    /**
     * Attempts to create a new <code>Attendee Account</code> with given information
     * and stores it in <code>attendeeHashMap</code>.
     *
     * @param username given username
     * @param password given password
     * @throws AccountAlreadyExistsException when there is already an account with the given username
     */
    public void addNewAttendee(String username, String password) throws AccountAlreadyExistsException {
        if (getAccountHashMap().containsKey(username)) throw new AccountAlreadyExistsException();
        Attendee newAttendee = new Attendee(username, password);
        attendeeHashMap.put(username, newAttendee);
    }

    /**
     * Attempts to create a new <code>VIP Attendee Account</code> with given information
     * and stores it in <code>attendeeHashMap</code>.
     *
     * @param username given username
     * @param password given password
     * @throws AccountAlreadyExistsException when there is already an account with the given username
     */
    public void addNewVipAttendee(String username, String password) throws AccountAlreadyExistsException {
        if (getAccountHashMap().containsKey(username)) throw new AccountAlreadyExistsException();
        VipAttendee newAttendee = new VipAttendee(username, password);
        attendeeHashMap.put(username, newAttendee);
    }

    /**
     * Attempts to create a new <code>Speaker Account</code> with given information
     * and store it in <code>speakerHashMap</code>.
     * @param username given username
     * @param password given password
     * @throws AccountAlreadyExistsException upon finding an existing account with the same username
     */
    public void addNewSpeaker(String username, String password) throws AccountAlreadyExistsException {
        if (getAccountHashMap().containsKey(username)) throw new AccountAlreadyExistsException();
        Speaker newSpeaker = new Speaker(username, password);
        speakerHashMap.put(username, newSpeaker);
    }

    /**
     * Creates a new <code>Organizer Account</code> with given information
     * and stores it in <code>organizerHashmap</code>.
     * @param username given username
     * @param password given password
     * @throws AccountAlreadyExistsException when there is already an account with the given username
     */
    public void addNewOrganizer(String username, String password) throws AccountAlreadyExistsException {
        if (getAccountHashMap().containsKey(username)) throw new AccountAlreadyExistsException();
        Organizer newOrganizer = new Organizer(username, password);
        organizerHashMap.put(username, newOrganizer);
    }

    /**
     * Adds the <code>Event</code> of a given ID to the attending list of <code>Attendee</code> with a given username.
     *
     * @param id given ID of an associated <code>Event</code>
     * @param attendee given username of <code>Attendee</code>
     */
    public void addEventToAttend(Integer id, String attendee) {
        attendeeHashMap.get(attendee).getEventsAttending().add(id);
    }

    /**
     * Removes the <code>Event</code> of a given ID from the attending list of <code>Attendee</code> with a given username.
     *
     * @param id given ID of an associated <code>Event</code>
     * @param attendee given username of <code>Attendee</code>
     */
    public void removeEventToAttend(Integer id, String attendee) {
        attendeeHashMap.get(attendee).getEventsAttending().remove(id);
    }

    /**
     * @param attendee given username of <code>Attendee</code>
     * @return a list of the IDs of events that the <code>Attendee</code> is attending
     */
    public ArrayList<Integer> getAttendeeEvents(String attendee) {
        return attendeeHashMap.get(attendee).getEventsAttending();
    }
}
