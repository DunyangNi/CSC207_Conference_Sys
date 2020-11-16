package presenter;
import use_cases.*;
import entities.*;
import java.util.*;

import java.util.Calendar;

public class Presenter {

    private EventManager eventmanager;
    private SignupManager signupManager;
    private FriendManager friendManager;

    // TODO: 11/16/20 Find a better way to do this
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
