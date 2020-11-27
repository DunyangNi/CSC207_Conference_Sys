package controller;

import gateway.AccountDataManager;
import gateway.ConversationDataManager;
import gateway.EventDataManager;
import gateway.FriendDataManager;
import presenter.*;
import use_cases.*;

import java.util.Scanner;

public class LoginController {
    private final AccountManager accountManager;
    private final EventManager eventManager;
    private final ConversationManager conversationManager;
    private final FriendManager friendManager;
    // fields for presenter should be filled out
    private final LoginPresenter presenter = new LoginPresenter();

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

//    public boolean tryUsername(String username) {
//         return accountManager.containsAccount(username);
//    }
//
//    public boolean tryPassword(String username, String password) {
//        return accountManager.isCorrectPassword(username, password);
//    }
//
//    public boolean attemptLogin(String username, String password) {
//        if (!accountManager.containsAccount(username) || !accountManager.isCorrectPassword(username, password) || username.equals("*") || password.equals("*")) {
//            return false;
//            }
//        return login(username);
//    }

    /**
     * Attempts login on the user
     * @return True if the user wishes to terminate the program
     */
    public boolean attemptLogin() {
        Scanner input = new Scanner(System.in);

        presenter.preUserInput();
        String username = input.nextLine();
        while (!accountManager.containsAccount(username)) {
            presenter.postUsernameInput();
            username = input.nextLine();
            if (username.equals("*")) {
                return false;
            }
        }
        presenter.prePasswordInput();
        String password = input.nextLine();
        while (!accountManager.isCorrectPassword(username, password)) {
            presenter.postPasswordInput();
            password = input.nextLine();
            if (password.equals("*")) {
                return false;
            }
        }
        presenter.postUserInput();
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

        AccountDataManager accountDataManager = new AccountDataManager();
        EventDataManager eventDataManager = new EventDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        FriendDataManager friendDataManager = new FriendDataManager();

        accountDataManager.saveManager("AccountManager", "AccountManager", accountManager);
        eventDataManager.saveManager("EventManager", "EventManager", eventManager);
        conversationDataManager.saveManager("ConversationManager", "ConversationnManager", conversationManager);
        friendDataManager.saveManager("FriendManager", "FriendManager", friendManager);

        return programEnd;
    }
}