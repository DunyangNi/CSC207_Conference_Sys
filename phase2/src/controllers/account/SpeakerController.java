package controllers.account;

import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import use_cases.*;
import java.util.*;
import java.lang.*;

import enums.*;

public class SpeakerController extends AccountController {
    /**
     * facilitates interaction with speaker upon login
     * @param username speaker username
     * @param accountmanager manages data of all accounts in program
     * @param contactManager manages friendlist functionality
     * @param conversationManager manages messaging functionality
     * @param eventmanager manages event data
     */
    public SpeakerController(String username, AccountManager accountmanager, ContactManager contactManager,
                             ConversationManager conversationManager, EventManager eventmanager) {
        super(username, accountmanager, contactManager, conversationManager, eventmanager);
    }

    /**
     * Displays all talks this speaker is giving in the future
     */
    public void SeeSpeakerTalkSchedule() {
        this.presenter.displaySpeakerTalksSchedule(this.username);
    }

    /**
     * Interacts with speaker via menu of options
     * @return True if the speaker wishes to terminate the program
     */
    @Override
    public boolean runInteraction() {
        boolean programEnd = false;
        boolean loggedIn = true;
        Scanner userInput = new Scanner(System.in);
        SpeakerEnum[] commandlist = SpeakerEnum.values();
        String command = userInput.nextLine();

        boolean validinput = false;
        SpeakerEnum enumRequest = SpeakerEnum.EXIT;

        while (!validinput) {
            for(SpeakerEnum commandEnum: commandlist){
                if (commandEnum.stringValue.equals(command)) {
                    validinput = true;
                    enumRequest = commandEnum;
                    break;
                }
            }
            if(!validinput){
                presenter.displayPrompt("Invalid input, please try again:\n");
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
            }
        }

        while(loggedIn){
            switch (enumRequest) {
                case EXIT:
                    loggedIn = false;
                    programEnd = true;
                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case VIEW_ALL_ACCOUNTS:
                    Set<String> accounts = am.getAccountHashMap().keySet();
                    presenter.displayAccountList(accounts);
                    break;
                case ADD_CONTACT:
                    accounts = am.getAccountHashMap().keySet();
                    presenter.displayAccountList(accounts);
                    presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    try {
                        contactController.addFriend(contactToAdd);
                    } catch (UserNotFoundException | FriendNotFoundException | AlreadyFriendException e) {
                        e.printStackTrace();
                    }
                    break;
                case REMOVE_CONTACT:
                    presenter.displayContactList(username);
                    presenter.displayContactsPrompt("remove");
                    String removeContact = userInput.nextLine();
                    try {
                        contactController.removeFriend(removeContact);
                    } catch (ObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case VIEW_CONTACTS:
                    presenter.displayContactList(username);
                    break;
                case MESSAGE_ATTENDEE:
                    Set<String> allAttendees = am.getAttendeeHashMap().keySet();
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
                            try {
                                messageController.messageAttendee(message, attendee);
                            } catch (UserNotFoundException | RecipientNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            this.presenter.displayPrompt("The entered recipient username is invalid.");
                        }
                    } else {
                        this.presenter.displayPrompt("(No attendees)");
                    }
                    break;
                case MESSAGE_ALL_AT_TALKS:
                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
                    boolean doneAddingTalks = false;
                    while (!doneAddingTalks) {
                        this.presenter.displayPrompt("Please enter the ID of a Talk you are giving: ");
                        Integer id = Integer.parseInt(userInput.nextLine());
                        if (em.isSpeakerOfTalk(id, username)) {
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
                case VIEW_CONVERSATION:
                    try {
                        Set<String> myConversations = cm.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            this.presenter.displayConversations("empty", myConversations);
                        } else {
                            this.presenter.displayConversations("non_empty", myConversations);
                            String user = userInput.nextLine();
                            int pastMessages = userInput.nextInt();
                            userInput.nextLine();
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (InputMismatchException e) {
                        this.presenter.displayConversationsErrors("mismatch");
                    } catch (ObjectNotFoundException e) {
                        this.presenter.displayConversationsErrors("no_user");
                    } catch (NullPointerException e) {
                        this.presenter.displayConversationsErrors("no_conversation");
                    }
                    break;
                case MY_TALK_SCHEDULE:
                    this.SeeSpeakerTalkSchedule();
                    break;
                case VIEW_SCHEDULE:
                    this.SeeTalkSchedule();
                    break;
                case VIEW_MENU:
                    presenter.displaySpeakerMenu();
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();

                validinput = false;
                while (!validinput) {
                    for(SpeakerEnum commandEnum: commandlist){
                        if (commandEnum.stringValue.equals(command)) {
                            validinput = true;
                            enumRequest = commandEnum;
                            break;
                        }
                    }
                    if(!validinput){
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
