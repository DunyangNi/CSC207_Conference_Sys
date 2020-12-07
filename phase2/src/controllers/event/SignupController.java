package controllers.event;

import exceptions.conflict.AlreadySignedUpException;
import exceptions.conflict.EventIsFullException;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.conflict.VipRestrictionException;
import gateways.DataManager;
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
    public SignupController(DataManager dm) {
        this.username = dm.getUsername();
        this.em = dm.getEventManager();
        this.am = dm.getAccountManager();
    }

    /**
     * Signs up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void signupForEvent(Integer id) throws VipRestrictionException, EventNotFoundException,
            EventIsFullException, AlreadySignedUpException {
        if ((!am.getVipStatus(username)) && em.getVipRestriction(id)) {
            throw new VipRestrictionException();
        }
        else {
        em.addAttendee(id, username);
        am.addEventToAttend(id, username);
        }
    }

    /**
     * Cancels signing up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void cancelSignupForEvent(Integer id) throws EventNotFoundException, AttendeeNotFoundException {
        em.removeAttendee(id, username);
        am.removeEventToAttend(id, username);
    }
}
