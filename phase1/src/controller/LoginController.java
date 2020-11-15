package controller;

import Throwables.ObjectNotFoundException;
import use_cases.*;
import java.util.Scanner;

public class LoginController {
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    Scanner input;

    public LoginController(AccountManager am, EventManager em, ConversationManager cm,
                           FriendManager fm, SignupManager sm, Scanner input){
        this.accountManager = am;
        this.eventManager = em;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.signupManager = sm;
        this.input = input;
    }

    public void login(){
        boolean incorrectUsername; String username; String prompt; int firstMessage = 0;
        do {
            prompt = firstMessage == 0 ? "Please enter your username: " : "This username does not exist, please enter again: ";
            System.out.print(prompt);
            username = input.nextLine();
            incorrectUsername = !loginHelper(username);
            firstMessage = incorrectUsername ? 1 : 0;
        }
        while (incorrectUsername);
    }

    private void password(String username){
        boolean incorrectPassword; String password; String prompt; int firstMessage = 0;
        do {
            prompt = firstMessage == 0 ? "Please enter the password: " : "Wrong! Please enter again: ";
            System.out.print(prompt);
            password = input.nextLine();
            incorrectPassword = !accountManager.checkPassword(username, password);
            firstMessage = incorrectPassword ? 1 : 0;
        }
        while (incorrectPassword);
    }

    private boolean loginHelper(String username){
        if (accountManager.containsAttendee(username)) {
            password(username);
            AttendeeController ac = new AttendeeController(
                    username, eventManager, conversationManager,
                    friendManager, signupManager, accountManager);
            ac.runInteraction();
            return true;
        }
        if (accountManager.containsOrganizer(username)) {
            password(username);
            OrganizerController oc = new OrganizerController(
                    username, eventManager, accountManager, signupManager,
                    conversationManager, friendManager);
            oc.runInteraction();
            return true;
        }
        if (accountManager.containsSpeaker(username)) {
            password(username);
            SpeakerController sc = new SpeakerController(
                    username, eventManager, accountManager, conversationManager, signupManager, friendManager);
            sc.runInteraction();
            return true;
        }
        return false;
    }
}
