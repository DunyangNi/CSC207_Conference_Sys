package controller;

import Throwables.*;
import use_cases.*;

import java.util.*;

import presenter.*;

public class OrganizerController extends AccountController {
    /**
     * facilitates interaction with organizer upon login
     * @param username organizer username
     * @param accountManager manages data of all accounts in program
     * @param friendManager manages friendList functionality
     * @param conversationManager manages messaging functionality
     * @param eventManager manages event data
     * @param signupManager manages event signup functionality
     * @param presenter specifies the UI
     */
    public OrganizerController(String username, AccountManager accountManager, FriendManager friendManager,
                               ConversationManager conversationManager, EventManager eventManager,
                               SignupManager signupManager, Presenter presenter) {
        super(username, accountManager, friendManager, conversationManager, eventManager, signupManager, presenter);
    }





    /**
     * creates a new speaker account with the given information fields
     * @param username given username
     * @param password given password
     * @param firstname given first name
     * @param lastname given last name
     */


    /**
     * Registers a new talk into the database with the given information fields
     * @param time given time
     * @param topic given topic
     * @param location given location
     * @param speaker given speaker username
     */
    public void registerNewTalk(Calendar time, String topic, String location, String speaker) {
        try {
            Integer newTalkID = eventManager.addNewTalk(topic, time, location, username, speaker);
            signupManager.addEventKey(newTalkID);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }




    /**
     * displays the list of all locations currently in the database
     */
    public void seeLocationList() {
        ArrayList<String> locations = this.eventManager.getLocations();
        presenter.displayPrompt("Locations:\n");
        for (String location : locations) {
            presenter.displayPrompt(location);
        }
    }

    /**
     * interacts with the organizer via a menu of options
     * @return True if organizer wants to terminate the program
     */
    @Override
    public boolean runInteraction() throws UserNotFoundException, AlreadyExistException, UserNameNotFoundException, EmptyListException, InvalidIntegerException, MessageNotFound {
        boolean programEnd = false;
        boolean loggedIn = true;
        presenter.displayOrganizerMenu();
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();

        while (loggedIn) {
            // TODO: 11/16/20 Fix scopes defined by {
            switch (command) {
                case "00":
                    programEnd = true;
                    loggedIn = false;
                    break;
                case "0":
                    loggedIn = false;
                    break;
                case "1": {
                    presenter.displayUserPassPrompt();
                    String username = userInput.nextLine();
                    String password = userInput.nextLine();
                    accountCreationController.createSpeakerAccount(username, password, "", "");
                    break;
                }
                case "2":
                    Set<String> accounts = accountManager.getAccountHashMap().keySet();
                    presenter.displayAccountList(accounts);
                    break;
                case "3":
                    presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    addFriendController.addFriend(contactToAdd);
                    break;
                case "4":
                    presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    removeFriendController.removeFriend(contactToRemove);
                    break;
                case "5":
                    this.viewContactList();
                    break;
                case "6": {
                    presenter.displayMessagingPrompt("aSpeaker");
                    String username = userInput.nextLine();
                    String message = userInput.nextLine();
                    messageSpeakerController.messageSpeaker(message, username);
                    break;
                }
                case "7": {
                    presenter.displayMessagingPrompt("anAttendee");
                    String username = userInput.nextLine();
                    String message = userInput.nextLine();
                    messageAttendeeController.messageAttendee(message, username);
                    break;
                }
                case "8": {
                    presenter.displayMessagingPrompt("allSpeakers");
                    String message = userInput.nextLine();
                    messageSpeakerController.messageAllSpeakers(message);
                    break;
                }
                case "9": {
                    presenter.displayMessagingPrompt("allAttendees");
                    String message = userInput.nextLine();
                    messageAttendeeController.messageAllAttendees(message);
                    break;
                }
                case "10":
                    Set<String> recipients = conversationManager.getAllUserConversationRecipients(username);
                    if (recipients.isEmpty()) {
                        presenter.displayConversations("empty", recipients);
                    } else {
                        presenter.displayConversations("non_empty", recipients);
                        String recipient = userInput.nextLine();
                        int pastMessages = Integer.parseInt(userInput.nextLine());
                        viewConversationController.viewMessagesFrom(recipient, pastMessages);
                    }

                    break;
                case "11":
                    presenter.displayRoomRegistration();
                    String location = userInput.nextLine();
                    locationController.addNewLocation(location);
                    break;
                case "12":
                    this.seeLocationList();
                    break;
                case "13":
                    try {
                        presenter.displayEventPrompt("register");
                        String username = userInput.nextLine();
                        location = userInput.nextLine();
                        String topic = userInput.nextLine();
                        Calendar time = this.collectTimeInfo();
                        eventCreationController.createEvent(topic, time, location, username);

                    } catch (Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case "14":
                    try {
                        presenter.displayEventPrompt("cancel");
                        int id = userInput.nextInt();
                        userInput.nextLine();
                        eventCreationController.cancelTalk(id);
                    }
                    catch(Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case "15":
                    try {
                        presenter.displayEventPrompt("reschedule");
                        int id = userInput.nextInt();
                        userInput.nextLine();
                        Calendar newTime = this.collectTimeInfo();
                        eventModifyController.rescheduleTalk(id, newTime);
                    } catch (Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case "16":
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





