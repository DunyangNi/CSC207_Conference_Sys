package deprecated;

import controller.AccountController;
import controller.SignupController;
import exceptions.HTMLWriteErrorException;
import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateway.HTMLManager;
import use_cases.*;
import java.util.*;
import java.lang.*;

import enums.*;

public class AttendeeController extends AccountController {
    private final SignupController signupController = new SignupController(username, am, em);

    /**
     * facilitates interaction with attendee upon login
     * @param username attendee username
     * @param accountManager data about all accounts in the program
     * @param friendManager contact information
     * @param conversationManager conversation data
     * @param eventmanager event data
     */
    public AttendeeController(String username, AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventmanager) {
        super(username, accountManager, friendManager, conversationManager, eventmanager);
    }

    /**
     * Signs up <code>Attendee</code> user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void signupForTalk(Integer id) {
        signupController.signupForEvent(id);
    }

    /**
     * Cancels signing up <code>Attendee</code> user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void cancelSignupForTalk(Integer id) {
        signupController.cancelSignupForEvent(id);
    }

    /**
     * displays the schedule of talks that the attendee is attending
     */
    public void seeAttendeeTalkSchedule() {
        this.presenter.displayAttendeeTalkSchedule(this.username);
    }

    /**
     * Interacts with attendee via a menu of options
     * @return True if attendee wishes to terminate the program
     */
    @Override
    public boolean runInteraction() {
        boolean programEnd = false;
        boolean loggedIn = true;
        Scanner userInput = new Scanner(System.in);
        AttendeeEnum[] commandlist = AttendeeEnum.values();
        String command = userInput.nextLine();

        boolean validinput = false;
        AttendeeEnum enumRequest = AttendeeEnum.EXIT;

        while (!validinput) {
            for(AttendeeEnum commandEnum: commandlist){
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
                // TODO: 11/20/20 Add view all accounts command
                case EXIT:
                    loggedIn = false;
                    programEnd = true;
                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case ADD_CONTACT:
                    this.presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    try {
                        friendController.addFriend(contactToAdd);
                    } catch (UserNotFoundException | FriendNotFoundException | AlreadyFriendException e) {
                        e.printStackTrace();
                    }
                    break;
                case REMOVE_CONTACT:
                    this.presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    try {
                        friendController.removeFriend(contactToRemove);
                    } catch (ObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case VIEW_CONTACTS:
                    this.viewContactList();
                    break;
                case MESSAGE_ATTENDEE: {
                    //messageAttendee(String message, String attendeeUsername)
                    this.presenter.displayMessagingPrompt("anAttendee");
                    //String line1 = sc.nextLine();
                    String attendeeUsername = userInput.nextLine();
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    try {
                        messageController.messageAttendee(message, attendeeUsername);
                    } catch (UserNotFoundException | RecipientNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case MESSAGE_SPEAKER: {
                    //messageSpeaker(String message, String speakerusername)
                    this.presenter.displayMessagingPrompt("aSpeaker");
                    //String line1 = sc.nextLine();
                    String speakerUsername = userInput.nextLine();
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    try {
                        messageController.messageSpeaker(message, speakerUsername);
                    } catch (UserNotFoundException | RecipientNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
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
                case VIEW_SCHEDULE:
                    this.SeeTalkSchedule();
                    break;
                case SIGNUP_EVENT: {
                    this.presenter.displayTalkPrompt("attend");
                    String input = userInput.nextLine();
                    if(isNumeric(input)){
                        Integer id = Integer.parseInt(input);
                        this.signupForTalk(id);}
                    else{
                        this.presenter.displayTalkPrompt("invalid");
                    }
                    break;
                }
                case LEAVE_EVENT: {
                    this.presenter.displayTalkPrompt("cancel");
                    String input = userInput.nextLine();
                    if(isNumeric(input)){
                        Integer id = Integer.parseInt(input);
                        this.cancelSignupForTalk(id);}
                    else{
                        this.presenter.displayTalkPrompt("invalid");
                    }
                    break;
                }
                case VIEW_MY_SCHEDULE:
                    this.seeAttendeeTalkSchedule();
                    break;
                case DOWNLOAD_SCHEDULE: {  // download all event schedule in HTML
                    this.presenter.displayDownloadSchedulePrompt();
                    command = userInput.nextLine();
                    if (command.equalsIgnoreCase("Y")) {
                        try {
                            HTMLManager hm = new HTMLManager(em);
                            hm.generateHTML();
                            hm.openHTML();
                            this.presenter.displayPrompt("Successful! downloaded: " +
                                    hm.getDownloadLocation());
                        } catch (HTMLWriteErrorException e) { // wrong while processing HTML
                            this.presenter.displayPrompt("Internal Error during HTML processing");
                        }
                    } else {
                        presenter.displayPrompt("Aborted");
                    }
                    presenter.displayPrompt("");
                    break;
                }
                case VIEW_MENU:
                    presenter.displayAttendeeMenu();
                    break;
                default:
                    presenter.displayPrompt("Invalid input, please try again:\n");
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();

                validinput = false;
                while (!validinput) {
                    for(AttendeeEnum commandEnum: commandlist){
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
