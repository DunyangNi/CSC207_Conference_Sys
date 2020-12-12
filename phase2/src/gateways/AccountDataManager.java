package gateways;

import java.io.*;

import use_cases.account.AccountManager;

/**
 * Reads and saves account data and creates AccountDataManager
 * Fields:
 * accountPath: Path of file storing account data
 */
public class AccountDataManager implements DataReader, DataSaver{
    private final String accountPath;

    /**
     * Empty constructor. Sets path as AccountManager
     */
    public AccountDataManager() {
        this("AccountManager");
    }

    /**
     * Non-empty constructor. Sets path.
     * @param accountPath given path
     */
    public AccountDataManager(String accountPath) {
        this.accountPath = accountPath;
    }

    /**
     * From the path, attempt to read and create an AccountManager.
     * Create a new AccountManager if reading does not succeed.
     */
    public AccountManager readManager() {
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
