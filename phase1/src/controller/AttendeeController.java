package controller;

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
                case "7":
                    this.SeeTalkSchedule();
                    break;
                case "8": {
                    this.presenter.displayPrompt("Please enter the ID of the Talk you wish to attend: ");
                    String input = userInput.nextLine();
                    if(isNumeric(input)){
                        Integer id = Integer.parseInt(input);
                        this.signupForTalk(id);}
                    else{
                        this.presenter.displayPrompt("Invalid Talk ID.");
                    }
                    break;
                }
                case "9": {
                    this.presenter.displayPrompt("Please enter the ID of the Talk you wish to cancel: ");
                    String input = userInput.nextLine();
                    if(isNumeric(input)){
                        Integer id = Integer.parseInt(input);
                        this.cancelSignupForTalk(id);}
                    else{
                        this.presenter.displayPrompt("Invalid Talk ID.");
                    }
                    break;
                }
                case "10":
                    this.seeAttendeeTalkSchedule();
                    break;
                case "4": {
                    //messageAttendee(String message, String attendeeUsername)
                    this.presenter.displayPrompt("Specify the username of the attendee you're messaging");
                    //String line1 = sc.nextLine();
                    String attendeeUsername = userInput.nextLine();
                    this.presenter.displayPrompt("Specify the message you're sending");
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageAttendee(message, attendeeUsername);
                    break;
                }
                case "5": {
                    //messageSpeaker(String message, String speakerusername)
                    this.presenter.displayPrompt("Specify the username of the speaker you're messaging");
                    //String line1 = sc.nextLine();
                    String speakerUsername = userInput.nextLine();
                    this.presenter.displayPrompt("Specify the message you're sending");
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, speakerUsername);
                    break;
                }
                case "1":
                    this.presenter.displayPrompt("Please enter the username of a contact to add: ");
                    String contactToAdd = userInput.nextLine();
                    friendController.addFriend(contactToAdd);

                    break;
                case "2":
                    this.presenter.displayPrompt("Please enter the username of a contact to remove: ");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);

                    break;
                case "3":
                    this.viewContactList();

                    break;
                case "6":
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            this.presenter.displayPrompt("(No conversations)");
                        } else {
                            this.presenter.displayPrompt("List of Conversation Recipients");
                            this.presenter.displayPrompt("---------------------------------------------");
                            for (String recipient : myConversations) {
                                this.presenter.displayPrompt(recipient);
                            }
                            this.presenter.displayPrompt("---------------------------------------------\n");
                            this.presenter.displayPrompt("To access a conversation, please enter the recipient's username: ");
                            String user = userInput.nextLine();
                            this.presenter.displayPrompt("How many past messages would you like to see?");
                            int pastMessages = userInput.nextInt();
                            userInput.nextLine();
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (Exception e) {
                        this.presenter.displayPrompt(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
                    }
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
