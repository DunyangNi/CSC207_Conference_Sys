package controllers.account;

import enums.AccountTypeEnum;
import gateways.DataManager;
import use_cases.account.AccountManager;

public class LoginController {
    private final AccountManager am;

    /**
     * Manages login functionality for the program
     *
     * @param am stores data of all accounts
     * @param fm manages friendlist functionality
     * @param cm manages conversation/messaging functionality
     * @param em manages event data
     */
    public LoginController(DataManager dm) {
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

    public boolean usernameExists(String username) {
        return am.containsAccount(username);
    }

    public boolean isCorrectPassword(String username, String password) {
        return password.equals(am.getAccountHashMap().get(username).getPassword());
    }
}