package controller;
import entities.Speaker;
import use_cases.*;
import entities.*;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Calendar;
import java.util.TimeZone;
import presenter.*;
import java.util.*;
import java.lang.*;

public class OrganizerController {
    private String username;
    private EventManager eventmanager;
    private AccountManager accountmanager;
    private ConversationManager conversationManager;
    private Presenter presenter;

    private Calendar timeoftalkrequesthelper(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Specify the year of the talk");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.println("Specify the month of the talk (1-12)");
        int month = sc.nextInt();
        sc.nextLine();
        System.out.println("Specify the day of the month of the talk (1-31)");
        int dayofmonth = sc.nextInt();
        sc.nextLine();
        System.out.println("Specify the hour of the day of the talk (0-23)");
        int hourofday = sc.nextInt();
        sc.nextLine();

        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, dayofmonth);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.HOUR_OF_DAY, hourofday);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        return time;
    }

    public OrganizerController(String username, EventManager eventmanager,
                               AccountManager accountmanager, ConversationManager conversationManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
        this.conversationManager = conversationManager;
        this.presenter = new Presenter(eventmanager,accountmanager);
    }
    public void addNewLocation(String location) {
        this.eventmanager.addLocation(location);
    }
    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
        this.accountmanager.AddNewSpeaker(username, password, firstname, lastname);
    }


    public void scheduleSpeaker(int year, int dayofmonth, int month, int hourofday, String topic, String location, String speakerusername) {
        //requires a method that allows you to query speakers based on username
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, dayofmonth);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.HOUR_OF_DAY, hourofday);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        this.eventmanager.AddNewEvent(topic, time, location, this.accountmanager.fetchOrganizer(this.username), this.accountmanager.fetchSpeaker(speakerusername));
    }
    public void cancelTalk(String topic, Calendar time) {
        //needs a method for encoding string as event
        this.eventmanager.cancelTalk(this.eventmanager.fetchTalk(topic, time));
    }

    public void rescheduleTalk(String topic, Calendar oldtime, int year, int dayofmonth, int month, int hourofday) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        time.setTimeZone(tz);
        time.set(Calendar.DAY_OF_MONTH, dayofmonth);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.HOUR_OF_DAY, hourofday);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        this.eventmanager.ChangeTime(this.eventmanager.fetchTalk(topic, oldtime), time);
    }
    public void messageAllSpeakers(String message) {
        Iterator<String> speakerusernameiterator = this.accountmanager.speakerUsernameIterator();
        while( speakerusernameiterator.hasNext()){
            conversationManager.sendMessage(this.username, speakerusernameiterator.next(), message);
        }
    }

    public void messageSpeaker(String message, String speakerUsername) {
        conversationManager.sendMessage(this.username, speakerUsername, message);
    }

    public void messageAllAttendees(String message) {
        Iterator<String> attendeeusernameiterator = this.accountmanager.attendeeUsernameIterator();
        while(attendeeusernameiterator.hasNext()){
            conversationManager.sendMessage(this.username, attendeeusernameiterator.next(), message);
        }
    }

    public void messageAttendee(String message, String attendeeUsername) {
        conversationManager.sendMessage(this.username, attendeeUsername, message);
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void addFriend(String accountusername) {
        FriendManager.AddFriend(this.accountmanager.fetchAccount(this.username), this.accountmanager.fetchAccount(accountusername));
    }

    public void removeFriend(String accountusername) {
        FriendManager.RemoveFriend(this.accountmanager.fetchAccount(this.username), this.accountmanager.fetchAccount(accountusername));
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessages) {
        if (numMessages < 0) { System.out.println("This is an invalid number"); }
        else {
            String msgToPrint;
            ArrayList<Integer> convo = conversationManager.getConversationMessages(this.username, recipient);
            System.out.println("Your recent " + numMessages + " messages with " + recipient + ":");
            System.out.println();
            int recent_num = Math.min(numMessages, convo.size());
            for (int i = 0; i < recent_num; i++) {
                msgToPrint = conversationManager.messageToString(convo.get(numMessages - recent_num - 1 + i));
                System.out.println(msgToPrint);
                System.out.println();
            }
        }
    }

    public void seeLocationList() {
        ArrayList<String> locations = this.eventmanager.fetchLocations();
        System.out.println("Locations:");
        System.out.println();
        for(int i = 0; i<=locations.size() - 1; i++) {
            System.out.println(locations.get(i));
        }
    }

    public void runOrganizerInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true;
        while(loop_on){
            System.out.println("What would you like to do?");
            System.out.println("1 = enter a room into the system");
            System.out.println("2 = create a new speaker account");
            System.out.println("3 = schedule a speaker for a talk");
            System.out.println("4 = cancel an event");
            System.out.println("5 = reschedule an event");
            System.out.println("6 = message all speakers");
            System.out.println("7 = message an individual speaker");
            System.out.println("8 = message all attendees");
            System.out.println("9 = message an individual attendee");
            System.out.println("10 = see talk schedule");
            System.out.println("11 = add a contact");
            System.out.println("12 = remove a contact");
            System.out.println("13 = view contacts list");
            System.out.println("14 = view your conversation with someone");
            System.out.println("15 = see the list of rooms");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                System.out.println("Specify location:");
                String location = sc.nextLine();
                this.addNewLocation(location);
            }
            else if(choice == 2) {
                System.out.println("Specify account username");
                //sc.nextLine();
                String username = sc.nextLine();

                System.out.println("Specify account password");
                //sc.nextLine();
                String password = sc.nextLine();

                System.out.println("What is speaker's first name?");
                //sc.nextLine();
                String firstname = sc.nextLine();

                System.out.println("What is speaker's last name?");
                //sc.nextLine();
                String lastname = sc.nextLine();

                this.accountmanager.AddNewSpeaker(username, password, firstname, lastname);
            }
            else if(choice == 3) {
                //public void scheduleSpeaker(int year, int dayofmonth, int month, int hourofday, String topic, String location, String speakerusername) {
                System.out.println("Specify the username of talk speaker");
                //String line1 = sc.nextLine();
                String username = sc.nextLine();
                System.out.println("Specify the location of the talk");
                //line1 = sc.nextLine();
                String location = sc.nextLine();
                System.out.println("Specify the topic of the talk");
                //line1 = sc.nextLine();
                String topic = sc.nextLine();
                System.out.println("Specify the year of the talk");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the month of the talk (1-12)");
                int month = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the day of the month of the talk (1-31)");
                int dayofmonth = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the hour of the day of the talk (0-23)");
                int hourofday = sc.nextInt();
                sc.nextLine();
                this.scheduleSpeaker(year, dayofmonth, month, hourofday,topic, location, username);
                this.scheduleSpeaker(year, dayofmonth, month, hourofday, topic, location, username);
            }
            else if(choice == 4) {
                System.out.println("Specify the exact topic of the talk you wish to cancel");
                String topic = sc.nextLine();

                Calendar time = this.timeoftalkrequesthelper();

                this.cancelTalk(topic, time);
            }
            else if(choice == 5) {
                // rescheduleTalk(String topic, int year, int dayofmonth, int month, int hourofday)
                System.out.println("Specify the exact topic of the talk you wish to reschedule");
                //String line1 = sc.nextLine();
                String topic = sc.nextLine();

                Calendar oldtime = this.timeoftalkrequesthelper();

                System.out.println("Specify the year you would like to reschedule this talk to");
                int year = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the month you would like to reschedule to (1-12)");
                int month = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the day of the month you would like to reschedule to (1-31)");
                int dayofmonth = sc.nextInt();
                sc.nextLine();
                System.out.println("Specify the hour of the day you would like to reschedule to (0-23)");
                int hourofday = sc.nextInt();
                sc.nextLine();
                this.rescheduleTalk(topic, oldtime, year, dayofmonth, month, hourofday);
            }
            else if(choice == 6) {
                //messageAllSpeakers(String message)
                System.out.println("Specify the message that you want to send to all speakers");
                //String line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAllSpeakers(message);
            }
            else if(choice == 7) {
                //messageSpeaker(String message, String speakerusername)
                System.out.println("Specify the username of the speaker you want to message");
                //String line1 = sc.nextLine();
                String username = sc.nextLine();
                System.out.println("Specify the message you want to send the speaker");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageSpeaker(message, username);
            }
            else if(choice == 8) {
                System.out.println("Specify the message that you want to send to all attendees");
                //String line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAllAttendees(message);
            }
            else if(choice == 9) {
                System.out.println("Specify the username of the attendee you want to message");
                //String line1 = sc.nextLine();
                String username = sc.nextLine();
                System.out.println("Specify the message you want to send the attendee");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAttendee(message, username);
            }
            else if(choice == 10) {
                this.SeeTalkSchedule();
            }
            else if(choice == 11) {
                System.out.println("Specify username of contact to add");
                //String line1 = sc.nextLine();
                String friendusername = sc.nextLine();
                this.addFriend(friendusername);

            }
            else if(choice == 12) {
                System.out.println("Specify username of contact to remove");
                //String line1 = sc.nextLine();
                String usernametoremove = sc.nextLine();
                this.removeFriend(usernametoremove);

            }

            else if(choice == 13) {
                this.seeFriendList();

            }
            else if(choice == 14) {
                //viewMessagesFrom(String recipientusername, int nummessages)
                System.out.println("Specify username of user you would like to view your conversation with");
                //String line1 = sc.nextLine();
                String user = sc.nextLine();
                System.out.println("How many past messages would you like to see?");
                int pastmessages = sc.nextInt();
                sc.nextLine();
                this.viewMessagesFrom(user, pastmessages);

            }
            else if(choice == 15) {
                this.seeLocationList();

            }
            System.out.println("Thank you. Would you like to do another task?");
            System.out.println("1 = yes, 0 = no");
            int response = sc.nextInt();
            sc.nextLine();
            if (response == 0) {
                loop_on = false;
            }

        }
    }
}





