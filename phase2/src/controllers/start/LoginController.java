package controllers.start;

import enums.AccountType;
import use_cases.*;

public class LoginController {
    private final AccountManager am;
    private final EventManager em;
    private final ConversationManager cm;
    private final ContactManager fm;

    /**
     * Manages login functionality for the program
     * @param am stores data of all accounts
     * @param fm manages friendlist functionality
     * @param cm manages conversation/messaging functionality
     * @param em manages event data
     */
    public LoginController(AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
        this.am = am;
        this.cm = cm;
        this.fm = fm;
        this.em = em;
    }

    public AccountType login(String username) {
        if (am.containsOrganizer(username)) {
            return AccountType.ORGANIZER;
        } else if (am.containsSpeaker(username)) {
            return AccountType.SPEAKER;
        } else {
            return AccountType.ATTENDEE;
        }
    }

//    public boolean login(String username) {
//        boolean programEnd = false;
//        if (am.containsAttendee(username)) {
//            TextPresenter textpresenter = new TextPresenter(em, fm);
//            AttendeeController ac = new AttendeeController(username, am, fm, cm, em);
//            programEnd = ac.runInteraction();
//        }
//        if (am.containsOrganizer(username)) {
//            TextPresenter textpresenter = new TextPresenter(em, fm);
//            OrganizerController oc = new OrganizerController(username, am, fm, cm, em);
//            programEnd = oc.runInteraction();
//        }
//        if (am.containsSpeaker(username)) {
//            TextPresenter textpresenter = new TextPresenter(em, fm);
//            SpeakerController sc = new SpeakerController(username, am, fm, cm, em);
//            programEnd = sc.runInteraction();
//        }
//
//        AccountDataManager accountDataManager = new AccountDataManager();
//        ContactDataManager friendDataManager = new ContactDataManager();
//        ConversationDataManager conversationDataManager = new ConversationDataManager();
//        EventDataManager eventDataManager = new EventDataManager();
//
//        accountDataManager.saveManager("AccountManager", "AccountManager", am);
//        friendDataManager.saveManager("ContactManager", "ContactManager", fm);
//        conversationDataManager.saveManager("ConversationManager", "ConversationManager", cm);
//        eventDataManager.saveManager("EventManager", "EventManager", em);
//
//        return programEnd;
//    }

//    /**
//     * Attempts login on the user
//     * @return True if the user wishes to terminate the program
//     */
//    public boolean attemptLogin() {
//        Scanner input = new Scanner(System.in);
//
//        presenters.preUserInput();
//        String username = input.nextLine();
//        while (!accountManager.containsAccount(username)) {
//            presenters.takenUsernamePrompt();
//            username = input.nextLine();
//            if (username.equals("*")) {
//                return false;
//            }
//        }
//        presenters.passwordPrompt();
//        String password = input.nextLine();
//        while (!accountManager.isCorrectPassword(username, password)) {
//            presenters.incorrectPasswordPrompt();
//            password = input.nextLine();
//            if (password.equals("*")) {
//                return false;
//            }
//        }
//        presenters.postUserInput();
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
//            TextPresenter textpresenter = new TextPresenter(eventManager, contactManager);
//            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, contactManager, accountManager, textpresenter);
//            programEnd = ac.runInteraction();
//        }
//        if (accountManager.containsOrganizer(username)) {
//            TextPresenter textpresenter = new TextPresenter(eventManager, contactManager);
//            OrganizerController oc = new OrganizerController(username, accountManager, contactManager, conversationManager, eventManager, textpresenter);
//            programEnd = oc.runInteraction();
//        }
//        if (accountManager.containsSpeaker(username)) {
//            TextPresenter textpresenter = new TextPresenter(eventManager, contactManager);
//            SpeakerController sc = new SpeakerController(username, accountManager, contactManager, conversationManager, eventManager, textpresenter);
//            programEnd = sc.runInteraction();
//        }
//
//        RegistrationController.runDataManagers(accountManager, contactManager, conversationManager, eventManager);
//
//        return programEnd;
//    }
}