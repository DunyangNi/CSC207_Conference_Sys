package controllers.account;

import gateways.DataManager;
import use_cases.account.AccountManager;

import java.util.Set;

public class AccountController {
    private final AccountManager am;

    public AccountController(DataManager dm) {
        this.am = dm.getAccountManager();
    }

    public Set<String> getAllAccounts() {
        return am.getAccountHashMap().keySet();
    }
}
