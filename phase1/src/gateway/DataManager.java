package gateway;

import entities.*;
import use_cases.AccountManager;
import use_cases.EventManager;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * The gateway class that can save and read data from files
 */

public class DataManager {
    private String accountPath;
    private String eventPath;

    /**
     * The constructor of DataManager
     * @param accountPath address of account database
     * @param eventPath address of event database
     */
    public DataManager(String accountPath, String eventPath){
        this.accountPath = accountPath;
        this.eventPath = eventPath;
    }

    /**
     * The helper function that can save any type of objects.
     * @param filePath The address of the database
     * @param object The object we are saving
     * @throws IOException Throw exception when failed to save.
     */
    private void saveObject(String filePath, Object object) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(object);
        output.close();
    }

    /**
     * Saving the EventManager
     * @param eventManager The EventManager we are saving
     */
    public void saveEventManager(EventManager eventManager) {
        try{
            saveObject(eventPath, eventManager);
        } catch (IOException e) {
            System.out.println("Failed to save the EventManager.");
        }
    }

    /**
     * Saving the EventManager
     * @param accountManager The AccountManager we are saving
     */
    public void saveAccountManager(AccountManager accountManager) {
        try{
            saveObject(accountPath, accountManager);
        } catch (IOException e) {
            System.out.println("Failed to save the EventManager.");
        }
    }

    /**
     * Read the AccountManager from database.
     * @return The AccountManager from the file
     */
    public AccountManager readAccountManager() {
        try{
            InputStream file = new FileInputStream(accountPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            AccountManager am = (AccountManager) input.readObject();
            input.close();
            return am;
        } catch (IOException e) {
            System.out.println("Cannot read the AccountManager, creating a new AccountManager");
            return new AccountManager(new HashMap<String, Attendee>(), new HashMap<String, Organizer>(),
                    new HashMap<String, Speaker>());
        } catch (ClassNotFoundException e) {
            System.out.println("There is no AccountManager in database, creating a new AccountManager");
            return new AccountManager(new HashMap<String, Attendee>(), new HashMap<String, Organizer>(),
                    new HashMap<String, Speaker>());
        }
    }

    /**
     * Read the EventManager from database.
     * @return The EventManager from the file
     */
    public EventManager readEventManager() {
        try{
            InputStream file = new FileInputStream(eventPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            EventManager em = (EventManager) input.readObject();
            input.close();
            return em;
        } catch (IOException e) {
            System.out.println("Cannot read the EventManager, creating a new AccountManager");
            return new EventManager(new ArrayList<Event>(), new ArrayList<EventTalk>(), new ArrayList<String>());
        } catch (ClassNotFoundException e) {
            System.out.println("There is no EventManager in database, creating a new AccountManager");
            return new EventManager(new ArrayList<Event>(), new ArrayList<EventTalk>(), new ArrayList<String>());
        }
    }

    public static void main(String[] args){
        DataManager dm = new DataManager("AccountDatabase.ser", "EventDatabase.ser");
        EventManager em = dm.readEventManager();
        AccountManager am = dm.readAccountManager();
        Organizer o = new Organizer("abc", "123456", "a", "bc");
        em.AddNewEvent("abc", Calendar.getInstance(),"room 1", o);
        am.AddNewOrganizer("abc", "123456", "a", "bc");
        am.AddNewAttendee("firstuser", "123456", "first", "user");
        dm.saveAccountManager(am);
        dm.saveEventManager(em);
    }


}

