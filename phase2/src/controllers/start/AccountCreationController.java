package controllers.start;

import exceptions.already_exists.ObjectAlreadyExistsException;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.account.ContactManager;

public class AccountCreationController {
    private AccountManager accountManager;
    private ConversationManager conversationManager;
    private ContactManager contactManager;
    private EventManager eventManager;
    public AccountCreationController(AccountManager accountManager, ConversationManager conversationManager, ContactManager contactManager, EventManager eventManager){
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.contactManager = contactManager;
        this.eventManager = eventManager;
    }

    /**
     *
     * @param username
     * @param password
     * @param firstname
     * @param lastname
     * @throws ObjectAlreadyExistsException if the username already exist
     */
    public void createSpeakerAccount(String username, String password, String firstname, String lastname) throws ObjectAlreadyExistsException {
        this.accountManager.addNewSpeaker(username, password, firstname, lastname);
        addNewSpeakerKeys(username);
    }

    public void createAttendeeAccount(String username, String password, String firstname, String lastname) throws ObjectAlreadyExistsException {
        this.accountManager.addNewAttendee(username, password, firstname, lastname);
        addNewAttendeeKeys(username);
    }

    /**
     * helper function that adds a user's username as keys to certain
     * hashmaps in the use cases
     * @param username specified username
     */
    // (NEW!) (Helper)
    private void addNewSpeakerKeys(String username) {
        conversationManager.addAccountKey(username);
        contactManager.addAccountKey(username);
        eventManager.addSpeakerKey(username);
    }

    private void addNewAttendeeKeys(String username){
        conversationManager.addAccountKey(username);
        contactManager.addAccountKey(username);
    }
}
