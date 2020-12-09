package gateways;

import java.io.*;

import use_cases.account.ContactManager;

public class ContactDataManager implements DataReader, DataSaver {
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
            System.out.println("Could not read ContactManager, creating a new ContactManager.");
            return new ContactManager();
        } catch (ClassNotFoundException e) {
            System.out.println("ContactManager not found, creating a new ContactManager.");
            return new ContactManager();
        }
    }
}
