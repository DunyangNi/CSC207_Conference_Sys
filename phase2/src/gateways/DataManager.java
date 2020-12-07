package gateways;

import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

/**
 * The parameter objects that store all of the instances of use case classes
 */
public class DataManager {
    protected AccountManager am;
    protected ContactManager fm;
    protected ConversationManager cm;
    protected EventManager em;
    protected String username;

    /**
     * The constructor
     * @param accountManager The AccountManager from database.
     * @param contactManager The ContactManager from database.
     * @param conversationManager The ConversationManager from database.
     * @param eventManager The EventManager from database.
     */
    public DataManager(AccountManager accountManager, ContactManager contactManager, ConversationManager conversationManager, EventManager eventManager){
        am = accountManager;
        fm = contactManager;
        cm = conversationManager;
        em = eventManager;
    }

    /**
     * The getter of eventManager
     * @return em
     */
    public EventManager getEventManager(){
        return em;
    }

    /**
     * The getter of accountManager
     * @return am
     */
    public AccountManager getAccountManager(){
        return am;
    }

    /**
     * The getter of contactManager
     * @return fm
     */
    public ContactManager getContactManager(){
        return fm;
    }

    /**
     * The getter of conversationManager
     * @return cm
     */
    public ConversationManager getConversationManager(){
        return cm;
    }

    /**
     * The getter of username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * The setter of username
     * @param newUsername the input username
     */
    public void setUsername(String newUsername){
        username = newUsername;
    }

}