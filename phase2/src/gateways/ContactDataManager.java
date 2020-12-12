package gateways;

import java.io.*;

import use_cases.message.ContactManager;

/**
 * Reads and saves contact data and creates ContactManager
 * Fields:
 * contactPath: Path of file storing contact data
 */
public class ContactDataManager implements DataReader, DataSaver {
    private final String contactPath;

    /**
     * Empty constructor. Sets path as ContactManager
     */
    public ContactDataManager() {
        this("ContactManager");
    }

    /**
     * Non-empty constructor. Sets path.
     * @param contactPath given path
     */
    public ContactDataManager(String contactPath) {
        this.contactPath = contactPath;
    }

    /**
     * From the path, attempt to read and create a ContactManager.
     * Create a new ContactManager if reading does not succeed.
     */
    public ContactManager readManager() {
        try{
            return (ContactManager) readObject(contactPath);
        } catch (IOException e) {
            System.out.println("Could not read ContactManager, creating a new ContactManager.");
            return new ContactManager();
        } catch (ClassNotFoundException e) {
            System.out.println("ContactManager not found, creating a new ContactManager.");
            return new ContactManager();
        }
    }
}
