package controllers.account;

import enums.ViewEnum;
import gateways.DataManager;
import use_cases.account.AccountManager;

// TODO: 12/07/20 Consider merging with AccountController
public class LoginController extends AccountController {
    private final AccountManager am;
    private final DataManager dm;

    /**
     * Manages login functionality for the program
     */
    public LoginController(DataManager dm) {
        super(dm);
        this.dm = dm;
        this.am = dm.getAccountManager();
    }

    public ViewEnum login(String username) {
        ViewEnum view;
        if (am.containsOrganizer(username)) {
            view = ViewEnum.ORGANIZER;
        } else if (am.containsSpeaker(username)) {
            view = ViewEnum.SPEAKER;
        } else {
            view = ViewEnum.ATTENDEE;
        }
        dm.setUsername(username); // TODO find more appropriate place to call this?
        return view;
    }

    public boolean isCorrectPassword(String username, String password) {
        return password.equals(am.getAccountHashMap().get(username).getPassword());
    }
}