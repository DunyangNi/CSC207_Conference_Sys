package controller;

import Throwables.ObjectNotFoundException;
import use_cases.*;
import java.util.*;
import java.lang.*;
import presenter.*;

public class AttendeeController extends AccountController {

    public AttendeeController(String username, EventManager eventmanager, ConversationManager conversationManager,
                              FriendManager friendManager, SignupManager signupManager, AccountManager accountManager, Presenter presenter) {
        super(username, accountManager, friendManager, conversationManager, eventmanager, signupManager, presenter);
    }

    public void signupForTalk(Integer id) {
        try {
            signupManager.addAttendee(id, username);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }

    public void cancelSignupForTalk(Integer id) {
        try {
            signupManager.removeAttendee(id, username);
        } catch (Exception e) {
            presenter.displayPrompt(e.toString());
        }
    }

    public void seeAttendeeTalkSchedule() {
        this.presenter.displayAttendeeTalkSchedule(this.username);
    }

    @Override
    public boolean runInteraction() {
        boolean loggedIn = true;
        boolean programEnd = false;
        presenter.displayAttendeeMenu();
        Scanner userInput = new Scanner(System.in);
        String command = userInput.nextLine();
        while(loggedIn){
            switch (command) {
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
                    friendController.addFriend(contactToAdd);
                    break;
                case "2":
                    this.presenter.displayContactsPrompt("remove");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);
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
                    messageController.messageAttendee(message, attendeeUsername);
                    break;
                }
                case "5": {
                    //messageSpeaker(String message, String speakerusername)
                    this.presenter.displayMessagingPrompt("aSpeaker");
                    //String line1 = sc.nextLine();
                    String speakerUsername = userInput.nextLine();
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, speakerUsername);
                    break;
                }
                case "6":
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
                case "7":
                    this.SeeTalkSchedule();
                    break;
                case "8": {
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
                case "9": {
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
