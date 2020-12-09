package gateways;

import use_cases.RequestManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.event.LocationManager;

import javax.swing.text.html.HTML;

/**
 * The parameter objects that store all of the instances of use case classes
 */
public class DataManager {
    protected AccountManager accountManager;
    protected ContactManager contactManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;
    protected LocationManager locationManager;
    protected RequestManager requestManager;
    protected HTMLManager htmlManager;
    protected String username;


    public DataManager(AccountManager am, ContactManager fm, ConversationManager cm, EventManager em, LocationManager lm, RequestManager rm) {
        this.accountManager = am;
        this.contactManager = fm;
        this.conversationManager = cm;
        this.eventManager = em;
        this.locationManager = lm;
        this.requestManager = rm;
        this.htmlManager = new HTMLManager(em);
    }

    /**
     * The getter of eventManager
     * @return em
     */
    public EventManager getEventManager(){
        return eventManager;
    }

    /**
     * The getter of accountManager
     * @return am
     */
    public AccountManager getAccountManager(){
        return accountManager;
    }

    /**
     * The getter of contactManager
     * @return fm
     */
    public ContactManager getContactManager(){
        return contactManager;
    }

    /**
     * The getter of conversationManager
     * @return cm
     */
    public ConversationManager getConversationManager(){
        return conversationManager;
    }

    public LocationManager getLocationManager() { return locationManager; }

    public RequestManager getRequestManager() { return requestManager; }

    public HTMLManager getHtmlManager() { return htmlManager; }

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
