package presenter;
import use_cases.*;
import entities.*;
import java.util.*;

import java.util.Calendar;

public class Presenter {

    private EventManager eventmanager;
    private AccountManager accountmanager;

    public Presenter(EventManager eventmanager, AccountManager accountmanager) {

        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
    }

    public void displayTalkSchedule() {
        String[] topiclist = eventmanager.fetchTimeSortedTalkTopics(eventmanager.fetchTalkList());
        String[] speakerlist = eventmanager.fetchTimeSortedTalkSpeakers(eventmanager.fetchTalkList());
        String[] locationlist = eventmanager.fetchTimeSortedLocations(eventmanager.fetchTalkList());
        Calendar[] timelist = eventmanager.fetchTimeSortedTalkTimes(eventmanager.fetchTalkList());

        System.out.println("Schedule for events:");
        System.out.println("");

        Calendar timenow = Calendar.getInstance();
        for(int i = 0; i<= topiclist.length - 1; i++) {
            if(timenow.compareTo(timelist[i]) < 0) {
                System.out.println("Topic: " + topiclist[i]);
                System.out.println("Speaker: " + speakerlist[i]);
                System.out.println("Location: " + locationlist[i]);
                System.out.println("Time: " + timelist[i].getTime());
                System.out.println("");
            }
        }
    }

    public void displayAttendeeTalkSchedule(String attendeeusername) {
        String[] topiclist = eventmanager.fetchTimeSortedTalkTopics(eventmanager.fetchTalkList());
        String[] speakerlist = eventmanager.fetchTimeSortedTalkSpeakers(eventmanager.fetchTalkList());
        String[] locationlist = eventmanager.fetchTimeSortedLocations(eventmanager.fetchTalkList());
        Calendar[] timelist = eventmanager.fetchTimeSortedTalkTimes(eventmanager.fetchTalkList());

        System.out.println("Events you've signed up for:");
        System.out.println("");

        Calendar timenow = Calendar.getInstance();
        for(int i = 0; i<= topiclist.length - 1; i++) {
            if(timenow.compareTo(timelist[i]) < 0) {
                if (SignupManager.isSignedUp(this.eventmanager.fetchTalk(topiclist[i], timelist[i]), this.accountmanager.fetchAttendee(attendeeusername))) {
                    System.out.println("Topic: " + topiclist[i]);
                    System.out.println("Speaker: " + speakerlist[i]);
                    System.out.println("Location: " + locationlist[i]);
                    System.out.println("Time: " + timelist[i].getTime());
                    System.out.println("");
                }

            }
        }
    }

    public void displaySpeakerTalksSchedule(String speakerusername) {
        String[] topiclist = eventmanager.fetchTimeSortedTalkTopics(accountmanager.fetchSpeakerTalkList(speakerusername));
        String[] speakerlist = eventmanager.fetchTimeSortedTalkSpeakers(accountmanager.fetchSpeakerTalkList(speakerusername));
        String[] locationlist = eventmanager.fetchTimeSortedLocations(accountmanager.fetchSpeakerTalkList(speakerusername));
        Calendar[] timelist = eventmanager.fetchTimeSortedTalkTimes(accountmanager.fetchSpeakerTalkList(speakerusername));

        System.out.println("Schedule for events:");
        System.out.println("");

        Calendar timenow = Calendar.getInstance();
        for(int i = 0; i<= topiclist.length - 1; i++) {
            if(timenow.compareTo(timelist[i]) < 0) {
                System.out.println("Topic: " + topiclist[i]);
                System.out.println("Speaker: " + speakerlist[i]);
                System.out.println("Location: " + locationlist[i]);
                System.out.println("Time: " + timelist[i].getTime());
                System.out.println("");
            }
        }
    }

    public void displayFriendList(String myusername) {
        ArrayList<String> friendslist = FriendManager.getFriendList(this.accountmanager.fetchAccount(myusername));
        System.out.println("Your Contacts List:");
        System.out.println("");
        for(int i = 0; i<= friendslist.size() - 1; i++) {
            System.out.println(friendslist.get(i));
            System.out.println("");
        }
    }
}
