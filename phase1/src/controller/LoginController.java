package controller;

import use_cases.*;

import java.util.Scanner;

public class LoginController {
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;

    public LoginController(AccountManager am, ConversationManager cm, FriendManager fm, EventManager em, SignupManager sm) {
        this.accountManager = am;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.eventManager = em;
        this.signupManager = sm;
    }
    
    public void login() {
        System.out.println("Please enter your username:");
        Scanner input = new Scanner(System.in);
        String username = input.nextLine();

        while (!accountManager.containsAccount(username)) {
            System.out.println("This username does not exist, please try again:");
            username = input.nextLine();
        }

        System.out.println("Please enter your password:");
        String password = input.nextLine();

        while (!accountManager.verifyPassword(username, password)) {
            System.out.println("Incorrect password, please try again:");
            password = input.nextLine();
        }

        accountSelector(username);
    }
    
    private void accountSelector(String username) {
        if (accountManager.containsAttendee(username)) {
            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, friendManager, signupManager, accountManager);
            ac.runInteraction();
        }
        if (accountManager.containsOrganizer(username)) {
            OrganizerController oc = new OrganizerController(username, eventManager, accountManager, signupManager, conversationManager, friendManager);
            oc.runInteraction();
        }
        if (accountManager.containsSpeaker(username)) {
            SpeakerController sc = new SpeakerController(username, eventManager, accountManager, conversationManager, signupManager, friendManager);
            sc.runInteraction();
        }
    }
}
