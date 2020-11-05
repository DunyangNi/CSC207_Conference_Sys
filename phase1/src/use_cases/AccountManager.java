package use_cases;

import java.util.HashMap;
import entities.Account;

public class AccountManager {

    /**
     * AUTHOR: ANDREW
     * The key represents username (never changes) and maps to an account
     */
    private HashMap<String, Account> AccountList;

    /**
     * Constructor; we initially have an empty AccountList until we add stuff
     */

    public AccountManager(){
        this.AccountList = new HashMap<>();
    }

    /**
     * Getter
     */

    public HashMap<String, Account> getAccountlist(){
        return AccountList;
    }

    /**
     * Creates new account and adds to list IF IT WON'T CREATE DUPLICATE USERNAMES. OTHERWISE DO NOTHING
     */

    public void AddNewAccount(String username, String password, String firstName, String lastName){
        if (! (AccountList.containsKey(username))){
            AccountList.put(username, new Account(username,password,firstName,lastName));
        }
    }

    /**
     * Change firstname, lastname, password. CURRENTLY, WE CANNOT CHANGE USERNAME. IF YOU WANT TO CHANGE,
     * THEN EITHER IT SHOULD BE RESTRICTED TO AN ADMIN USER, OR WE WILL NEED TO CREATE ANOTHER STRICT
     * IDENTIFIER SUCH AS ID.
     */

    public void ChangeFirstName(Account ChangeNameAccount, String NewFirstName){
        ChangeNameAccount.setFirstName(NewFirstName);
    }

    public void ChangeLastName(Account ChangeNameAccount, String NewLastName){
        ChangeNameAccount.setLastName(NewLastName);
    }


    public void ChangePassword(Account ChangePasswordAccount, String NewPassword){
        ChangePasswordAccount.setPassword(NewPassword);
    }

    public boolean contains(String username){
        return AccountList.containsKey(username);
    }

}
