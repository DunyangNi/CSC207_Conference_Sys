package controller;

import Throwables.ConflictException;
import use_cases.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Set;

public class OrganizerController extends AccountController {
    public OrganizerController(String username, AccountManager accountManager, FriendManager friendManager,
                               ConversationManager conversationManager, EventManager eventmanager,
                               SignupManager signupManager) {
        super(username, accountManager, friendManager, conversationManager, eventmanager, signupManager);
    }

    // (NEW!) (Helper)
    private void addNewSpeakerKeys(String username) {
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
        eventManager.addSpeakerKey(username);
    }

    public void addNewLocation(String location) {
        try {
            this.eventManager.addNewLocation(location);
        } catch (ConflictException e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
        try {
            this.accountManager.AddNewSpeaker(username, password, firstname, lastname);
            addNewSpeakerKeys(username);
        } catch (ConflictException e) {
            presenter.displayPrompt(e.toString()); // r
        }
    }

    public void registerNewTalk(Calendar time, String topic, String location, String speaker) {
        try {
            Integer newTalkID = eventManager.addNewTalk(topic, time, location, username, speaker);
            signupManager.addEventKey(newTalkID);
        } catch (Exception e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void cancelTalk(Integer id) {
        try {
            this.eventManager.cancelTalk(id);
            signupManager.removeEventKey(id);
        } catch (Exception e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void rescheduleTalk(Integer id, Calendar newTime) {
        try {
            this.eventManager.changeTime(id, newTime);
        } catch (Exception e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void seeLocationList() {
        ArrayList<String> locations = this.eventManager.getLocations();
        presenter.displayPrompt("Locations:\n");
        for (String location : locations) {
            presenter.displayPrompt(location);
        }
    }

    @Override
    public void runInteraction() {
        boolean loggedIn = true;
        presenter.displayOrganizerMenu();
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();

        while (loggedIn) {

            // TODO: 11/16/20 Fix scopes defined by {
            switch (command) {
                case "00":
                    return;
                case "0":
                    loggedIn = false;
                    StartController startController = new StartController(accountManager, friendManager, conversationManager, eventManager, signupManager);
                    startController.runStartMenu();
                    break;
                case "1":
                    presenter.displayPrompt("Enter a name for the new room:");
                    String location = userInput.nextLine();
                    addNewLocation(location);
                    break;
                case "2": {
                    presenter.displayPrompt("Enter a username:");
                    String username = userInput.nextLine();
                    presenter.displayPrompt("Enter a password:");
                    String password = userInput.nextLine();
                    presenter.displayPrompt("Enter the speaker's first name");
                    String firstName = userInput.nextLine();
                    presenter.displayPrompt("Enter the speaker's last name");
                    String lastName = userInput.nextLine();
                    createSpeakerAccount(username, password, firstName, lastName);
                    break;
                }
                case "3":
                    try {
                        presenter.displayPrompt("Enter the speaker's username:");
                        String username = userInput.nextLine();
                        presenter.displayPrompt("Enter the event room:");
                        location = userInput.nextLine();
                        presenter.displayPrompt("Enter the event topic:");
                        String topic = userInput.nextLine();
                        presenter.displayPrompt("Enter the event time:");
                        Calendar time = this.collectTimeInfo();
                        Integer newTalkID = eventManager.addNewTalk(topic, time, location, this.username, username);
                        signupManager.addEventKey(newTalkID);
                    } catch (Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case "4":
                    presenter.displayPrompt("Please enter the ID of a talk you wish to cancel: ");
                    int id = userInput.nextInt();
                    userInput.nextLine();
                    this.cancelTalk(id);
                    break;
                case "5":
                    try {
                        presenter.displayPrompt("Please enter the ID of a talk you wish to reschedule: ");
                        id = userInput.nextInt();
                        userInput.nextLine();
                        Calendar newTime = this.collectTimeInfo();
                        this.rescheduleTalk(id, newTime);
                    } catch (Exception e) {
                        presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
                    }
                    break;
                case "6": {
                    presenter.displayPrompt("Please enter the message that you want to send to all speakers:");
                    String message = userInput.nextLine();
                    messageController.messageAllSpeakers(message);
                    break;
                }
                case "7": {
                    presenter.displayPrompt("Please enter the username of the speaker you wish to message: ");
                    String username = userInput.nextLine();
                    presenter.displayPrompt("Please enter the message you want to send to this speaker:");
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, username);
                    break;
                }
                case "8": {
                    presenter.displayPrompt("Please enter the message that you want to send to all attendees:");
                    String message = userInput.nextLine();
                    messageController.messageAllAttendees(message);
                    break;
                }
                case "9": {
                    presenter.displayPrompt("Please enter the username of the attendee you want to message: ");
                    String username = userInput.nextLine();
                    presenter.displayPrompt("Please enter the message you want to send the attendee:");
                    String message = userInput.nextLine();
                    messageController.messageAttendee(message, username);
                    break;
                }
                case "10":
                    this.SeeTalkSchedule();
                    break;
                case "11":
                    presenter.displayPrompt("Please enter the username of a contact to add: ");
                    String contactToAdd = userInput.nextLine();
                    friendController.addFriend(contactToAdd);
                    break;
                case "12":
                    presenter.displayPrompt("Please enter the username of a contact to remove: ");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);
                    break;
                case "13":
                    this.seeFriendList();
                    break;
                case "14":
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            presenter.displayPrompt("You have no conversations.");
                        } else {
                            presenter.displayPrompt("[CONVERSATION RECIPIENTS]");
                            presenter.displayPrompt("----------------------------------------------------------------");
                            for (String recipient : myConversations) {
                                presenter.displayPrompt(recipient);
                            }
                            presenter.displayPrompt("----------------------------------------------------------------");
                            presenter.displayPrompt("To access a conversation, please enter the recipient's username:");
                            String user = userInput.nextLine();
                            presenter.displayPrompt("How many past messages would you like to see?");
                            int pastMessages = Integer.parseInt(userInput.nextLine());
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (Exception e) {
                        presenter.displayPrompt("\nSomething went wrong. Please enter valid input.");
                    }
                    break;
                case "15":
                    this.seeLocationList();
                    break;
                case "16":
                    presenter.displayPrompt(accountManager.fetchAccountList().keySet().toString());
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (!command.equals("0")) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
                if (command.equals("*")) {
                    presenter.displayOrganizerMenu();
                }
            }
        }
    }
}





