package use_cases;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
        return (obj instanceof AccountManager && (this.fetchAccountList().equals(((AccountManager) obj).fetchAccountList())));
    }

    public HashMap<String, Speaker> getSpeakerList() {
        return this.speakerList;
    }

    public void setSpeakerList(HashMap<String, Speaker> speakerList) {
        this.speakerList = speakerList;
    }

    public HashMap<String, Attendee> getAttendeeList() {
        return this.attendeeList;
    }

    public void setAttendeeList(HashMap<String, Attendee> attendeeList) {
        this.attendeeList = attendeeList;
    }

    public HashMap<String, Organizer> getOrganizerList() {
        return this.organizerList;
    }

    public void setOrganizerList(HashMap<String, Organizer> organizerList) {
        this.organizerList = organizerList;
    }

    public HashMap<String, Account> fetchAccountList() {
        HashMap<String, Account> accountList = new HashMap<>();
        accountList.putAll(this.getAttendeeList());
        accountList.putAll(this.getSpeakerList());
        accountList.putAll(this.getOrganizerList());
        return accountList;
    }

    public Organizer fetchOrganizer(String username) {
        return organizerList.get(username);
    }

    public Speaker fetchSpeaker(String username) {
        return speakerList.get(username);
    }

    public Attendee fetchAttendee(String username) {
        return attendeeList.get(username);
    }

    public Account fetchAccount(String username) {
        return this.fetchAccountList().get(username);
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
        return fetchAccountList().containsKey(username);
    }

    public void AddNewAttendee(String username, String password, String firstName, String lastName) {
        if (fetchAccountList().containsKey(username)) {
            return;
        }
        Attendee newAttendee = new Attendee(username, password, firstName, lastName);
        attendeeList.put(username, newAttendee);
    }

    public void AddNewSpeaker(String username, String password, String firstName, String lastName) throws ConflictException {
        if (fetchAccountList().containsKey(username)) {
            throw new ConflictException("Username already exists");
        }
        Speaker newSpeaker = new Speaker(username, password, firstName, lastName);
        speakerList.put(username, newSpeaker);
    }

    public void AddNewOrganizer(String username, String password, String firstName, String lastName) {
        if (fetchAccountList().containsKey(username)) {
            return;
        }
        Organizer newOrganizer = new Organizer(username, password, firstName, lastName);
        organizerList.put(username, newOrganizer);
    }

    public void ChangeFirstName(Account ChangeNameAccount, String NewFirstName) {
        ChangeNameAccount.setFirstName(NewFirstName);
    }

    public void ChangeLastName(Account ChangeNameAccount, String NewLastName) {
        ChangeNameAccount.setLastName(NewLastName);
    }

    public void ChangePassword(Account ChangePasswordAccount, String NewPassword) {
        ChangePasswordAccount.setPassword(NewPassword);
    }

    public void deleteAccount(String username) throws ObjectNotFoundException {
        if (!(attendeeList.containsKey(username) || speakerList.containsKey(username)))
            throw new ObjectNotFoundException("Account");
        attendeeList.remove(username);
        speakerList.remove(username);
    }

    public boolean verifyPassword(String username, String password) {
        return password.equals(fetchAccount(username).getPassword());
    }

    public Iterator<String> speakerUsernameIterator() {
//        Iterator<Speaker> speakerIterator = this.speakerList.values().iterator();
//        ArrayList<String> usernames = new ArrayList<>();
//        while (speakerIterator.hasNext()) {
//            usernames.add(speakerIterator.next().getUsername());
//        }
//        return usernames.iterator();
        return speakerList.keySet().iterator();
    }

    public Iterator<String> attendeeUsernameIterator() {
//        Iterator<Attendee> speakerIterator = this.attendeeList.values().iterator();
//        ArrayList<String> usernames = new ArrayList<>();
//        while (speakerIterator.hasNext()) {
//            usernames.add(speakerIterator.next().getUsername());
//        }
//        return usernames.iterator();
        return attendeeList.keySet().iterator();
    }

    public Iterator<String> organizerUsernameIterator() {
//        Iterator<Organizer> speakerIterator = this.organizerList.values().iterator();
//        ArrayList<String> usernames = new ArrayList<>();
//        while (speakerIterator.hasNext()) {
//            usernames.add(speakerIterator.next().getUsername());
//        }
//        return usernames.iterator();
        return organizerList.keySet().iterator();
    }
}
