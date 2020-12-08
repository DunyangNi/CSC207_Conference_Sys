package controllers.account;

import enums.AccountTypeEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import exceptions.already_exists.ObjectAlreadyExistsException;
import gateways.*;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

// TODO: 12/07/20 Consider merging with AccountController
public class AccountRegistrationController extends AccountController {
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
    public AccountRegistrationController(DataManager dm) {
        super(dm);
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
                addNewSpeakerKeys(username);
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
        fm.addAccountKey(username);
        cm.addAccountKey(username);
    }

    private void addNewSpeakerKeys(String username) {
        em.addSpeakerKey(username);
    }

    public void createSpeakerAccount(String username, String password, String firstname, String lastname) throws ObjectAlreadyExistsException {
        this.am.addNewSpeaker(username, password, firstname, lastname);
        addNewSpeakerKeys(username);
    }

    public void createAttendeeAccount(String username, String password, String firstname, String lastname) throws ObjectAlreadyExistsException {
        this.am.addNewAttendee(username, password, firstname, lastname);
        addNewAttendeeKeys(username);
    }

    private void addNewAttendeeKeys(String username){
        cm.addAccountKey(username);
        fm.addAccountKey(username);
    }
}
