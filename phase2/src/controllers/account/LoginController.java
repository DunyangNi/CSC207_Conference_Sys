package controllers.account;

import enums.ViewEnum;
import exceptions.conflict.IncorrectPasswordException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import use_cases.account.AccountManager;

/**
 * Manages login functionality for the program
 *
 * Fields:
 * AccountManager: Stores account in program
 * DataManager: Stores all data of program
 */
public class LoginController extends AccountController {
    private final AccountManager am;
    private final DataManager dm;

    /**
     * Creates an instance of <code>LoginController</code> with given parameters..
     *
     * @param dm Datamanager containing the desired AccountManager
     */
    public LoginController(DataManager dm) {
        super(dm);
        this.dm = dm;
        this.am = dm.getAccountManager();
    }


    /**
     * Attempts to log in an account with given username and password
     *
     * @return an enum representing the account type for the username provided
     * @param username login username
     * @param password login password
     */
    public ViewEnum login(String username, String password) throws IncorrectPasswordException, UserNotFoundException {
        if (!usernameExists(username)) {
            throw new UserNotFoundException();
        }
        if (!isCorrectPassword(username, password)) {
            throw new IncorrectPasswordException();
        }
        return loginHelper(username);
    }

    /**
     * Login Helper function.
     * @return an enum representing the account type for the username provided
     * @param username desired username
     */
    public ViewEnum loginHelper(String username) {
        ViewEnum view;
        if (am.containsOrganizer(username)) {
            view = ViewEnum.ORGANIZER;
        } else if (am.containsSpeaker(username)) {
            view = ViewEnum.SPEAKER;
        } else {
            if (am.isVipAttendee(username)) {
                view = ViewEnum.VIP_ATTENDEE;
            } else {
                view = ViewEnum.ATTENDEE;
            }
        }
        dm.setUsername(username);
        return view;
    }

    /**
     * Verifies a login attempt.
     *
     * @param username desired username
     * @param password desired password
     * @return True iff username is in AccountManager and the password is valid.
     */
    public boolean isCorrectPassword(String username, String password) {
        return password.equals(am.getAccountHashMap().get(username).getPassword());
    }
}