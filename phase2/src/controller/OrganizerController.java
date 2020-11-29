package controller;

import exceptions.ConflictException;
import exceptions.ObjectNotFoundException;
import use_cases.*;

import java.util.*;

import enums.*;

public class OrganizerController extends AccountController {
    /**
     * facilitates interaction with organizer upon login
     * @param username organizer username
     * @param accountManager manages data of all accounts in program
     * @param friendManager manages friendList functionality
     * @param conversationManager manages messaging functionality
     * @param eventManager manages event data
     */
    public OrganizerController(String username, AccountManager accountManager, FriendManager friendManager,
                               ConversationManager conversationManager, EventManager eventManager) {
        super(username, accountManager, friendManager, conversationManager, eventManager);
    }

    /**
     * helper function that adds a user's username as keys to certain
     * hashmaps in the use cases
     * @param username specified username
     */
    // (NEW!) (Helper)
    private void addNewSpeakerKeys(String username) {
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
        eventManager.addSpeakerKey(username);
    }

    /**
     * adds a new allowed location where events can take place to the database
     * @param location location to be added
     */
    public void addNewLocation(String location) {
        try {
            this.eventManager.addNewLocation(location);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }

    /**
     * creates a new speaker account with the given information fields
     * @param username given username
     * @param password given password
     * @param firstname given first name
     * @param lastname given last name
     */
    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
        try {
            this.accountManager.addNewSpeaker(username, password, firstname, lastname);
            addNewSpeakerKeys(username);
        } catch (ConflictException e) {
            presenter.displayPrompt(e.toString()); //
        }
    }

//    /**
//     * Registers a new talk into the database with the given information fields
//     * @param time given time
//     * @param topic given topic
//     * @param location given location
//     * @param speaker given speaker username
//     */
//    public void registerNewTalk(Calendar time, String topic, String location, String speaker) {
//        try {
//            Integer newTalkID = eventManager.addNewTalk(topic, time, location, username, speaker);
//        } catch (Exception e) {
//            presenter.displayPrompt(e.toString());
//        }
//    }

    /**
     * cancels a talk with the given id
     * @param id id of talk to cancel
     */
    public void cancelTalk(Integer id) {
        try {
            this.eventManager.cancelEvent(id);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }

    /**
     * reschedules a talk with the given id to time newTime
     * @param id talk id
     * @param newTime time to reschedule talk to
     */
    public void rescheduleTalk(Integer id, Calendar newTime) {
        try {
            this.eventManager.changeTime(id, newTime);
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
    public boolean runInteraction() {
        boolean programEnd = false;
        boolean loggedIn = true;
        presenter.displayOrganizerMenu();
        Scanner userInput = new Scanner(System.in);
        OrganizerCommand[] commandlist = OrganizerCommand.values();
        String command = userInput.nextLine();

        boolean validinput = false;
        OrganizerCommand enumRequest = OrganizerCommand.EXIT;

        while (validinput == false) {
            for(OrganizerCommand commandEnum: commandlist){
                if (commandEnum.command.equals(command)) {
                    validinput = true;
                    enumRequest = commandEnum;
                    break;
                }
            }
            if(validinput == false){
                presenter.displayPrompt("Invalid input, please try again:\n");
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
            }
        }

        while (loggedIn) {
            // TODO: 11/16/20 Fix scopes defined by {
            switch (enumRequest) {
                case EXIT:
                    programEnd = true;
                    loggedIn = false;
                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case NEW_SPEAKER: {
                    presenter.displayUserPassPrompt();
                    String username = userInput.nextLine();
                    String password = userInput.nextLine();
                    createSpeakerAccount(username, password, "", "");
                    break;
                }
                case VIEW_ALL_ACCOUNTS:
                    Set<String> accounts = accountManager.getAccountHashMap().keySet();
                    presenter.displayAccountList(accounts);
                    break;
                case ADD_CONTACT:
                    presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    friendController.addFriend(contactToAdd);
                    break;
                case REMOVE_CONTACT:
                    presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);
                    break;
                case VIEW_CONTACTS:
                    this.viewContactList();
                    break;
                case MESSAGE_SPEAKER: {
                    presenter.displayMessagingPrompt("aSpeaker");
                    String username = userInput.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, username);
                    break;
                }
                case MESSAGE_ATTENDEE: {
                    presenter.displayMessagingPrompt("anAttendee");
                    String username = userInput.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageAttendee(message, username);
                    break;
                }
                case MESSAGE_ALL_SPEAKERS: {
                    presenter.displayMessagingPrompt("allSpeakers");
                    String message = userInput.nextLine();
                    messageController.messageAllSpeakers(message);
                    break;
                }
                case MESSAGE_ALL_ATTENDEES: {
                    presenter.displayMessagingPrompt("allAttendees");
                    String message = userInput.nextLine();
                    messageController.messageAllAttendees(message);
                    break;
                }
                case VIEW_CONVERSATION:
                    try {
                        Set<String> recipients = conversationManager.getAllUserConversationRecipients(username);

                        if (recipients.isEmpty()) {
                            presenter.displayConversations("empty", recipients);
                        } else {
                            presenter.displayConversations("non_empty", recipients);
                            String recipient = userInput.nextLine();
                            int pastMessages = Integer.parseInt(userInput.nextLine());
                            this.viewMessagesFrom(recipient, pastMessages);
                        }
                    } catch (InputMismatchException e) {
                        this.presenter.displayConversationsErrors("mismatch");
                    } catch (ObjectNotFoundException e) {
                        this.presenter.displayConversationsErrors("no_user");
                    } catch (NullPointerException e) {
                        this.presenter.displayConversationsErrors("no_conversation");
                    }
                    break;
                case ADD_ROOM:
                    presenter.displayRoomRegistration();
                    String location = userInput.nextLine();
                    addNewLocation(location);
                    break;
                case VIEW_ROOMS:
                    this.seeLocationList();
                    break;
                case ADD_EVENT:
                    try {
                        presenter.displayEventPrompt("register");
                        String username = userInput.nextLine();
                        location = userInput.nextLine();
                        String topic = userInput.nextLine();
                        Calendar time = this.collectTimeInfo();
                        eventManager.addNewEvent(EventType.TALK, topic, time, location, this.username, new ArrayList<>(Collections.singletonList(username)), 2, false);
                    } catch (Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case CANCEL_EVENT:
                    try {
                        presenter.displayEventPrompt("cancel");
                        int id = userInput.nextInt();
                        userInput.nextLine();
                        this.cancelTalk(id);
                    }
                    catch(Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case RESCHEDULE_EVENT:
                    try {
                        presenter.displayEventPrompt("reschedule");
                        int id = userInput.nextInt();
                        userInput.nextLine();
                        Calendar newTime = this.collectTimeInfo();
                        this.rescheduleTalk(id, newTime);
                    } catch (Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case VIEW_SCHEDULE:
                    this.SeeTalkSchedule();
                    break;
                case VIEW_MENU:
                    presenter.displayOrganizerMenu();
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();

                validinput = false;
                while (validinput == false) {
                    for(OrganizerCommand commandEnum: commandlist){
                        if (commandEnum.command.equals(command)) {
                            validinput = true;
                            enumRequest = commandEnum;
                            break;
                        }
                    }
                    if(validinput == false){
                        presenter.displayPrompt("Invalid input, please try again:\n");
                        presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                        command = userInput.nextLine();
                    }
                }
            }
        }
        return programEnd;
    }
}





