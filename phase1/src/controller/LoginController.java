package controller;

import use_cases.*;
import presenter.Presenter;
import java.util.Scanner;

public class LoginController {
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    private Presenter presenter = new Presenter();
    private RegistrationController registrationController;

    public LoginController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em, SignupManager sm) {
        this.accountManager = am;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.eventManager = em;
        this.signupManager = sm;
        registrationController = new RegistrationController(accountManager, friendManager, conversationManager, eventManager, signupManager);
    }
    
    public void attemptLogin() {
        presenter.displayPrompt("Please enter your username:");
        Scanner input = new Scanner(System.in);
        String username = input.nextLine();

        while (!accountManager.containsAccount(username)) {
            presenter.displayPrompt("This username does not exist, please try again.\nEnter '*' to register a new account instead:");
            username = input.nextLine();
            if (username.equals("*")) {
                registrationController.attemptRegister();
            }
        }

        presenter.displayPrompt("Please enter your password:");
        String password = input.nextLine();

        while (!accountManager.verifyPassword(username, password)) {
            presenter.displayPrompt("Incorrect password, please try again\nEnter '*' to register a new account instead:");
            password = input.nextLine();
            if (username.equals("*")) {
                registrationController.attemptRegister();
            }
        }
        login(username);
    }

    private void login(String username) {
        if (accountManager.containsAttendee(username)) {
            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, friendManager, signupManager, accountManager);
            ac.runInteraction();
        }
        if (accountManager.containsOrganizer(username)) {
            OrganizerController oc = new OrganizerController(username, accountManager, friendManager, conversationManager, eventManager, signupManager);
            oc.runInteraction();
        }
        if (accountManager.containsSpeaker(username)) {
            SpeakerController sc = new SpeakerController(username, eventManager, accountManager, conversationManager, signupManager, friendManager);
            sc.runInteraction();
        }
    }
}
