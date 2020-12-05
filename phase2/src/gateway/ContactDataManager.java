package gateway;

import java.io.*;
import use_cases.*;

public class ContactDataManager implements DataReader, DataSaver{
    private final String friendPath;

    public ContactDataManager() {
        this("ContactManager");
    }

    public ContactDataManager(String friendPath) {
        this.friendPath = friendPath;
    }

    public ContactManager readManager() {
        try{
            return (ContactManager) readObject(friendPath);
        } catch (IOException e) {
            System.out.println("Could not read AccountManager, creating a new AccountManager.");
            return new ContactManager();
        } catch (ClassNotFoundException e) {
            System.out.println("AccountManager not found, creating a new AccountManager.");
            return new ContactManager();
        }
    }
}
