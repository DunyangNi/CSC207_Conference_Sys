package use_cases;

import Throwables.ConflictException;
import entities.Account;
import entities.Attendee;
import entities.Organizer;
import entities.Speaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AccountManager implements Serializable {

    private HashMap<String, Attendee> attendeeList;
    private HashMap<String, Organizer> organizerList;
    private HashMap<String, Speaker> speakerList;

    public AccountManager() {
        this(new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    public AccountManager(HashMap<String, Attendee> attendeeList, HashMap<String, Organizer> organizerList, HashMap<String, Speaker> speakerList) {
        this.attendeeList = attendeeList;
        this.organizerList = organizerList;
        this.speakerList = speakerList;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AccountManager && (this.getAccountList().equals(((AccountManager) obj).getAccountList())));
    }

    public void AddNewAttendee(String username, String password, String firstName, String lastName) {
        if (getAccountList().containsKey(username)) {
            return;
        }
        Attendee newAttendee = new Attendee(username, password, firstName, lastName);
        attendeeList.put(username, newAttendee);
    }

    public void AddNewSpeaker(String username, String password, String firstName, String lastName) throws ConflictException {
        if (getAccountList().containsKey(username)) {
            throw new ConflictException("Username already exists");
        }
        Speaker newSpeaker = new Speaker(username, password, firstName, lastName);
        speakerList.put(username, newSpeaker);
    }

    public void AddNewOrganizer(String username, String password, String firstName, String lastName) {
        if (getAccountList().containsKey(username)) {
            return;
        }
        Organizer newOrganizer = new Organizer(username, password, firstName, lastName);
        organizerList.put(username, newOrganizer);
    }

    public boolean verifyPassword(String username, String password) {
        return password.equals(fetchAccount(username).getPassword());
    }

//    public void ChangeFirstName(Account ChangeNameAccount, String NewFirstName) {
//        ChangeNameAccount.setFirstName(NewFirstName);
//    }
//
//    public void ChangeLastName(Account ChangeNameAccount, String NewLastName) {
//        ChangeNameAccount.setLastName(NewLastName);
//    }
//
//    public void ChangePassword(Account ChangePasswordAccount, String NewPassword) {
//        ChangePasswordAccount.setPassword(NewPassword);
//    }

    public HashMap<String, Speaker> getSpeakerList() {
        return this.speakerList;
    }

    public HashMap<String, Attendee> getAttendeeList() {
        return this.attendeeList;
    }

    public HashMap<String, Organizer> getOrganizerList() {
        return this.organizerList;
    }

    public HashMap<String, Account> getAccountList() {
        HashMap<String, Account> accountList = new HashMap<>();
        accountList.putAll(this.getAttendeeList());
        accountList.putAll(this.getSpeakerList());
        accountList.putAll(this.getOrganizerList());
        return accountList;
    }

    public boolean containsAttendee(String username) {
        return attendeeList.containsKey(username);
    }

    public boolean containsSpeaker(String username) {
        return speakerList.containsKey(username);
    }

    public boolean containsOrganizer(String username) {
        return organizerList.containsKey(username);
    }

    public boolean containsAccount(String username) {
        return getAccountList().containsKey(username);
    }

//    public Organizer fetchOrganizer(String username) {
//        return organizerList.get(username);
//    }
//
//    public Speaker fetchSpeaker(String username) {
//        return speakerList.get(username);
//    }
//
//    public Attendee fetchAttendee(String username) {
//        return attendeeList.get(username);
//    }

    public Account fetchAccount(String username) {
        return this.getAccountList().get(username);
    }

    public Iterator<String> speakerUsernameIterator() {
        Iterator<Speaker> speakerIterator = this.speakerList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while (speakerIterator.hasNext()) {
            usernames.add(speakerIterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public Iterator<String> attendeeUsernameIterator() {
        Iterator<Attendee> speakerIterator = this.attendeeList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while (speakerIterator.hasNext()) {
            usernames.add(speakerIterator.next().getUsername());
        }
        return usernames.iterator();
    }

    public Iterator<String> organizerUsernameIterator() {
        Iterator<Organizer> speakerIterator = this.organizerList.values().iterator();
        ArrayList<String> usernames = new ArrayList<>();
        while (speakerIterator.hasNext()) {
            usernames.add(speakerIterator.next().getUsername());
        }
        return usernames.iterator();
    }
}
