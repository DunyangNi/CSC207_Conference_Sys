package controllers.account;

import enums.AccountTypeEnum;
import gateways.DataManager;
import use_cases.account.AccountManager;

// TODO: 12/07/20 Consider merging with AccountController
public class LoginController extends AccountController {
    private final AccountManager am;

    /**
     * Manages login functionality for the program
     */
    public LoginController(DataManager dm) {
        super(dm);
        this.am = dm.getAccountManager();
    }

    public AccountTypeEnum login(String username) {
        if (am.containsOrganizer(username)) {
            return AccountTypeEnum.ORGANIZER;
        } else if (am.containsSpeaker(username)) {
            return AccountTypeEnum.SPEAKER;
        } else {
            return AccountTypeEnum.ATTENDEE;
        }
    }

    public boolean isCorrectPassword(String username, String password) {
        return password.equals(am.getAccountHashMap().get(username).getPassword());
    }
}