package controller;

import use_cases.AccountManager;
import use_cases.EventManager;

/**
 * Allows the <code>Attendee</code> user to sign up or cancel signing up for a <code>Event</code>.
 */
public class SignupController {
    protected String username;
    protected AccountManager accountManager;
    protected EventManager eventManager;

    /**
     * Creates an instance of <code>SignupController</code> with given username, <code>AccountManager</code>,
     * and <code>EventManager</code>.
     *
     * @param username given username of user
     * @param am given <code>AccountManager</code> managing data of all Accounts in system
     * @param em given <code>EventManager</code>
     */
    public SignupController(String username, AccountManager am, EventManager em) {
        this.username = username;
        this.eventManager = em;
        this.accountManager = am;
    }

    /**
     * Signs up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void signupForEvent(Integer id) {
        try {
            eventManager.addAttendee(id, username);
            accountManager.addEventToAttend(id, username);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // to be replaced
        }
    }

    /**
     * Cancels signing up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void cancelSignupForEvent(Integer id) {
        try {
            eventManager.removeAttendee(id, username);
            accountManager.removeEventToAttend(id, username);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // to be replaced
        }
    }
}
