package controllers.message;

import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateway.DataManager;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

/**
 * The controller responsible for sending message to any type of accounts.
 */
public class MessageAccountController{

    protected String username;
    protected AccountManager accountManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;

    /**
     * Manages generic messaging functionality for user with given username
     *
     * @param username            user username
     * @param accountManager      manages data of all accounts in program
     * @param conversationManager manages messaging functionality
     * @param eventManager        manages event data
     */
    public MessageAccountController(DataManager dm) {
        this.username = dm.getUsername();
        this.accountManager = dm.getAccountManager();
        this.conversationManager = dm.getConversationManager();
        this.eventManager = dm.getEventManager();
    }

    public void messageAccount(String message, String account) throws UserNotFoundException, RecipientNotFoundException {
        conversationManager.sendMessage(this.username, account, message);
    }
}
