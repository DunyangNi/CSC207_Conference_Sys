package controller;

import gateway.DataManager;
import presenter.Presenter;
import presenter.TextPresenter;
import use_cases.*;

import java.util.Scanner;

public class LoginController {
    private final AccountManager accountManager;
    private final EventManager eventManager;
    private final ConversationManager conversationManager;
    private final FriendManager friendManager;
    // fields for presenter should be filled out
    private final Presenter presenter = new TextPresenter();

    /**
     * Manages login functionality for the program
     * @param am stores data of all accounts
     * @param fm manages friendlist functionality
     * @param cm manages conversation/messaging functionality
     * @param em manages event data
     */
    public LoginController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.eventManager = em;
    }

    /**
     * Attempts login on the user
     * @return True if the user wishes to terminate the program
     */
    public boolean attemptLogin() {
        presenter.displayPrompt("[LOGIN MENU]");
        Scanner input = new Scanner(System.in);

        presenter.displayPrompt("Enter your username:");
        String username = input.nextLine();
        while (!accountManager.containsAccount(username)) {
            presenter.displayPrompt("This username does not exist, please try again. Enter '*' to return to the start menu.");
            username = input.nextLine();
            if (username.equals("*")) {
                return false;
            }
        }
        presenter.displayPrompt("Enter your password:");
        String password = input.nextLine();
        while (!accountManager.isCorrectPassword(username, password)) {
            presenter.displayPrompt("Incorrect password, please try again. Enter '*' to return to the start menu.");
            password = input.nextLine();
            if (password.equals("*")) {
                return false;
            }
        }
        return login(username);
    }

    /**
     * Directs user to their respective menu of options depending on their account type
     * (Attendee, Organizer, or Speaker) and runs the menu interaction
     * @param username user's username
     * @return True if the user wishes to terminate the program
     */
    private boolean login(String username) {
        boolean programEnd = false;
        if (accountManager.containsAttendee(username)) {
            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager);
            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, friendManager, accountManager, textpresenter);
            programEnd = ac.runInteraction();
        }
        if (accountManager.containsOrganizer(username)) {
            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager);
            OrganizerController oc = new OrganizerController(username, accountManager, friendManager, conversationManager, eventManager, textpresenter);
            programEnd = oc.runInteraction();
        }
        if (accountManager.containsSpeaker(username)) {
            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager);
            SpeakerController sc = new SpeakerController(username, accountManager, friendManager, conversationManager, eventManager, textpresenter);
            programEnd = sc.runInteraction();
        }
        DataManager dataManager = new DataManager();
        dataManager.saveAllManagers(eventManager, accountManager, conversationManager, friendManager);
        return programEnd;
    }
}