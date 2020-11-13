package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import entities.Account;
import entities.*;

public class AccountManager implements Serializable {

    /**
     * AUTHOR: ANDREW
     * The key represents username (never changes) and maps to an account
     */
    private HashMap<String, Attendee> AttendeeList;
    private HashMap<String, Organizer> OrganizerList;
    private HashMap<String, Speaker> SpeakerList;

    // (NEW!)
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof AccountManager) {
            boolean sameAttendeeList = AttendeeList.equals(((AccountManager) obj).getAttendeeList());
            boolean sameOrganizerList = OrganizerList.equals(((AccountManager) obj).getOrganizerList());
            boolean sameSpeakerList = SpeakerList.equals(((AccountManager) obj).getSpeakerList());
            result = sameAttendeeList && sameOrganizerList && sameSpeakerList;
        }
        return result;
    }

    /**
     * Constructor; we initially have an empty AccountList until we add stuff
     */

    // (NEW!)
    public AccountManager() {
        this(new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    public AccountManager(HashMap<String, Attendee> AttendeeList, HashMap<String, Organizer> OrganizerList, HashMap<String, Speaker> SpeakerList) {
        this.AttendeeList = AttendeeList;
        this.OrganizerList = OrganizerList;
        this.SpeakerList = SpeakerList;

        // NEEDS TO LOAD IN
    }

    public boolean isTalkSpeaker(Speaker speaker, EventTalk talk) {
        return speaker.getUsername().equals(talk.getSpeaker());
    }

    public boolean checkPassword(String username, String password){
        return password.equals(fetchAccount(username).getPassword());
    }

    /**
     * Creates new account and adds to list IF IT WON'T CREATE DUPLICATE USERNAMES. OTHERWISE DO NOTHING
     */

    public boolean AddNewSpeaker(String username, String password, String firstName, String lastName) {
        if (SpeakerList.containsKey(username)) {
            return false;
        }
        Speaker newSpeaker = new Speaker(username, password, firstName, lastName);
        SpeakerList.put(username, newSpeaker);
        return true;
    }

    public boolean AddNewAttendee(String username, String password, String firstName, String lastName) {
        if (AttendeeList.containsKey(username)) {
            return false;
        }
        Attendee newAttendee = new Attendee(username, password, firstName, lastName);
        AttendeeList.put(username, newAttendee);
        return true;
    }

    public boolean AddNewOrganizer(String username, String password, String firstName, String lastName) {
        if (OrganizerList.containsKey(username)) {
            return false;
        }
        Organizer newOrganizer = new Organizer(username, password, firstName, lastName);
        OrganizerList.put(username, newOrganizer);
        return true;
    }

    /**
     * Change firstname, lastname, password. CURRENTLY, WE CANNOT CHANGE USERNAME. IF YOU WANT TO CHANGE,
     * THEN EITHER IT SHOULD BE RESTRICTED TO AN ADMIN USER, OR WE WILL NEED TO CREATE ANOTHER STRICT
     * IDENTIFIER SUCH AS ID.
     */

    public void ChangeFirstName(Account ChangeNameAccount, String NewFirstName) {
        ChangeNameAccount.setFirstName(NewFirstName);
    }

    public void ChangeLastName(Account ChangeNameAccount, String NewLastName) {
        ChangeNameAccount.setLastName(NewLastName);
    }

    public void ChangePassword(Account ChangePasswordAccount, String NewPassword) {
        ChangePasswordAccount.setPassword(NewPassword);
    }

    public Organizer fetchOrganizer(String username) {
        //retrieves organizer with the username
        return OrganizerList.get(username);
    }

    public Speaker fetchSpeaker(String username) {
        return SpeakerList.get(username);
    }

    public Attendee fetchAttendee(String username) {
        return AttendeeList.get(username);
    }

    /**
     * NEW!
     * General Account fetcher.
     * @param username Account username
     * @return Attendee, Speaker, or Organizer object otherwise null
     */
    public Account fetchAccount(String username) {
        if (this.getAttendeeList().containsKey(username)) {
            return this.fetchAttendee(username);
        }
        if (this.getSpeakerList().containsKey(username)) {
            return this.fetchSpeaker(username);
        }
        if (this.getOrganizerList().containsKey(username)) {
            return this.fetchOrganizer(username);
        }
        return null;
    }

    public ArrayList<Integer> fetchSpeakerTalkList(String username) {
        return this.fetchSpeaker(username).getSpeakerTalks();
    }

    public Iterator<String> speakerUsernameIterator() {
        Iterator<Speaker> speakeriterator = this.SpeakerList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while(speakeriterator.hasNext()) {
            usernames.add(speakeriterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public Iterator<String> attendeeUsernameIterator() {
        Iterator<Attendee> speakeriterator = this.AttendeeList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while(speakeriterator.hasNext()) {
            usernames.add(speakeriterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public Iterator<String> organizerUsernameIterator() {
        Iterator<Organizer> speakeriterator = this.OrganizerList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while(speakeriterator.hasNext()) {
            usernames.add(speakeriterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public boolean containsAttendee (String username){
        return AttendeeList.containsKey(username);
    }

    public boolean containsSpeaker (String username){
        return SpeakerList.containsKey(username);
    }

    public boolean containsOrganizer (String username){
        return OrganizerList.containsKey(username);
    }

    public HashMap<String, Speaker> getSpeakerList() {
        return this.SpeakerList;
    }

    public HashMap<String, Attendee> getAttendeeList() {
        return this.AttendeeList;
    }

    public HashMap<String, Organizer> getOrganizerList() {
        return this.OrganizerList;
    }
}
