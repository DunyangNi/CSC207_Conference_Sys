package controller;

import Throwables.UserNameNotFoundException;
import Throwables.UserNotFoundException;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.SignupManager;

/**
 * The controller responsible for sending message to any type of accounts.
 */
public class MessageAccountController{

    protected String username;
    protected AccountManager accountManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;
    protected SignupManager signupManager;

    /**
     * Manages generic messaging functionality for user with given username
     *
     * @param username            user username
     * @param accountManager      manages data of all accounts in program
     * @param conversationManager manages messaging functionality
     * @param eventManager        manages event data
     * @param signupManager       manages signupfunctionality
     */
    public MessageAccountController(String username, AccountManager accountManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager) {
        this.username = username;
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
    }

    /**
     * sends a message to account with specified username
     * @param message the message to be sent
     * @param account recipient account username
     */
    public void messageAccount(String message, String account) throws UserNotFoundException, UserNameNotFoundException {
        conversationManager.sendMessage(this.username, account, message);
    }
}
