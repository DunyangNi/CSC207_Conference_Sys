package controller;

import exceptions.already_exists.ObjectAlreadyExistsException;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

public class AccountCreationController {
    private AccountManager accountManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private EventManager eventManager;
    public AccountCreationController(AccountManager accountManager, ConversationManager conversationManager, FriendManager friendManager, EventManager eventManager){
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.friendManager = friendManager;
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
        friendManager.addAccountKey(username);
        eventManager.addSpeakerKey(username);
    }

    private void addNewAttendeeKeys(String username){
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
    }
}
