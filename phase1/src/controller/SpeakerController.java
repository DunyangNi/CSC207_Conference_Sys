package controller;

import use_cases.*;
import java.util.*;
import java.lang.*;
import presenter.*;

public class SpeakerController {
    private String username;
    private EventManager eventmanager;
    private AccountManager accountmanager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    private Presenter presenter;

    public SpeakerController(String username, EventManager eventmanager, AccountManager accountmanager,
                             ConversationManager conversationManager, FriendManager friendManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.accountmanager = accountmanager;
        this.conversationManager = conversationManager;
        this.friendManager = friendManager;
        signupManager = new SignupManager(eventmanager);
        this.presenter = new Presenter(eventmanager, friendManager, signupManager);
    }

    // timeoftalkrequesthelper() is deprecated due to use of IDs.

    public void SeeSpeakerTalkSchedule() {
        this.presenter.displaySpeakerTalksSchedule(this.username);
    }

    public void messageAttendee(String message, String attendeeUsername) {
        conversationManager.sendMessage(this.username, attendeeUsername, message);
    }

    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) {
        Set<String> selectedAttendeeUsernames = new HashSet<>();
        for (Integer id : selectedSpeakerTalks) {
            if (eventmanager.isTalk(id)) { selectedAttendeeUsernames.addAll(eventmanager.getAttendeesAtEvent(id)); }
        }
        for(String attendeeUsername : selectedAttendeeUsernames) {
            conversationManager.sendMessage(this.username, attendeeUsername, message);
        }
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
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
        if (numMessages < 0) {
            System.out.println("This is an invalid number");
        }
        else if (!conversationManager.getAllUserConversationRecipients(this.username).contains(recipient)) {
            System.out.println("Error: Account '" + recipient + "' is not found");
            System.out.println();
        }
        else {
            String msgToPrint;
            ArrayList<Integer> selectedConversation = conversationManager.getConversationMessages(this.username, recipient);
            System.out.println("Your last " + numMessages + " messages with " + recipient + ":");
            System.out.println();
            int recent_num = Math.min(numMessages, selectedConversation.size());
            for (int i = 0; i < recent_num; i++) {
                msgToPrint = conversationManager.messageToString(selectedConversation.get(numMessages - recent_num - 1 + i));
                System.out.println(msgToPrint);
                System.out.println();
            }
        }
    }
    public void runSpeakerInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true;
        while(loop_on){
            System.out.println("What would you like to do?");
            System.out.println("1 = see a schedule of talks you're giving");
            System.out.println("2 = message an attendee");
            System.out.println("3 = message all attendees for one or multiple talks you're giving");
            System.out.println("4 = see a schedule of all talks");
            System.out.println("5 = add a new contact");
            System.out.println("6 = remove a contact");
            System.out.println("7 = view contacts list");
            System.out.println("8 = view your conversation with someone");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                this.SeeSpeakerTalkSchedule();

            }
            else if(choice == 2) {
                Set<String> allAttendees = accountmanager.getAttendeeList().keySet();
                if (!allAttendees.isEmpty()) {
                    System.out.println("List of attendees");
                    System.out.println("---------------------------------------------");
                    for (String attendeeUsername: allAttendees) { System.out.println(attendeeUsername); }
                    System.out.println("---------------------------------------------\n");
                    System.out.println("Specify the attendee's username");
                    String attendee = sc.nextLine();
                    System.out.println("Please enter your message to send: ");
                    String message = sc.nextLine();
                    if (allAttendees.contains(attendee)) { this.messageAttendee(message, attendee); }
                    else { System.out.println("The entered recipient username is invalid."); }
                }
                else {
                    System.out.println("(No attendees)");
                }
            }

            else if(choice == 3) {
                ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
                boolean doneAddingTalks = false;
                while (!doneAddingTalks) {
                    System.out.print("Please enter the ID of a Talk you are giving: ");
                    Integer id = Integer.parseInt(sc.nextLine());
                    if (eventmanager.isSpeakerOfTalk(id, username)) { selectedSpeakerTalks.add(id); }
                    else { System.out.println("Invalid ID. You are not speaking at this talk."); continue; }
                    System.out.println("Would you like to add another Talk? (1 = yes, 0 = no)");
                    int response = sc.nextInt(); sc.nextLine();
                    doneAddingTalks = response == 0;
                }
                System.out.println("Please enter your message to send: ");
                String message = sc.nextLine();
                this.messageAttendeesAtTalks(selectedSpeakerTalks, message);
            }

            else if(choice == 4) {
                this.SeeTalkSchedule();
            }

            else if(choice == 5) {
                Set<String> allAccts = accountmanager.fetchAccountList().keySet();
                if (!allAccts.isEmpty()) {
                    System.out.println("List of users");
                    System.out.println("---------------------------------------------");
                    for (String acct : allAccts) { System.out.println(acct); }
                    System.out.println("---------------------------------------------\n");
                    System.out.println("Specify username of contact to add");
                    String newContact = sc.nextLine();
                    if (allAccts.contains(newContact)) { this.addFriend(newContact); }
                    else { System.out.println("The entered contact username is invalid."); }
                }
                else { System.out.println("(No users)"); }
            }
            
            else if(choice == 6) {
                System.out.println("Specify username of contact to remove");
                String removeContact = sc.nextLine();
                Set<String> allAccounts = accountmanager.fetchAccountList().keySet();
                if (allAccounts.contains(removeContact)) { this.removeFriend(removeContact); }
                else { System.out.println("The entered contact username is invalid."); }
            }

            else if(choice == 7) {
                this.seeFriendList();
            }
            
            else if(choice == 8) {
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
