package controller;

import Throwables.ConflictException;
import use_cases.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Set;

public class OrganizerController extends AccountController {
    public OrganizerController(String username, AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventmanager, SignupManager signupManager) {
        super(username, accountManager, friendManager, conversationManager, eventmanager, signupManager);
    }

    public void addNewLocation(String location) {
        try {
            this.eventManager.addLocation(location);
        } catch (ConflictException e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
        try {
            this.accountManager.AddNewSpeaker(username, password, firstname, lastname);
            conversationManager.addAccountKey(username);
            friendManager.addAccountKey(username);
        } catch (ConflictException e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void registerNewTalk(Calendar time, String topic, String location, String speaker) {
        try {
            Integer newTalkID = eventManager.AddNewEvent(topic, time, location, username, speaker);
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
            this.eventManager.ChangeTime(id, newTime);
        } catch (Exception e) {
            presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void seeLocationList() {
        ArrayList<String> locations = this.eventManager.fetchLocations();
        presenter.displayPrompt("Locations:\n");
        for (String location : locations) {
            presenter.displayPrompt(location);
        }
    }

    @Override
    public void runInteraction() {
        Scanner input = new Scanner(System.in);
        boolean loop_on = true;
        while (loop_on) {
            presenter.displayPrompt("What would you like to do?");
            presenter.displayPrompt("0 = logout");
            presenter.displayPrompt("1 = register a new room into the system");
            presenter.displayPrompt("2 = register a new speaker account");
            presenter.displayPrompt("3 = register a new talk");
            presenter.displayPrompt("4 = cancel an event");
            presenter.displayPrompt("5 = reschedule an event");
            presenter.displayPrompt("6 = message all speakers");
            presenter.displayPrompt("7 = message an individual speaker");
            presenter.displayPrompt("8 = message all attendees");
            presenter.displayPrompt("9 = message an individual attendee");
            presenter.displayPrompt("10 = see talk schedule");
            presenter.displayPrompt("11 = add a contact");
            presenter.displayPrompt("12 = remove a contact");
            presenter.displayPrompt("13 = view contacts list");
            presenter.displayPrompt("14 = view your conversation with someone");
            presenter.displayPrompt("15 = see the list of rooms");
            presenter.displayPrompt("16 = see the list of accounts");
            String command = input.nextLine();


            switch (command) {
                case "1":
                    presenter.displayPrompt("Enter a name for the new room:");
                    String location = input.nextLine();
                    addNewLocation(location);
                    break;
                case "2": {
                    presenter.displayPrompt("Enter a username");
                    String username = input.nextLine();
                    presenter.displayPrompt("Enter a password");
                    String password = input.nextLine();
                    presenter.displayPrompt("Enter the speaker's first name");
                    String firstName = input.nextLine();
                    presenter.displayPrompt("Enter the speaker's last name");
                    String lastName = input.nextLine();
                    createSpeakerAccount(username, password, firstName, lastName);
                    break;
                }
                case "3":
                    try {
                        presenter.displayPrompt("Enter the speaker's username:");
                        String username = input.nextLine();
                        presenter.displayPrompt("Enter the event room:");
                        location = input.nextLine();
                        presenter.displayPrompt("Enter the event topic:");
                        String topic = input.nextLine();
                        presenter.displayPrompt("Enter the event time:");
                        // TODO: 11/16/20 Fix this!
                        Calendar time = this.registerEventTime();

                        this.registerNewTalk(time, topic, location, username);
                    } catch (Exception e) {
                        presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
                    }
                    break;
                case "4":
                    System.out.print("Please enter the ID of a talk you wish to cancel: ");
                    int id = input.nextInt();
                    input.nextLine();
                    this.cancelTalk(id);
                    break;
                case "5":
                    try {
                        presenter.displayPrompt("Please enter the ID of a talk you wish to reschedule: ");
                        id = input.nextInt();
                        input.nextLine();
                        Calendar newTime = this.registerEventTime();
                        this.rescheduleTalk(id, newTime);
                    } catch (Exception e) {
                        presenter.displayPrompt("\nSomething went wrong. Please enter valid input.\n");
                    }
                    break;
                case "6": {
                    presenter.displayPrompt("Please enter the message that you want to send to all speakers:");
                    String message = input.nextLine();
                    messageController.messageAllSpeakers(message);
                    break;
                }
                case "7": {
                    System.out.print("Please enter the username of the speaker you wish to message: ");
                    String username = input.nextLine();
                    presenter.displayPrompt("Please enter the message you want to send to this speaker:");
                    String message = input.nextLine();
                    messageController.messageSpeaker(message, username);
                    break;
                }
                case "8": {
                    presenter.displayPrompt("Please enter the message that you want to send to all attendees:");
                    String message = input.nextLine();
                    messageController.messageAllAttendees(message);
                    break;
                }
                case "9": {
                    System.out.print("Please enter the username of the attendee you want to message: ");
                    String username = input.nextLine();
                    presenter.displayPrompt("Please enter the message you want to send the attendee:");
                    String message = input.nextLine();
                    messageController.messageAttendee(message, username);
                    break;
                }
                case "10":
                    this.SeeTalkSchedule();
                    break;
                case "11":
                    System.out.print("Please enter the username of a contact to add: ");
                    String contactToAdd = input.nextLine();
                    friendController.addFriend(contactToAdd);
                    break;
                case "12":
                    System.out.print("Please enter the username of a contact to remove: ");
                    String contactToRemove = input.nextLine();
                    friendController.removeFriend(contactToRemove);
                    break;
                case "13":
                    this.seeFriendList();
                    break;
                case "14":
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            presenter.displayPrompt("(No conversations)");
                        } else {
                            presenter.displayPrompt("List of Conversation Recipients");
                            presenter.displayPrompt("---------------------------------------------");
                            for (String recipient : myConversations) {
                                presenter.displayPrompt(recipient);
                            }
                            presenter.displayPrompt("---------------------------------------------\n");
                            System.out.print("To access a conversation, please enter the recipient's username: ");
                            String user = input.nextLine();
                            presenter.displayPrompt("How many past messages would you like to see?");
                            int pastMessages = input.nextInt();
                            input.nextLine();
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
                    System.out.println(accountManager.getAccountList().keySet());
            }
            presenter.displayPrompt("Thank you. Would you like to do another task?");
            presenter.displayPrompt("1 = yes, 0 = no");
            int response = input.nextInt();
            input.nextLine();
            if (response == 0) {
                loop_on = false;
            }

        }
    }
}





