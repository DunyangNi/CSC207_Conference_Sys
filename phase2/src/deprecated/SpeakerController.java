package deprecated;

import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;

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
//    public SpeakerController(String username, AccountManager accountmanager, ContactManager contactManager,
//                             ConversationManager conversationManager, EventManager eventmanager) {
//        super(username, accountmanager, contactManager, conversationManager, eventmanager);
//    }

    /**
     * Displays all talks this speaker is giving in the future
     */
    public void SeeSpeakerTalkSchedule() {
        this.oldPresenter.displaySpeakerTalksSchedule(this.username);
    }

    /**
     * Interacts with speaker via menu of options
     * @return True if the speaker wishes to terminate the program
     */
    @Override
    public boolean runInteraction() {
        boolean programEnd = false;
//        boolean loggedIn = true;
//        Scanner userInput = new Scanner(System.in);
//        SpeakerMenuEnum[] commandlist = SpeakerMenuEnum.values();
//        String command = userInput.nextLine();
//
//        boolean validinput = false;
//        SpeakerMenuEnum enumRequest = SpeakerMenuEnum.EXIT;
//
//        while (!validinput) {
//            for(SpeakerMenuEnum commandEnum: commandlist){
//                if (commandEnum.stringValue.equals(command)) {
//                    validinput = true;
//                    enumRequest = commandEnum;
//                    break;
//                }
//            }
//            if(!validinput){
//                oldPresenter.displayPrompt("Invalid input, please try again:\n");
//                oldPresenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
//                command = userInput.nextLine();
//            }
//        }
//
//        while(loggedIn){
//            switch (enumRequest) {
//                case EXIT:
//                    loggedIn = false;
//                    programEnd = true;
//                    break;
//                case LOGOUT:
//                    loggedIn = false;
//                    break;
//                case VIEW_ALL_ACCOUNTS:
//                    Set<String> accounts = am.getAccountHashMap().keySet();
//                    oldPresenter.displayAccountList(accounts);
//                    break;
//                case ADD_CONTACT:
//                    accounts = am.getAccountHashMap().keySet();
//                    oldPresenter.displayAccountList(accounts);
//                    oldPresenter.displayContactsPrompt("add");
//                    String contactToAdd = userInput.nextLine();
//                    try {
//                        contactController.addFriend(contactToAdd);
//                    } catch (UserNotFoundException | FriendNotFoundException | AlreadyFriendException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case REMOVE_CONTACT:
//                    oldPresenter.displayContactList(username);
//                    oldPresenter.displayContactsPrompt("remove");
//                    String removeContact = userInput.nextLine();
//                    try {
//                        contactController.removeFriend(removeContact);
//                    } catch (ObjectNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case VIEW_CONTACTS:
//                    oldPresenter.displayContactList(username);
//                    break;
//                case MESSAGE:
//                    Set<String> allAttendees = am.getAttendeeHashMap().keySet();
//                    if (!allAttendees.isEmpty()) {
//                        this.oldPresenter.displayPrompt("List of attendees");
//                        this.oldPresenter.displayPrompt("---------------------------------------------");
//                        for (String attendeeUsername : allAttendees) {
//                            this.oldPresenter.displayPrompt(attendeeUsername);
//                        }
//                        this.oldPresenter.displayPrompt("---------------------------------------------\n");
//                        this.oldPresenter.displayPrompt("Specify the attendee's username");
//                        String attendee = userInput.nextLine();
//                        this.oldPresenter.displayPrompt("Please enter your message to send: ");
//                        String message = userInput.nextLine();
//                        if (allAttendees.contains(attendee)) {
//                            try {
//                                messageController.messageAttendee(message, attendee);
//                            } catch (UserNotFoundException | RecipientNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            this.oldPresenter.displayPrompt("The entered recipient username is invalid.");
//                        }
//                    } else {
//                        this.oldPresenter.displayPrompt("(No attendees)");
//                    }
//                    break;
//                case MESSAGE_ATTENDEES:
//                    ArrayList<Integer> selectedSpeakerTalks = new ArrayList<>();
//                    boolean doneAddingTalks = false;
//                    while (!doneAddingTalks) {
//                        this.oldPresenter.displayPrompt("Please enter the ID of a Talk you are giving: ");
//                        Integer id = Integer.parseInt(userInput.nextLine());
//                        if (em.isSpeakerOfTalk(id, username)) {
//                            selectedSpeakerTalks.add(id);
//                        } else {
//                            this.oldPresenter.displayPrompt("Invalid ID. You are not speaking at this talk.");
//                            continue;
//                        }
//                        this.oldPresenter.displayPrompt("Would you like to add another Talk? (1 = yes, 0 = no)");
//                        int response = userInput.nextInt();
//                        userInput.nextLine();
//                        doneAddingTalks = response == 0;
//                    }
//                    this.oldPresenter.displayPrompt("Please enter your message to send: ");
//                    String message = userInput.nextLine();
//                    messageController.messageTalkAttendees(selectedSpeakerTalks, message);
//                    break;
//                case VIEW_CONVERSATION:
//                    try {
//                        Set<String> myConversations = cm.getAllUserConversationRecipients(username);
//                        if (myConversations.isEmpty()) {
//                            this.oldPresenter.displayConversations("empty", myConversations);
//                        } else {
//                            this.oldPresenter.displayConversations("non_empty", myConversations);
//                            String user = userInput.nextLine();
//                            int pastMessages = userInput.nextInt();
//                            userInput.nextLine();
//                            this.viewMessagesFrom(user, pastMessages);
//                        }
//                    } catch (InputMismatchException e) {
//                        this.oldPresenter.displayConversationsErrors("mismatch");
//                    } catch (ObjectNotFoundException e) {
//                        this.oldPresenter.displayConversationsErrors("no_user");
//                    } catch (NullPointerException e) {
//                        this.oldPresenter.displayConversationsErrors("no_conversation");
//                    }
//                    break;
//                case MY_TALK_SCHEDULE:
//                    this.SeeSpeakerTalkSchedule();
//                    break;
//                case VIEW_SCHEDULE:
//                    this.SeeTalkSchedule();
//                    break;
//                case VIEW_MENU:
//                    oldPresenter.displaySpeakerMenu();
//                    break;
//                default:
//                    oldPresenter.displayPrompt("Invalid input, please try again:\n");
//            }
//            if (loggedIn) {
//                oldPresenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
//                command = userInput.nextLine();
//
//                validinput = false;
//                while (!validinput) {
//                    for(SpeakerMenuEnum commandEnum: commandlist){
//                        if (commandEnum.stringValue.equals(command)) {
//                            validinput = true;
//                            enumRequest = commandEnum;
//                            break;
//                        }
//                    }
//                    if(!validinput){
//                        oldPresenter.displayPrompt("Invalid input, please try again:\n");
//                        oldPresenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
//                        command = userInput.nextLine();
//                    }
//                }
//            }
//        }
        return programEnd;
    }
}
