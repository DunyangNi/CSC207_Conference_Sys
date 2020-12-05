package controllers.event;

import use_cases.account.AccountManager;
import use_cases.event.EventManager;

import java.util.Calendar;
import java.util.HashMap;
// TODO: 12/04/20 Merge this class with EventController
/**
 * Allows the <code>Attendee</code> user to sign up or cancel signing up for a <code>Event</code>.
 */
public class SignupController {
    protected String username;
    protected AccountManager am;
    protected EventManager em;

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
        this.em = em;
        this.am = am;
    }

    /**
     * Signs up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void signupForEvent(Integer id) {
        if (!am.getVipStatus(username) || !em.getVipRestriction(id)){
            try {
                em.addAttendee(id, username);
                am.addEventToAttend(id, username);
            } catch (Exception e) {
                System.out.println(e.getMessage()); // to be replaced
            }
        }
        System.out.println("This event is restricted to VIPs.");
    }

    /**
     * Cancels signing up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void cancelSignupForEvent(Integer id) {
        try {
            em.removeAttendee(id, username);
            am.removeEventToAttend(id, username);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // to be replaced
        }
    }

    // TODO: 12/04/20 Implement this
    public void displayAttendeeSchedule(String username, HashMap<String[], Calendar> attendeeTalks) {
    }
}
