package controller;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import use_cases.*;

import java.util.*;

import presenter.*;

public class OrganizerController extends AccountController {
    public OrganizerController(String username, AccountManager accountManager, FriendManager friendManager,
                               ConversationManager conversationManager, EventManager eventmanager,
                               SignupManager signupManager, Presenter presenter) {
        super(username, accountManager, friendManager, conversationManager, eventmanager, signupManager, presenter);
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
            presenter.displayPrompt(e.toString());
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
            presenter.displayPrompt(e.toString());
        }
    }

    public void cancelTalk(Integer id) {
        try {
            this.eventManager.cancelTalk(id);
            signupManager.removeEventKey(id);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }

    public void rescheduleTalk(Integer id, Calendar newTime) {
        try {
            this.eventManager.changeTime(id, newTime);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
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
    public boolean runInteraction() {
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
                    createSpeakerAccount(username, password, "", "");
                    break;
                }
                case "2":
                    presenter.displayPrompt(accountManager.fetchAccountList().keySet().toString());
                    break;
                case "3":
                    presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    friendController.addFriend(contactToAdd);
                    break;
                case "4":
                    presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);
                    break;
                case "5":
                    this.viewContactList();
                    break;
                case "6": {
                    presenter.displayMessagingPrompt("aSpeaker");
                    String username = userInput.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, username);
                    break;
                }
                case "7": {
                    presenter.displayMessagingPrompt("anAttendee");
                    String username = userInput.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageAttendee(message, username);
                    break;
                }
                case "8": {
                    presenter.displayMessagingPrompt("allSpeakers");
                    String message = userInput.nextLine();
                    messageController.messageAllSpeakers(message);
                    break;
                }
                case "9": {
                    presenter.displayMessagingPrompt("allAttendees");
                    String message = userInput.nextLine();
                    messageController.messageAllAttendees(message);
                    break;
                }
                case "10":
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
                case "11":
                    presenter.displayRoomRegistration();
                    String location = userInput.nextLine();
                    addNewLocation(location);
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
                        Integer newTalkID = eventManager.addNewTalk(topic, time, location, this.username, username);
                        signupManager.addEventKey(newTalkID);
                    } catch (Exception e) {
                        presenter.displayPrompt(e.toString());
                    }
                    break;
                case "14":
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
                case "15":
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





