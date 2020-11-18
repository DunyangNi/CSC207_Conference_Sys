package presenter;
import use_cases.*;
import entities.*;
import java.util.*;

import java.util.Calendar;

public class Presenter {
    // TODO: 11/17/20 Consider whether these fields are necessary or what other fields might be required
    private EventManager eventmanager;
    private SignupManager signupManager;
    private FriendManager friendManager;

    // TODO: 11/16/20 Find a better way to do this?
    public Presenter() {
    }

    public Presenter(EventManager eventManager, FriendManager friendManager, SignupManager signupManager) {
        this.eventmanager = eventManager;
        this.signupManager = signupManager;
        this.friendManager = friendManager;
    }

    public void displayPrompt(String output) {
        System.out.println(output);
    }

    public void displayOrganizerMenu() {
        System.out.println("=====[ORGANIZER MENU]=====");
        System.out.println("[ACCOUNT COMMANDS]");
        System.out.println("0  = Logout");
        System.out.println("2  = Register a new speaker account");
        System.out.println("16 = View list of all accounts");

        System.out.println("\n[CONTACT COMMANDS]");
        System.out.println("11 = Add a contact");
        System.out.println("12 = Remove a contact");
        System.out.println("13 = View list of all contacts");

        System.out.println("\n[CONVERSATION COMMANDS]");
        System.out.println("7  = Message a speaker");
        System.out.println("9  = Message an attendee");
        System.out.println("6  = Message all speakers");
        System.out.println("8  = Message all attendees");
        System.out.println("14 = View your conversations");

        System.out.println("\n[EVENT COMMANDS]");
        System.out.println("1  = Register a new event room");
        System.out.println("15 = View list of all rooms");
        System.out.println("3  = Register a new event");
        System.out.println("4  = Cancel an event");
        System.out.println("5  = Reschedule an event");
        System.out.println("10 = View talk schedule");
        System.out.println("==========================");
        System.out.println("Enter a command (1-16):");
    }
    
    public void displayTalkSchedule() {
        HashMap<String[], Calendar> allTalks = eventmanager.fetchSortedTalks();
        System.out.println("Schedule for all talks:\n");
        if (allTalks.keySet().isEmpty()) System.out.println("Nothing!");
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : allTalks.keySet()) {
            if(timeNow.compareTo(allTalks.get(eventInfo)) < 0) {
                System.out.println("ID: " + eventInfo[4]);
                System.out.println("Topic: " + eventInfo[0]);
                System.out.println("Speaker: " + eventInfo[1]);
                System.out.println("Location: " + eventInfo[2]);
                System.out.println("Time: " + eventInfo[3]);
                System.out.println();
            }
        }
    }

    public void displayAttendeeTalkSchedule(String attendee) {
        HashMap<String[], Calendar> attendeeTalks = eventmanager.fetchSortedTalks();
        System.out.println("Talks you've signed up for:\n");
        Calendar timeNow = Calendar.getInstance();
        if (attendeeTalks.keySet().isEmpty()) System.out.println("Nothing!");
        for(String[] eventInfo : attendeeTalks.keySet()) {
            if(timeNow.compareTo(attendeeTalks.get(eventInfo)) < 0 &&
                    signupManager.isSignedUp(Integer.parseInt(eventInfo[4]), attendee)) {
                System.out.println("ID: " + eventInfo[4]);
                System.out.println("Topic: " + eventInfo[0]);
                System.out.println("Speaker: " + eventInfo[1]);
                System.out.println("Location: " + eventInfo[2]);
                System.out.println("Time: " + eventInfo[3]);
                System.out.println();
            }
        }
    }

    public void displaySpeakerTalksSchedule(String speaker) {
        HashMap<String[], Calendar> speakerTalks = eventmanager.fetchSortedTalks(speaker);
        System.out.println("Schedule for talks you're speaking at:\n");
        if (speakerTalks.keySet().isEmpty()) System.out.println("Nothing!");
        Calendar timeNow = Calendar.getInstance();
        for(String[] eventInfo : speakerTalks.keySet()) {
            if(timeNow.compareTo(speakerTalks.get(eventInfo)) < 0) {
                System.out.println("ID: " + eventInfo[4]);
                System.out.println("Topic: " + eventInfo[0]);
                System.out.println("Speaker: " + eventInfo[1]);
                System.out.println("Location: " + eventInfo[2]);
                System.out.println("Time: " + eventInfo[3]);
                System.out.println();
            }
        }
    }

    public void displayFriendList(String user) {
        ArrayList<String> selectedFriends = friendManager.getFriendList(user);
        System.out.println("Your Contacts List:\n");
        if (selectedFriends.isEmpty()) { System.out.println("No one!"); }
        else { for (String friend : selectedFriends) { System.out.println(friend); } }
    }
}
