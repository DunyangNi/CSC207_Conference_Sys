package gateway;

import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import java.io.*;

/**
 * The gateway class that can save and read data from files
 */

public class DataManager {
    private String accountPath;
    private String eventPath;
    private String conversationPath;

    /**
     * The constructor of DataManager
     * @param accountPath address of account database
     * @param eventPath address of event database
     */
    public DataManager(String accountPath, String eventPath, String conversationPath){
        this.accountPath = accountPath;
        this.eventPath = eventPath;
        this.conversationPath = conversationPath;
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
        buffer.close();
        file.close();
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
            System.out.println("Failed to save the AccountManager.");
        }
    }

    /**
     * (NEW!) Saving the EventManager
     * @param conversationManager The AccountManager we are saving
     */
    public void saveConversationManager(ConversationManager conversationManager) {
        try{
            saveObject(conversationPath, conversationManager);
        } catch (IOException e) {
            System.out.println("Failed to save the ConversationManager.");
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
            buffer.close();
            file.close();
            return am;
        } catch (IOException e) {
            System.out.println("Cannot read the AccountManager, creating a new AccountManager");
            return new AccountManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no AccountManager in database, creating a new AccountManager");
            return new AccountManager();
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
            buffer.close();
            file.close();
            return em;
        } catch (IOException e) {
            System.out.println("Cannot read the EventManager, creating a new EventManager");
            return new EventManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no EventManager in database, creating a new EventManager");
            return new EventManager();
        }
    }

    /**
     * Read the ConversationManager from database.
     * @return The ConversationManager from the file
     */
    public ConversationManager readConversationManager() {
        try{
            InputStream file = new FileInputStream(conversationPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            ConversationManager em = (ConversationManager) input.readObject();
            input.close();
            buffer.close();
            file.close();
            return em;
        } catch (IOException e) {
            System.out.println("Cannot read the ConversationManager, creating a new ConversationManager");
            return new ConversationManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no ConversationManager in database, creating a new ConversationManager");
            return new ConversationManager();
        }
    }
}

