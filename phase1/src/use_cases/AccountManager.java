package use_cases;

import java.util.HashMap;
import java.util.Iterator;

import entities.Account;
import entities.*;

public class AccountManager {

    /**
     * AUTHOR: ANDREW
     * The key represents username (never changes) and maps to an account
     */
    private HashMap<String, Attendee> AttendeeList;
    private HashMap<String, Organizer> OrganizerList;
    private HashMap<String, Speaker> SpeakerList;

    /**
     * Constructor; we initially have an empty AccountList until we add stuff
     */

    public AccountManager(HashMap<String, Attendee> AttendeeList, HashMap<String, Organizer> OrganizerList,  HashMap<String, Speaker> SpeakerList){
        this.AttendeeList = AttendeeList;
        this.OrganizerList = OrganizerList;
        this.SpeakerList = SpeakerList;

        // NEEDS TO LOAD IN
    }

    /**
     * Getter
     */

    public HashMap<String, Speaker> getSpeakerlist(){
        return this.SpeakerList;
    }
    public HashMap<String, Attendee> getAttendeelist() { return this.AttendeeList;}
    public HashMap<String, Organizer> getOrganizerList() { return this.OrganizerList;}

    /**
     * Creates new account and adds to list IF IT WON'T CREATE DUPLICATE USERNAMES. OTHERWISE DO NOTHING
     */

    public void AddNewSpeaker(String username, String password, String firstName, String lastName){
        if (! (SpeakerList.containsKey(username))){
            SpeakerList.put(username, new Speaker(username,password,firstName,lastName));
        }
    }
    public void AddNewAttendee(String username, String password, String firstName, String lastName){
        if (! (AttendeeList.containsKey(username))){
            AttendeeList.put(username, new Attendee(username,password,firstName,lastName));
        }
    }
    public void AddNewOrganizer(String username, String password, String firstName, String lastName){
        if (! (OrganizerList.containsKey(username))){
            OrganizerList.put(username, new Organizer(username,password,firstName,lastName));
        }
    }

    /**
     * Change firstname, lastname, password. CURRENTLY, WE CANNOT CHANGE USERNAME. IF YOU WANT TO CHANGE,
     * THEN EITHER IT SHOULD BE RESTRICTED TO AN ADMIN USER, OR WE WILL NEED TO CREATE ANOTHER STRICT
     * IDENTIFIER SUCH AS ID.
     */

    public void ChangeFirstName(Account ChangeNameAccount, String NewFirstName){
        ChangeNameAccount.setFirstName(NewFirstName);
    }

    public void ChangeLastName(Account ChangeNameAccount, String NewLastName){
        ChangeNameAccount.setLastName(NewLastName);
    }


    public void ChangePassword(Account ChangePasswordAccount, String NewPassword){
        ChangePasswordAccount.setPassword(NewPassword);
    }

    public Organizer fetchOrganizer(String username) {
        //retrieves organizer with the username
        return OrganizerList.get(username);
    }
    public Speaker fetchSpeaker(String username) {
        return SpeakerList.get(username);
    }

    public Iterator<Speaker> speakerIterator() {
        return this.SpeakerList.values().iterator();
    }

    public Iterator<Attendee> attendeeIterator() {
        return this.AttendeeList.values().iterator();
    }
    public Iterator<Organizer> organizerIterator() {
        return this.OrganizerList.values().iterator();
    }
    public Attendee fetchAttendee(String username) {
        return AttendeeList.get(username);
    }

}
