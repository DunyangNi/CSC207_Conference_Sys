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
                    presenter.displayPrompt(accountManager.fetchAccountList().keySet().toString());
                    break;
                case "2":
                    Set<String> allAccts = accountManager.fetchAccountList().keySet();
                    if (!allAccts.isEmpty()) {
                        this.presenter.displayPrompt("List of users");
                        this.presenter.displayPrompt("---------------------------------------------");
                        for (String acct : allAccts) {
                            this.presenter.displayPrompt(acct);
                        }
                        this.presenter.displayPrompt("---------------------------------------------\n");
                        this.presenter.displayPrompt("Specify username of contact to add");
                        String newContact = userInput.nextLine();
                        if (allAccts.contains(newContact)) {
                            friendController.addFriend(newContact);
                        } else {
                            this.presenter.displayPrompt("The entered contact username is invalid.");
                        }
                    } else {
                        this.presenter.displayPrompt("(No users)");
                    }
                    break;
                case "3":
                    this.presenter.displayPrompt("Specify username of contact to remove");
                    String removeContact = userInput.nextLine();
                    Set<String> allAccounts = accountManager.fetchAccountList().keySet();
                    if (allAccounts.contains(removeContact)) {
                        friendController.removeFriend(removeContact);
                    } else {
                        this.presenter.displayPrompt("The entered contact username is invalid.");
                    }
                    break;
                case "4":
                    this.viewContactList();
                    break;
                case "5":
                    Set<String> allAttendees = accountManager.getAttendeeList().keySet();
                    if (!allAttendees.isEmpty()) {
                        this.presenter.displayPrompt("List of attendees");
                        this.presenter.displayPrompt("---------------------------------------------");
                        for (String attendeeUsername : allAttendees) {
                            this.presenter.displayPrompt(attendeeUsername);
                        }
                        this.presenter.displayPrompt("---------------------------------------------\n");
                        this.presenter.displayPrompt("Specify the attendee's username");
                        String attendee = userInput.nextLine();
                        this.presenter.displayPrompt("Please enter your message to send: ");
                        String message = userInput.nextLine();
                        if (allAttendees.contains(attendee)) {
                            messageController.messageAttendee(message, attendee);
                        } else {
                            this.presenter.displayPrompt("The entered recipient username is invalid.");
                        }
                    } else {
                        this.presenter.displayPrompt("(No attendees)");
                    }
                    break;
                case "6":
                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
                    boolean doneAddingTalks = false;
                    while (!doneAddingTalks) {
                        this.presenter.displayPrompt("Please enter the ID of a Talk you are giving: ");
                        Integer id = Integer.parseInt(userInput.nextLine());
                        if (eventManager.isSpeakerOfTalk(id, username)) {
                            selectedSpeakerTalks.add(id);
                        } else {
                            this.presenter.displayPrompt("Invalid ID. You are not speaking at this talk.");
                            continue;
                        }
                        this.presenter.displayPrompt("Would you like to add another Talk? (1 = yes, 0 = no)");
                        int response = userInput.nextInt();
                        userInput.nextLine();
                        doneAddingTalks = response == 0;
                    }
                    this.presenter.displayPrompt("Please enter your message to send: ");
                    String message = userInput.nextLine();
                    messageController.messageAttendeesAtTalks(selectedSpeakerTalks, message);
                    break;
                case "7":
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            this.presenter.displayPrompt("(No conversations)");
                        } else {
                            this.presenter.displayPrompt("List of Conversation Recipients");
                            this.presenter.displayPrompt("---------------------------------------------");
                            for (String recipient : myConversations) {
                                this.presenter.displayPrompt(recipient);
                            }
                            this.presenter.displayPrompt("---------------------------------------------\n");
                            this.presenter.displayPrompt("To access a conversation, please enter the recipient's username: ");
                            String user = userInput.nextLine();
                            this.presenter.displayPrompt("How many past messages would you like to see?");
                            int pastMessages = userInput.nextInt();
                            userInput.nextLine();
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (Exception e) {
                        this.presenter.displayPrompt(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
                    }
                    break;
                case "8":
                    this.SeeSpeakerTalkSchedule();
                    break;
                case "9":
                    this.SeeTalkSchedule();
                    break;
                case "*":
                    presenter.displayOrganizerMenu();
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
            }
        }
        return programEnd;
    }
}
