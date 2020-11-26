package gateway;

import java.io.*;
import use_cases.*;

public class AccountDataManager implements DataReader, DataSaver{
    private final String accountPath;

    public AccountDataManager() {
        this("AccountManager");
    }

    public AccountDataManager(String accountPath) {
        this.accountPath = accountPath;
    }

    public AccountManager readAccountManager() {
        try{
            return (AccountManager) readObject(accountPath);
        } catch (IOException e) {
            System.out.println("Could not read AccountManager, creating a new AccountManager.");
            return new AccountManager();
        } catch (ClassNotFoundException e) {
            System.out.println("AccountManager not found, creating a new AccountManager.");
            return new AccountManager();
        }
    }
}
