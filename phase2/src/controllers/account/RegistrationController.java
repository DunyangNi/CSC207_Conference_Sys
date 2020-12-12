package controllers.account;

import enums.AccountTypeEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import gateways.DataManager;
import use_cases.message.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.message.ContactManager;

/**
 * Controls account registration functionality of the program
 */

public class RegistrationController extends AccountController {
    public final String ORGANIZER_CODE = "123456";
    public final String SPEAKER_CODE = "123456";
    public final String VIP_CODE = "123456";
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;

    /**
     * Creates an instance of <code>RegistrationController</code> with given parameters..
     *
     * @param dm Datamanager containing all needed managers
     */
    public RegistrationController(DataManager dm) {
        super(dm);
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
    }

    /**
     * Attempts to register an account.
     *
     * @param accountType an enum representing the type of account to be registered
     * @param username    username to register
     * @param password    password to register
     * @throws AccountAlreadyExistsException when username is taken
     */
    public void register(AccountTypeEnum accountType, String username, String password)
            throws AccountAlreadyExistsException {
        switch (accountType) {
            case ATTENDEE:
                am.addNewAttendee(username, password);
                break;
            case VIP_ATTENDEE:
                am.addNewVipAttendee(username, password);
                break;
            case SPEAKER:
                am.addNewSpeaker(username, password);
                break;
            case ORGANIZER:
                am.addNewOrganizer(username, password);
                break;
        }
        addNewAccountKeys(username);
        saveData();
    }

    /**
     * Gets the relevant registration code base on account type.
     *
     * @param accountType an enum representing the type of account to be registered
     * @return the registration code for the corresponding account type if it exists. otherwise return null
     */
    public String getRegistrationCode(AccountTypeEnum accountType) {
        switch (accountType) {
            case VIP_ATTENDEE:
                return VIP_CODE;
            case SPEAKER:
                return SPEAKER_CODE;
            case ORGANIZER:
                return ORGANIZER_CODE;
        }
        return null;
    }

    /**
     * helper method that adds the given username as a key to various
     * hashmaps in the use cases
     *
     * @param username given username of associated <code>Account</code>
     */
    private void addNewAccountKeys(String username) {
        fm.addAccountKey(username);
        cm.addAccountKey(username);
    }
}
