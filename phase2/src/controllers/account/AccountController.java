package controllers.account;

import enums.AccountTypeEnum;
import enums.ViewEnum;
import gateways.DataManager;
import use_cases.account.AccountManager;
import views.View;
import views.ViewFactory;

import java.util.Set;

public class AccountController {
    private final DataManager dm;
    private final AccountManager am;
    private final String username;

    public AccountController(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.username = dm.getUsername();
    }

    public AccountTypeEnum getAccountType() {
        if (am.containsAttendee(username)) {
            return AccountTypeEnum.ATTENDEE;
        } else if (am.containsSpeaker(username)) {
            return AccountTypeEnum.SPEAKER;
        } else {
            return AccountTypeEnum.ORGANIZER;
        }
    }

    public boolean usernameExists(String username) {
        return am.containsAccount(username);
    }

    public Set<String> getAccountList() {
        return dm.getAccountManager().getAccountHashMap().keySet();
    }

    public View getView(ViewEnum viewEnum) { // TODO find more appropriate place. ATM, Controller is interacting with View
        ViewFactory viewFactory = new ViewFactory(dm);
        return viewFactory.getView(viewEnum);
    }

    public void saveData() {
        dm.saveData();
    }
}
