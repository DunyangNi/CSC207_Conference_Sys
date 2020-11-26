package controller;

import Throwables.ObjectNotFoundException;
import use_cases.*;
import java.util.*;
import java.lang.*;
import presenter.*;
import Enums.*;

public class AttendeeController extends AccountController {
    private final SignupController signupController = new SignupController(username, accountManager, eventManager);

    /**
     * facilitates interaction with attendee upon login
     * @param username attendee username
     * @param eventmanager event data
     * @param conversationManager conversation data
     * @param friendManager contact information
     * @param accountManager data about all accounts in the program
     * @param presenter specifies the UI
     */
    public AttendeeController(String username, EventManager eventmanager, ConversationManager conversationManager,
                              FriendManager friendManager, AccountManager accountManager, Presenter presenter) {
        super(username, accountManager, friendManager, conversationManager, eventmanager, presenter);
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
        presenter.displayAttendeeMenu();
        Scanner userInput = new Scanner(System.in);
        AttendeeCommand[] commandlist = AttendeeCommand.values();
        String command = userInput.nextLine();

        boolean validinput = false;
        AttendeeCommand enumRequest = AttendeeCommand.EXIT;

        while (!validinput) {
            for(AttendeeCommand commandEnum: commandlist){
                if (commandEnum.command.equals(command)) {
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
                    friendController.addFriend(contactToAdd);
                    break;
                case REMOVE_CONTACT:
                    this.presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);
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
                    messageController.messageAttendee(message, attendeeUsername);
                    break;
                }
                case MESSAGE_SPEAKER: {
                    //messageSpeaker(String message, String speakerusername)
                    this.presenter.displayMessagingPrompt("aSpeaker");
                    //String line1 = sc.nextLine();
                    String speakerUsername = userInput.nextLine();
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, speakerUsername);
                    break;
                }
                case VIEW_CONVERSATION:
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
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
                    for(AttendeeCommand commandEnum: commandlist){
                        if (commandEnum.command.equals(command)) {
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
