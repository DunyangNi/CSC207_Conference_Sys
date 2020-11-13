package controller;

import use_cases.*;
import presenter.*;
import java.util.*;
import java.lang.*;

public class AttendeeController {
    private String username;
    private EventManager eventmanager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    private Presenter presenter;

    public AttendeeController(String username, EventManager eventmanager,
                              ConversationManager conversationManager, FriendManager friendManager,
                              SignupManager signupManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.conversationManager = conversationManager;
        this.friendManager = friendManager;
        this.signupManager = signupManager;
        this.presenter = new Presenter(eventmanager, friendManager, signupManager);
    }

    // timeoftalkrequesthelper() is now deprecated due to use of IDs.

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    // subject to change, error handling
    public void signupForTalk(Integer id) {
        if(this.eventmanager.isTalk(id)) {
            signupManager.addAttendee(id, username);
        }
    }

    // subject to change, error handling
    public void cancelSignupForTalk(Integer id) {
        if(this.eventmanager.isTalk(id)) {
            signupManager.removeAttendee(id, username);
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

    public void addFriend(String friendToAdd) {
        friendManager.AddFriend(this.username, friendToAdd);
    }

    public void removeFriend(String friendToRemove) {
        friendManager.RemoveFriend(this.username, friendToRemove);
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
                System.out.print("Please enter the ID of the Talk you wish to attend: ");
                // Grab ID number and attempt to sign up (error handling?)
                Integer id = Integer.parseInt(sc.nextLine());
                this.signupForTalk(id);

            }
            else if(choice == 3) {
                System.out.print("Please enter the ID of the Talk you wish to cancel: ");
                // Grab ID number and attempt to cancel (error handling?)
                Integer id = Integer.parseInt(sc.nextLine());
                this.cancelSignupForTalk(id);
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
                String speakerUsername = sc.nextLine();
                System.out.println("Specify the message you're sending");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                this.messageAttendee(message, speakerUsername);
            }
            else if(choice == 7) {
                System.out.print("Please enter the username of a contact to add: ");
                String contactToAdd = sc.nextLine();
                this.addFriend(contactToAdd);

            }
            else if(choice == 8) {
                System.out.print("Please enter the username of a contact to remove: ");
                String contactToRemove = sc.nextLine();
                this.removeFriend(contactToRemove);

            }

            else if(choice == 9) {
                this.seeFriendList();

            }

            else if(choice == 10) {
                Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                if (myConversations.isEmpty()) { System.out.println("(No conversations)"); }
                else {
                    System.out.println("List of Conversation Recipients");
                    System.out.println("---------------------------------------------");
                    for (String recipient : myConversations) { System.out.println(recipient); }
                    System.out.println("---------------------------------------------\n");
                    System.out.print("To access a conversation, please enter the recipient's username: ");
                    String user = sc.nextLine();
                    System.out.println("How many past messages would you like to see?");
                    int pastMessages = sc.nextInt(); sc.nextLine();
                    this.viewMessagesFrom(user, pastMessages);
                }
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
