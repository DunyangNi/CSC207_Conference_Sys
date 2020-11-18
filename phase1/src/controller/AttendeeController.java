package controller;

import use_cases.*;
import java.util.*;
import java.lang.*;

public class AttendeeController extends AccountController {

    public AttendeeController(String username, EventManager eventmanager, ConversationManager conversationManager,
                              FriendManager friendManager, SignupManager signupManager, AccountManager accountManager) {
        super(username, accountManager, friendManager, conversationManager, eventmanager, signupManager);
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
                    this.SeeTalkSchedule();
                    break;
                case "2": {
                    System.out.print("Please enter the ID of the Talk you wish to attend: ");
                    Integer id = Integer.parseInt(userInput.nextLine());
                    this.signupForTalk(id);
                    break;
                }
                case "3": {
                    System.out.print("Please enter the ID of the Talk you wish to cancel: ");
                    Integer id = Integer.parseInt(userInput.nextLine());
                    this.cancelSignupForTalk(id);
                    break;
                }
                case "4":
                    this.seeAttendeeTalkSchedule();
                    break;
                case "5": {
                    //messageAttendee(String message, String attendeeUsername)
                    System.out.println("Specify the username of the attendee you're messaging");
                    //String line1 = sc.nextLine();
                    String attendeeUsername = userInput.nextLine();
                    System.out.println("Specify the message you're sending");
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageAttendee(message, attendeeUsername);
                    break;
                }
                case "6": {
                    //messageSpeaker(String message, String speakerusername)
                    System.out.println("Specify the username of the speaker you're messaging");
                    //String line1 = sc.nextLine();
                    String speakerUsername = userInput.nextLine();
                    System.out.println("Specify the message you're sending");
                    //line1 = sc.nextLine();
                    String message = userInput.nextLine();
                    messageController.messageSpeaker(message, speakerUsername);
                    break;
                }
                case "7":
                    System.out.print("Please enter the username of a contact to add: ");
                    String contactToAdd = userInput.nextLine();
                    friendController.addFriend(contactToAdd);

                    break;
                case "8":
                    System.out.print("Please enter the username of a contact to remove: ");
                    String contactToRemove = userInput.nextLine();
                    friendController.removeFriend(contactToRemove);

                    break;
                case "9":
                    this.seeFriendList();

                    break;
                case "10":
                    try {
                        Set<String> myConversations = conversationManager.getAllUserConversationRecipients(username);
                        if (myConversations.isEmpty()) {
                            System.out.println("(No conversations)");
                        } else {
                            System.out.println("List of Conversation Recipients");
                            System.out.println("---------------------------------------------");
                            for (String recipient : myConversations) {
                                System.out.println(recipient);
                            }
                            System.out.println("---------------------------------------------\n");
                            System.out.print("To access a conversation, please enter the recipient's username: ");
                            String user = userInput.nextLine();
                            System.out.println("How many past messages would you like to see?");
                            int pastMessages = userInput.nextInt();
                            userInput.nextLine();
                            this.viewMessagesFrom(user, pastMessages);
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
                    }
                    break;
            }
            if (loggedIn) {
                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
                command = userInput.nextLine();
                if (command.equals("*"))
                    presenter.displayOrganizerMenu();
            }
        }
        return programEnd;
    }
}
