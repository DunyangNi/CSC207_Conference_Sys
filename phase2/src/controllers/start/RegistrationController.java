package controllers.start;

import exceptions.already_exists.AccountAlreadyExistsException;
import gateway.AccountDataManager;
import gateway.ContactDataManager;
import gateway.ConversationDataManager;
import gateway.EventDataManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

public class RegistrationController {
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    public final String ORGANIZER_CODE = "123456";
    public final String SPEAKER_CODE = "123456";

    /**
     * handles the creation of new organizer and attendee accounts for registration
     *
     * @param am manages data of all accounts in the program
     * @param fm manages friend list functionality
     * @param cm manages messaging functionality
     * @param em manages data of all events in the program
     */
    public RegistrationController(AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
        this.am = am;
        this.cm = cm;
        this.fm = fm;
        this.em = em;
    }

    // TODO: 11/28/20 Refactor methods to manage exceptions consistently
    public void register(String accountType, String username, String password) throws AccountAlreadyExistsException {
        if (accountType.equals("1")) {
            am.addNewAttendee(username, password, "", "");
        }
        else if (accountType.equals("2")) {
            am.addNewSpeaker(username, password, "", "");
        }
        else {
            am.addNewOrganizer(username, password, "", "");
        }
        addNewAccountKeys(username);

        AccountDataManager accountDataManager = new AccountDataManager();
        ContactDataManager contactDataManager = new ContactDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        EventDataManager eventDataManager = new EventDataManager();

        accountDataManager.saveManager("AccountManager", "AccountManager", am);
        contactDataManager.saveManager("ContactManager", "ContactManager", fm);
        conversationDataManager.saveManager("ConversationManager", "ConversationManager", cm);
        eventDataManager.saveManager("EventManager", "EventManager", em);
    }

//    /**
//     * prompts user for which account they wish to create (attendee or organizer) and then attempts
//     * to create a new account for them according to username/password specifications by the user
//     *
//     * @return false
//     */
//    public boolean attemptRegister() {
//        presenters.preUserInput("accountType");
//        String accountType = input.nextLine();
//
//        while (!(accountType.equals("1") || (accountType.equals("2")))) {
//            presenters.postUserInput("accountType");
//            accountType = input.nextLine();
//        }
//
//        String[] accountInfo = getNewAccountInfo(accountType);
//        if (accountType.equals("1")) {
//            accountManager.addNewAttendee(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
//        } else {
//            accountManager.addNewOrganizer(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
//        }
//        addNewAccountKeys(accountInfo[0]);
//
//        AccountDataManager accountDataManager = new AccountDataManager();
//        EventDataManager eventDataManager = new EventDataManager();
//        ConversationDataManager conversationDataManager = new ConversationDataManager();
//        ContactDataManager friendDataManager = new ContactDataManager();
//
//        accountDataManager.saveManager("AccountManager", "AccountManager", accountManager);
//        eventDataManager.saveManager("EventManager", "EventManager", eventManager);
//        conversationDataManager.saveManager("ConversationManager", "ConversationManager", conversationManager);
//        friendDataManager.saveManager("ContactManager", "ContactManager", contactManager);
//
//        return false;
//    }
//
//
//    /**
//     * Prompts the user for the organizer account registration code
//     */
//    private void requireOrganizerPassword() {
//        String ORGANIZER_REGISTRATION_CODE = "123456";
//
//        presenters.preUserInput("code");
//        String code = input.nextLine();
//        while (!code.equals(ORGANIZER_REGISTRATION_CODE)) {
//            presenters.postUserInput("code");
//            code = input.nextLine();
//        }
//    }
//
//    /**
//     * prompts user for username and password
//     *
//     * @param type 1 if account type is Attendee, 2 if account type is Organizer
//     * @return username and password info
//     */
//    private String[] getNewAccountInfo(String type) {
//        if (type.equals("2")) {
//            requireOrganizerPassword();
//        }
//        presenters.preUserInput("username");
//        String username = input.nextLine();
//
//        while ((accountManager.containsAccount(username))) {
//            presenters.postUserInput("username");
//            username = input.nextLine();
//        }
//
//        // Obtain rest of information and bundle into Tuple of 4
//        presenters.preUserInput("password");
//        String password = input.nextLine();
//
//        return new String[]{username, password, "", ""};
//    }

    /**
     * helper method that adds the given username as a key to various
     * hashmaps in the use cases
     *
     * @param username given username of associated <code>Account</code>
     */
    private void addNewAccountKeys(String username) {
        cm.addAccountKey(username);
        fm.addAccountKey(username);
    }
}
