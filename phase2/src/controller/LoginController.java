package controller;

import gateway.AccountDataManager;
import gateway.ConversationDataManager;
import gateway.EventDataManager;
import gateway.FriendDataManager;
import presenter.*;
import use_cases.*;

public class LoginController {
    private final AccountManager am;
    private final EventManager em;
    private final ConversationManager cm;
    private final FriendManager fm;

    /**
     * Manages login functionality for the program
     * @param am stores data of all accounts
     * @param fm manages friendlist functionality
     * @param cm manages conversation/messaging functionality
     * @param em manages event data
     */
    public LoginController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.am = am;
        this.cm = cm;
        this.fm = fm;
        this.em = em;
    }

    public boolean login(String username) {
        boolean programEnd = false;
        if (am.containsAttendee(username)) {
            TextPresenter textpresenter = new TextPresenter(em, fm);
            AttendeeController ac = new AttendeeController(username, am, fm, cm, em);
            programEnd = ac.runInteraction();
        }
        if (am.containsOrganizer(username)) {
            TextPresenter textpresenter = new TextPresenter(em, fm);
            OrganizerController oc = new OrganizerController(username, am, fm, cm, em);
            programEnd = oc.runInteraction();
        }
        if (am.containsSpeaker(username)) {
            TextPresenter textpresenter = new TextPresenter(em, fm);
            SpeakerController sc = new SpeakerController(username, am, fm, cm, em);
            programEnd = sc.runInteraction();
        }

        AccountDataManager accountDataManager = new AccountDataManager();
        FriendDataManager friendDataManager = new FriendDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        EventDataManager eventDataManager = new EventDataManager();

        accountDataManager.saveManager("AccountManager", "AccountManager", am);
        friendDataManager.saveManager("FriendManager", "FriendManager", fm);
        conversationDataManager.saveManager("ConversationManager", "ConversationManager", cm);
        eventDataManager.saveManager("EventManager", "EventManager", em);

        return programEnd;
    }

//    /**
//     * Attempts login on the user
//     * @return True if the user wishes to terminate the program
//     */
//    public boolean attemptLogin() {
//        Scanner input = new Scanner(System.in);
//
//        presenter.preUserInput();
//        String username = input.nextLine();
//        while (!accountManager.containsAccount(username)) {
//            presenter.takenUsernamePrompt();
//            username = input.nextLine();
//            if (username.equals("*")) {
//                return false;
//            }
//        }
//        presenter.passwordPrompt();
//        String password = input.nextLine();
//        while (!accountManager.isCorrectPassword(username, password)) {
//            presenter.incorrectPasswordPrompt();
//            password = input.nextLine();
//            if (password.equals("*")) {
//                return false;
//            }
//        }
//        presenter.postUserInput();
//        return login(username);
//    }
//
//    /**
//     * Directs user to their respective menu of options depending on their account type
//     * (Attendee, Organizer, or Speaker) and runs the menu interaction
//     * @param username user's username
//     * @return True if the user wishes to terminate the program
//     */
//    private boolean login(String username) {
//        boolean programEnd = false;
//        if (accountManager.containsAttendee(username)) {
//            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager);
//            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, friendManager, accountManager, textpresenter);
//            programEnd = ac.runInteraction();
//        }
//        if (accountManager.containsOrganizer(username)) {
//            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager);
//            OrganizerController oc = new OrganizerController(username, accountManager, friendManager, conversationManager, eventManager, textpresenter);
//            programEnd = oc.runInteraction();
//        }
//        if (accountManager.containsSpeaker(username)) {
//            TextPresenter textpresenter = new TextPresenter(eventManager, friendManager);
//            SpeakerController sc = new SpeakerController(username, accountManager, friendManager, conversationManager, eventManager, textpresenter);
//            programEnd = sc.runInteraction();
//        }
//
//        RegistrationController.runDataManagers(accountManager, friendManager, conversationManager, eventManager);
//
//        return programEnd;
//    }
}