package controller;

import use_cases.ConversationManager;
import use_cases.AccountManager;
import use_cases.EventManager;
import use_cases.SignupManager;
import presenter.*;

import java.util.*;

public class MessageController {
    protected String username;
    protected AccountManager accountManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;
    protected SignupManager signupManager;

    /**
     * Manages generic messaging functionality for user with given username
     * @param username user username
     * @param accountManager manages data of all accounts in program
     * @param conversationManager manages messaging functionality
     * @param eventManager manages event data
     * @param signupManager manages signupfunctionality
     */
    public MessageController(String username, AccountManager accountManager, ConversationManager conversationManager,
                             EventManager eventManager, SignupManager signupManager){
        this.username = username;
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
    }












}
