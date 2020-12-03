package gateway;

import entities.Account;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

/**
 * The parameter objects that store all of the instances of use case classes
 */
public class DataManager {
    protected EventManager em;
    protected AccountManager am;
    protected FriendManager fm;
    protected ConversationManager cm;
    protected String username;

    /**
     * The constructor
     * @param eventManager The EventManager from database.
     * @param accountManager The AccountManager from database.
     * @param friendManager The FriendManager from database.
     * @param conversationManager The ConversationManager from database.
     */
    public DataManager(EventManager eventManager, AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager){
        em = eventManager;
        am = accountManager;
        fm = friendManager;
        cm = conversationManager;
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
     * The getter of friendManager
     * @return fm
     */
    public FriendManager getFriendManager(){
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
