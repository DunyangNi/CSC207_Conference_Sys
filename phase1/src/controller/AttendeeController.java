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
    public void runInteraction() {
        Scanner sc = new Scanner(System.in);
        boolean loop_on = true;
        while(loop_on){
            System.out.println("What would you like to do?");
            System.out.println("1 = see talk schedule");
            System.out.println("2 = sign up for a talk");
            System.out.println("3 = cancel enrolment for a talk");
            System.out.println("4 = see a schedule of talks you're attending");
            System.out.println("5 = message another attendee");
            System.out.println("6 = message a speaker");
            System.out.println("7 = add a new contact");
            System.out.println("8 = remove a contact");
            System.out.println("9 = see contacts list");
            System.out.println("10 = view your conversation with someone");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                this.SeeTalkSchedule();

            }
            else if(choice == 2) {
                System.out.print("Please enter the ID of the Talk you wish to attend: ");
                Integer id = Integer.parseInt(sc.nextLine());
                this.signupForTalk(id);

            }
            else if(choice == 3) {
                System.out.print("Please enter the ID of the Talk you wish to cancel: ");
                Integer id = Integer.parseInt(sc.nextLine());
                this.cancelSignupForTalk(id);
            }
            else if(choice == 4) {
                this.seeAttendeeTalkSchedule();
            }
            else if(choice == 5) {
                //messageAttendee(String message, String attendeeUsername)
                System.out.println("Specify the username of the attendee you're messaging");
                //String line1 = sc.nextLine();
                String attendeeUsername = sc.nextLine();
                System.out.println("Specify the message you're sending");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                messageController.messageAttendee(message, attendeeUsername);
            }
            else if(choice == 6) {
                //messageSpeaker(String message, String speakerusername)
                System.out.println("Specify the username of the speaker you're messaging");
                //String line1 = sc.nextLine();
                String speakerUsername = sc.nextLine();
                System.out.println("Specify the message you're sending");
                //line1 = sc.nextLine();
                String message = sc.nextLine();
                messageController.messageAttendee(message, speakerUsername);
            }
            else if(choice == 7) {
                System.out.print("Please enter the username of a contact to add: ");
                String contactToAdd = sc.nextLine();
                friendController.addFriend(contactToAdd);

            }
            else if(choice == 8) {
                System.out.print("Please enter the username of a contact to remove: ");
                String contactToRemove = sc.nextLine();
                friendController.removeFriend(contactToRemove);

            }

            else if(choice == 9) {
                this.seeFriendList();

            }

            else if(choice == 10) {
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
                        String user = sc.nextLine();
                        System.out.println("How many past messages would you like to see?");
                        int pastMessages = sc.nextInt();
                        sc.nextLine();
                        this.viewMessagesFrom(user, pastMessages);
                    }
                }
                catch(Exception e) {
                    System.out.println("\nSomething went wrong. Please enter valid input.\n");
                }
            }

            System.out.println("Thank you. Would you like to do another task?");
            System.out.println("1 = yes, 0 = no");
            int response = sc.nextInt();
            sc.nextLine();
            if(response == 0) {
                loop_on = false;
            }
        }

    }
}
