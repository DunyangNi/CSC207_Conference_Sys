package controllers.account;

import enums.AccountTypeEnum;
import enums.ViewEnum;
import gateways.DataManager;
import use_cases.account.AccountManager;
import views.start.View;
import views.start.ViewFactory;

import java.util.Set;

/**
 * A contoller class responsible for getting and dealing with Account types and lists
 *
 * Fields:
 * DataManager: Datamanager class which stores information of accounts and an accountmanager
 * AccountManager: AccountManager class which stores accounts
 * username: username of the user using the program
 */
public class AccountController {
    private final DataManager dm;
    private final AccountManager am;
    private final String username;

    /**
     * Creates an instance of <code>AccountController</code> with given parameters..
     *
     * @param dm desired Datamanager
     */
    public AccountController(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.username = dm.getUsername();
    }

    /**
     * @return enum representing the account type of the account with the username
     */
    public AccountTypeEnum getAccountType() {
        if (am.containsAttendee(username)) {
            if (am.isVipAttendee(username)) {
                return AccountTypeEnum.VIP_ATTENDEE;
            } else {
                return AccountTypeEnum.ATTENDEE;
            }
        } else if (am.containsSpeaker(username)) {
            return AccountTypeEnum.SPEAKER;
        } else {
            return AccountTypeEnum.ORGANIZER;
        }
    }

    /**
     * @return true iff the AccountManager contains an account with the given username
     * @param username desired username
     */
    public boolean usernameExists(String username) {
        return am.containsAccount(username);
    }

    /**
     * @return list of accounts in the AccountManager
     */
    public Set<String> getAccountList() {
        return dm.getAccountManager().getAccountHashMap().keySet();
    }

    /**
     * @return a view based on the view enum given
     * @param viewEnum the given view enum
     */
    public View getView(ViewEnum viewEnum) { // TODO find more appropriate place. ATM, Controller is interacting with View
        ViewFactory viewFactory = new ViewFactory(dm);
        return viewFactory.getView(viewEnum);
    }

    /**
     * Lets the datamanager save its current data.
     */
    public void saveData() {
        dm.saveData();
    }
}
