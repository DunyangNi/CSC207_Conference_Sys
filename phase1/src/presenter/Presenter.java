package presenter;
import use_cases.*;
import entities.*;
import java.util.*;

import java.util.Calendar;

public class Presenter {

    private EventManager eventmanager;
    private AccountManager accountmanager;
    private SignupManager signupManager;

    public Presenter(EventManager eventmanager, AccountManager accountmanager, SignupManager signupManager) {
        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
        this.signupManager = signupManager;
    }

    public void displayTalkSchedule() {
        HashMap<String[], Calendar> allTalks = eventmanager.fetchSortedTalks();
        System.out.println("Schedule for all talks:\n");
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

    public void displayFriendList(String myusername) {
        ArrayList<String> friendslist = FriendManager.getFriendList(this.accountmanager.fetchAccount(myusername));
        System.out.println("Your Contacts List:\n");
        for(int i = 0; i<= friendslist.size() - 1; i++) {
            System.out.println(friendslist.get(i));
            System.out.println();
        }
    }
}
