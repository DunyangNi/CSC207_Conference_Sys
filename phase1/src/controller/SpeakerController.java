package controller;

import use_cases.*;
import java.util.*;
import java.lang.*;
import presenter.*;

public class SpeakerController extends AccountController {

    public SpeakerController(String username, AccountManager accountmanager, FriendManager friendManager,
                             ConversationManager conversationManager, EventManager eventmanager,
                             SignupManager signupManager, Presenter presenter) {
        super(username, accountmanager, friendManager, conversationManager, eventmanager, signupManager, presenter);
    }

    public void SeeSpeakerTalkSchedule() {
        this.presenter.displaySpeakerTalksSchedule(this.username);
    }

    @Override
    public boolean runInteraction() {
        boolean programEnd = false;
        boolean loggedIn = true;
        presenter.displaySpeakerMenu();
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();
        while(loggedIn){
            switch (command) {
                case "00":
                    loggedIn = false;
                    programEnd = true;
                    break;
                case "0":
                    loggedIn = false;
                    break;
                case "1":
                    this.SeeSpeakerTalkSchedule();
                    break;
                case "2":
                    Set<String> allAttendees = accountManager.getAttendeeList().keySet();
                    if (!allAttendees.isEmpty()) {
                        System.out.println("List of attendees");
                        System.out.println("---------------------------------------------");
                        for (String attendeeUsername : allAttendees) {
                            System.out.println(attendeeUsername);
                        }
                        System.out.println("---------------------------------------------\n");
                        System.out.println("Specify the attendee's username");
                        String attendee = userInput.nextLine();
                        System.out.println("Please enter your message to send: ");
                        String message = userInput.nextLine();
                        if (allAttendees.contains(attendee)) {
                            messageController.messageAttendee(message, attendee);
                        } else {
                            System.out.println("The entered recipient username is invalid.");
                        }
                    } else {
                        System.out.println("(No attendees)");
                    }
                    break;
                case "3":
                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
                    boolean doneAddingTalks = false;
                    while (!doneAddingTalks) {
                        System.out.print("Please enter the ID of a Talk you are giving: ");
                        Integer id = Integer.parseInt(userInput.nextLine());
                        if (eventManager.isSpeakerOfTalk(id, username)) {
                            selectedSpeakerTalks.add(id);
                        } else {
                            System.out.println("Invalid ID. You are not speaking at this talk.");
                            continue;
                        }
                        System.out.println("Would you like to add another Talk? (1 = yes, 0 = no)");
                        int response = userInput.nextInt();
                        userInput.nextLine();
                        doneAddingTalks = response == 0;
                    }
                    System.out.println("Please enter your message to send: ");
                    String message = userInput.nextLine();
                    messageController.messageAttendeesAtTalks(selectedSpeakerTalks, message);
                    break;
                case "4":
                    this.SeeTalkSchedule();
                    break;
                case "5":
                    Set<String> allAccts = accountManager.fetchAccountList().keySet();
                    if (!allAccts.isEmpty()) {
                        System.out.println("List of users");
                        System.out.println("---------------------------------------------");
                        for (String acct : allAccts) {
                            System.out.println(acct);
                        }
                        System.out.println("---------------------------------------------\n");
                        System.out.println("Specify username of contact to add");
                        String newContact = userInput.nextLine();
                        if (allAccts.contains(newContact)) {
                            friendController.addFriend(newContact);
                        } else {
                            System.out.println("The entered contact username is invalid.");
                        }
                    } else {
                        System.out.println("(No users)");
                    }
                    break;
                case "6":
                    System.out.println("Specify username of contact to remove");
                    String removeContact = userInput.nextLine();
                    Set<String> allAccounts = accountManager.fetchAccountList().keySet();
                    if (allAccounts.contains(removeContact)) {
                        friendController.removeFriend(removeContact);
                    } else {
                        System.out.println("The entered contact username is invalid.");
                    }
                    break;
                case "7":
                    this.seeFriendList();
                    break;
                case "8":
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
                            String user = userInput.nextLine();
                            System.out.println("How many past messages would you like to see?");
                            int pastMessages = userInput.nextInt();
                            userInput.nextLine();
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
                    }
                    break;
                case "16":
                    presenter.displayPrompt(accountManager.fetchAccountList().keySet().toString());
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
                if (command.equals("*")) {
                    presenter.displaySpeakerMenu();
                }
            }
        }
        return programEnd;
    }
}
