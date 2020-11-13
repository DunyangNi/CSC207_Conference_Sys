package controller;

import use_cases.AccountManager;
import use_cases.EventManager;
import use_cases.*;
import entities.*;
import presenter.*;
import java.util.*;
import java.lang.*;

public class AttendeeController {
    private String username;
    private EventManager eventmanager;
    private AccountManager accountmanager;
    private ConversationManager conversationManager;
    private Presenter presenter;

    public AttendeeController(String username, EventManager eventmanager, 
                              AccountManager accountmanager, ConversationManager conversationManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
        this.conversationManager = conversationManager;
        this.presenter = new Presenter(eventmanager, accountmanager);
    }

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

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void signupfortalk(String topic, Calendar time) {
        if(this.eventmanager.containsTalk(topic, time)) {
            if (!(SignupManager.isFull(eventmanager.fetchTalk(topic, time))) && !(SignupManager.isSignedUp(eventmanager.fetchTalk(topic, time), accountmanager.fetchAttendee(username)))) {
                SignupManager.addAttendee(eventmanager.fetchTalk(topic, time), accountmanager.fetchAttendee(username));
            }
        }
    }

    public void cancelenrolmentfortalk(String topic, Calendar time) {
        if(this.eventmanager.containsTalk(topic, time)) {
            if(CancelManager.isSignedUp(this.eventmanager.fetchTalk(topic, time), this.accountmanager.fetchAttendee(username))){
                CancelManager.removeAttendee(this.eventmanager.fetchTalk(topic, time), this.accountmanager.fetchAttendee(username));
            }
        }
    }

    public void seeAttendeeTalkSchedule() {
        this.presenter.displayAttendeeTalkSchedule(this.username);
    }

    public void messageAttendee(String message, String attendeeUsername) {
        conversationManager.sendMessage(this.username, attendeeUsername, message);
    }

    public void messageSpeaker(String message, String speakerusername) {
        conversationManager.sendMessage(this.username, speakerusername, message);
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

    public void runAttendeeInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true;
        while(loop_on){
            System.out.println("What would you like to do?");
            System.out.println("1 = see talk schedule");
            System.out.println("2 = sign up for a talk");
            System.out.println("3 = cancel enrolment for a talk");
            System.out.println("4 = see a schedule of talks you're attending");
            System.out.println("5 = message another attendee");
            System.out.println("6 = message a speaker");
            System.out.println("7 = add a new contact");
            System.out.println("8 = remove a contact");
            System.out.println("9 = see contacts list");
            System.out.println("10 = view your conversation with someone");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                this.SeeTalkSchedule();

            }
            else if(choice == 2) {
                //signupfortalk(String topic, Calendar time)
                System.out.println("Specify the exact topic of the talk");
                //String line1 = sc.nextLine();
                String topic = sc.nextLine();
                Calendar time = this.timeoftalkrequesthelper();
                this.signupfortalk(topic, time);

            }
            else if(choice == 3) {
                System.out.println("Specify the exact topic of the talk");
                //String line1 = sc.nextLine();
                String topic = sc.nextLine();
                Calendar time = this.timeoftalkrequesthelper();
                this.cancelenrolmentfortalk(topic, time);
            }
            else if(choice == 4) {
                this.seeAttendeeTalkSchedule();
            }
            else if(choice == 5) {
                //messageAttendee(String message, String attendeeUsername)
                System.out.println("Specify the username of the attendee you're messaging");
                //String line1 = sc.nextLine();
                String attendeeUsername = sc.nextLine();
                System.out.println("Specify the message you're sending");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAttendee(message, attendeeUsername);
            }
            else if(choice == 6) {
                //messageSpeaker(String message, String speakerusername)
                System.out.println("Specify the username of the speaker you're messaging");
                //String line1 = sc.nextLine();
                String speakerusername = sc.nextLine();
                System.out.println("Specify the message you're sending");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAttendee(message, speakerusername);
            }
            else if(choice == 7) {
                System.out.println("Specify username of contact to add");
               // String line1 = sc.nextLine();
                String friendusername = sc.nextLine();
                this.addFriend(friendusername);

            }
            else if(choice == 8) {
                System.out.println("Specify username of contact to remove");
                //String line1 = sc.nextLine();
                String usernametoremove = sc.nextLine();
                this.removeFriend(usernametoremove);

            }

            else if(choice == 9) {
                this.seeFriendList();

            }
            else if(choice == 10) {
                //viewMessagesFrom(String recipientusername, int nummessages)
                System.out.println("Specify username of user you would like to view your conversation with");
                //String line1 = sc.nextLine();
                String user = sc.nextLine();
                System.out.println("How many past messages would you like to see?");
                int pastmessages = sc.nextInt();
                sc.nextLine();
                this.viewMessagesFrom(user, pastmessages);

            }
            System.out.println("Thank you. Would you like to do another task?");
            System.out.println("1 = yes, 0 = no");
            int response = sc.nextInt();
            sc.nextLine();
            if(response == 0) {
                loop_on = false;
            }
        }

    }
}
