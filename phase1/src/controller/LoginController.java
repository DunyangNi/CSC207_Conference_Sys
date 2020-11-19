package controller;

import gateway.DataManager;
import use_cases.*;
import presenter.*;

import javax.xml.soap.Text;
import java.util.Scanner;

public class LoginController {
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    // fields for presenter should be filled out
    private Presenter presenter = new TextPresenter();

    public LoginController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em, SignupManager sm) {
        this.accountManager = am;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.eventManager = em;
        this.signupManager = sm;
    }
    
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
        while (!accountManager.verifyPassword(username, password)) {
            presenter.displayPrompt("Incorrect password, please try again. Enter '*' to return to the start menu.");
            password = input.nextLine();
            if (password.equals("*")) {
                return false;
            }
        }
        return login(username);
    }

    private boolean login(String username) {
        boolean programEnd = false;
        if (accountManager.containsAttendee(username)) {
            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager, signupManager);
            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, friendManager, signupManager, accountManager, textpresenter);
            programEnd = ac.runInteraction();
        }
        if (accountManager.containsOrganizer(username)) {
            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager, signupManager);
            OrganizerController oc = new OrganizerController(username, accountManager, friendManager, conversationManager, eventManager, signupManager, textpresenter);
            programEnd = oc.runInteraction();
        }
        if (accountManager.containsSpeaker(username)) {
            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager, signupManager);
            SpeakerController sc = new SpeakerController(username, accountManager, friendManager, conversationManager, eventManager, signupManager, textpresenter);
            programEnd = sc.runInteraction();
        }
        DataManager dataManager = new DataManager();
        dataManager.saveManager("EventManager", "EventManager", eventManager);
        dataManager.saveManager("AccountManager", "AccountManager", accountManager);
        dataManager.saveManager("ConversationManager", "ConversationManager", conversationManager);
        dataManager.saveManager("FriendManager", "FriendManager", friendManager);
        dataManager.saveManager("SignupManager", "SignupManager", signupManager);
        return programEnd;
    }
}
