package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import entities.*;

public class AccountManager implements Serializable {

    /**
     * AUTHOR: ANDREW
     * The key represents username (never changes) and maps to an account
     */
    private HashMap<String, Attendee> attendeeList;
    private HashMap<String, Organizer> organizerList;
    private HashMap<String, Speaker> speakerList;

    // (NEW!)
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof AccountManager) {
            boolean sameAttendeeList = attendeeList.equals(((AccountManager) obj).getAttendeeList());
            boolean sameOrganizerList = organizerList.equals(((AccountManager) obj).getOrganizerList());
            boolean sameSpeakerList = speakerList.equals(((AccountManager) obj).getSpeakerList());
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

    public AccountManager(HashMap<String, Attendee> AttendeeList, HashMap<String, Organizer> OrganizerList,
                          HashMap<String, Speaker> SpeakerList) {
        this.attendeeList = AttendeeList;
        this.organizerList = OrganizerList;
        this.speakerList = SpeakerList;

        // NEEDS TO LOAD IN
    }

    // isTalkSpeaker() has been moved to EventManager instead.

    public boolean checkPassword(String username, String password){
        return password.equals(fetchAccount(username).getPassword());
    }

    /**
     * Creates new account and adds to list IF IT WON'T CREATE DUPLICATE USERNAMES. OTHERWISE DO NOTHING
     */

    public boolean AddNewSpeaker(String username, String password, String firstName, String lastName) {
        if (speakerList.containsKey(username)) {
            return false;
        }
        Speaker newSpeaker = new Speaker(username, password, firstName, lastName);
        speakerList.put(username, newSpeaker);
        return true;
    }

    public boolean AddNewAttendee(String username, String password, String firstName, String lastName) {
        if (attendeeList.containsKey(username)) {
            return false;
        }
        Attendee newAttendee = new Attendee(username, password, firstName, lastName);
        attendeeList.put(username, newAttendee);
        return true;
    }

    public boolean AddNewOrganizer(String username, String password, String firstName, String lastName) {
        if (organizerList.containsKey(username)) {
            return false;
        }
        Organizer newOrganizer = new Organizer(username, password, firstName, lastName);
        organizerList.put(username, newOrganizer);
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
        return organizerList.get(username);
    }

    public Speaker fetchSpeaker(String username) {
        return speakerList.get(username);
    }

    public Attendee fetchAttendee(String username) {
        return attendeeList.get(username);
    }

    /**
     * NEW!
     * General Account fetcher.
     * @param username Account username
     * @return Attendee, Speaker, or Organizer object otherwise null
     */
    public Account fetchAccount(String username) {
        return this.fetchAccountList().get(username);
    }

    /**
     * NEW!
     * Redesigned implementation of accountList, now an instance variable.
     * @return a HashMap<String, Account> of all username: Accounts pairs.
     */
    public HashMap<String, Account> fetchAccountList() {
        HashMap<String, Account> accountList = new HashMap<>();
        accountList.putAll(this.getAttendeeList());
        accountList.putAll(this.getSpeakerList());
        accountList.putAll(this.getOrganizerList());
        return accountList;
    }

    // TODO: 11/13/20 This method is obsolete now that we've removed Speaker.speakerTalks. Will need to resolve usage errors in Presenter. 
//    public ArrayList<Integer> fetchSpeakerTalkList(String username) {
//        return this.fetchSpeaker(username).getSpeakerTalks();
//    }
    // the issue is now fixed, as this responsibility is now in EventManager as fetchSortedTalks(speaker)

    public Iterator<String> speakerUsernameIterator() {
        Iterator<Speaker> speakeriterator = this.speakerList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while(speakeriterator.hasNext()) {
            usernames.add(speakeriterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public Iterator<String> attendeeUsernameIterator() {
        Iterator<Attendee> speakeriterator = this.attendeeList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while(speakeriterator.hasNext()) {
            usernames.add(speakeriterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public Iterator<String> organizerUsernameIterator() {
        Iterator<Organizer> speakeriterator = this.organizerList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while(speakeriterator.hasNext()) {
            usernames.add(speakeriterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public boolean containsAttendee (String username){
        return attendeeList.containsKey(username);
    }

    public boolean containsSpeaker (String username){
        return speakerList.containsKey(username);
    }

    public boolean containsOrganizer (String username){
        return organizerList.containsKey(username);
    }

    // (NEW!)
    public boolean doesNotContainAccount(String user) { return !fetchAccountList().containsKey(user); }

    public HashMap<String, Speaker> getSpeakerList() {
        return this.speakerList;
    }

    public HashMap<String, Attendee> getAttendeeList() {
        return this.attendeeList;
    }

    public HashMap<String, Organizer> getOrganizerList() {
        return this.organizerList;
    }
}
