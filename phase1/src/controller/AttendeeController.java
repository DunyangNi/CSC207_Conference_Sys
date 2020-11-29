package controller;

import Throwables.*;
import use_cases.*;
import java.util.*;
import java.lang.*;
import presenter.*;

public class AttendeeController extends AccountController {


    /**
     * Facilitates interaction with the user (organizer/speaker/attendee) upon login
     *
     * @param username                   username of account user
     * @param accountManager             manages data of all accounts in program
     * @param friendManager              manages data of contacts lists
     * @param conversationManager        manages conversation data
     * @param eventManager               manages event data
     * @param signupManager              manages event signup and related information
     * @param presenter                  defines the UI
     */
    public AttendeeController(String username, AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager, Presenter presenter) {
        super(username, accountManager, friendManager, conversationManager, eventManager, signupManager, presenter);
    }

    /**
     * Signs attendee up for a talk with the given id
     * @param id talk id
     */
    public void signupForTalk(Integer id) {
        try {
            signupManager.addAttendee(id, username);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }

    /**
     * Cancels talk with given id
     * @param id talk id
     */
    public void cancelSignupForTalk(Integer id) {
        try {
            signupManager.removeAttendee(id, username);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
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
    public boolean runInteraction() throws UserNotFoundException, UserNameNotFoundException, AlreadyExistException, InvalidIntegerException, MessageNotFound, EmptyListException, EventNotFoundException, EventFullException {
        boolean loggedIn = true;
        boolean programEnd = false;
        presenter.displayAttendeeMenu();
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();
        while(loggedIn){
            switch (command) {
                // TODO: 11/20/20 Add view all accounts command
                case "00":
                    loggedIn = false;
                    programEnd = true;
                    break;
                case "0":
                    loggedIn = false;
                    break;
                case "1":
                    this.presenter.displayContactsPrompt("add");
                    String contactToAdd = userInput.nextLine();
                    addFriendController.addFriend(contactToAdd);
                    break;
                case "2":
                    this.presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    removeFriendController.removeFriend(contactToRemove);
                    break;
                case "3":
                    this.viewContactList();
                    break;
                case "4": {
                    //messageAttendee(String message, String attendeeUsername)
                    this.presenter.displayMessagingPrompt("anAttendee");
                    //String line1 = sc.nextLine();
                    String attendeeUsername = userInput.nextLine();
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageAttendeeController.messageAttendee(message, attendeeUsername);
                    break;
                }
                case "5": {
                    //messageSpeaker(String message, String speakerusername)
                    this.presenter.displayMessagingPrompt("aSpeaker");
                    //String line1 = sc.nextLine();
                    String speakerUsername = userInput.nextLine();
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageSpeakerController.messageSpeaker(message, speakerUsername);
                    break;
                }
                case "6":{
                    Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                    if (myConversations.isEmpty()) {
                        this.presenter.displayConversations("empty", myConversations);
                    } else {
                        this.presenter.displayConversations("non_empty", myConversations);
                        String user = userInput.nextLine();
                        int pastMessages = userInput.nextInt();
                        userInput.nextLine();
                        System.out.println(viewConversationController.viewMessagesFrom(user, pastMessages));
                    break;
                    }
                }
                case "7":{
                    this.SeeTalkSchedule();
                    break;}
                case "8": {
                    this.presenter.displayTalkPrompt("attend");
                    String input = userInput.nextLine();
                    if(isNumeric(input)){
                        Integer id = Integer.parseInt(input);
                        eventSignUpController.signupForEvent(id);}
                    else{
                        this.presenter.displayTalkPrompt("invalid");
                    }
                    break;
                }
                case "9": {
                    this.presenter.displayTalkPrompt("cancel");
                    String input = userInput.nextLine();
                    if(isNumeric(input)){
                        Integer id = Integer.parseInt(input);
                        eventSignUpController.cancelForEvent(id);}
                    else{
                        this.presenter.displayTalkPrompt("invalid");
                    }
                    break;
                }
                case "10":
                    this.seeAttendeeTalkSchedule();
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
