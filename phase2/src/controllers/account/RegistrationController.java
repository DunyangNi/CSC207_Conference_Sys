package controllers.account;

import enums.AccountTypeEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import gateways.*;
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
     */
    public RegistrationController(DataManager dm) {
        this.am = dm.getAccountManager();
        this.cm = dm.getConversationManager();
        this.fm = dm.getContactManager();
        this.em = dm.getEventManager();
    }

    public void register(AccountTypeEnum accountType, String username, String password) throws AccountAlreadyExistsException {
        switch (accountType) {
            case ATTENDEE:
                am.addNewAttendee(username, password, "", "");
                break;
            case SPEAKER:
                am.addNewSpeaker(username, password, "", "");
                break;
            case ORGANIZER:
                am.addNewOrganizer(username, password, "", "");
                break;
        }
        addNewAccountKeys(username);
    }

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

    public boolean usernameExists(String username) {
        return am.containsAccount(username);
    }
}
