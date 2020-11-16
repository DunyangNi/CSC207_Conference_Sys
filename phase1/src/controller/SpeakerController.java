package controller;

import use_cases.*;
import java.util.*;
import java.lang.*;

public class SpeakerController extends UserController {

    public SpeakerController(String username, EventManager eventmanager, AccountManager accountmanager,
                             ConversationManager conversationManager, SignupManager signupManager,
                             FriendManager friendManager) {
        super(username, eventmanager, conversationManager, friendManager, signupManager, accountmanager);
    }

    public void SeeSpeakerTalkSchedule() {
        this.presenter.displaySpeakerTalksSchedule(this.username);
    }

    @Override
    public void runInteraction() {
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
                Set<String> allAttendees = accountManager.getAttendeeList().keySet();
                if (!allAttendees.isEmpty()) {
                    System.out.println("List of attendees");
                    System.out.println("---------------------------------------------");
                    for (String attendeeUsername: allAttendees) { System.out.println(attendeeUsername); }
                    System.out.println("---------------------------------------------\n");
                    System.out.println("Specify the attendee's username");
                    String attendee = sc.nextLine();
                    System.out.println("Please enter your message to send: ");
                    String message = sc.nextLine();
                    if (allAttendees.contains(attendee)) { messageController.messageAttendee(message, attendee); }
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
                    int response = sc.nextInt();
                    doneAddingTalks = response == 0;
                }
                System.out.println("Please enter your message to send: ");
                String message = sc.nextLine();
                messageController.messageAttendeesAtTalks(selectedSpeakerTalks, message);
            }

            else if(choice == 4) {
                this.SeeTalkSchedule();
            }

            else if(choice == 5) {
                Set<String> allAccts = accountManager.getAccountList().keySet();
                if (!allAccts.isEmpty()) {
                    System.out.println("List of users");
                    System.out.println("---------------------------------------------");
                    for (String acct : allAccts) { System.out.println(acct); }
                    System.out.println("---------------------------------------------\n");
                    System.out.println("Specify username of contact to add");
                    String newContact = sc.nextLine();
                    if (allAccts.contains(newContact)) { friendController.addFriend(newContact); }
                    else { System.out.println("The entered contact username is invalid."); }
                }
                else { System.out.println("(No users)"); }
            }
            
            else if(choice == 6) {
                System.out.println("Specify username of contact to remove");
                String removeContact = sc.nextLine();
                Set<String> allAccounts = accountManager.getAccountList().keySet();
                if (allAccounts.contains(removeContact)) { friendController.removeFriend(removeContact); }
                else { System.out.println("The entered contact username is invalid."); }
            }

            else if(choice == 7) {
                this.seeFriendList();
            }
            
            else if(choice == 8) {
                try {
                    Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                    if (myConversations.isEmpty()) {
                        System.out.println("(No conversations)");
                    } else {
                        System.out.println("List of Conversation Recipients");
                        System.out.println("---------------------------------------------");
                        for (String recipient : myConversations) {
                            System.out.println(recipient);
                        }
                        System.out.println("---------------------------------------------\n");
                        System.out.print("To access a conversation, please enter the recipient's username: ");
                        String user = sc.nextLine();
                        System.out.println("How many past messages would you like to see?");
                        int pastMessages = sc.nextInt();
                        sc.nextLine();
                        this.viewMessagesFrom(user, pastMessages);
                    }
                }
                catch(Exception e) {
                    System.out.println("\nSomething went wrong. Please enter valid input.\n");
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
