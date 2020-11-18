package gateway;

import use_cases.*;

import java.io.*;
import java.sql.SQLOutput;

/**
 * The gateway class that can save and read data from files
 */

public class DataManager {
    private final String accountPath;
    private final String eventPath;
    private final String conversationPath;
    private final String friendPath;
    private final String signupPath;

    /**
     * The constructor of DataManager
     */
    public DataManager() {
        this("AccountManager", "FriendManager", "ConversationManager", "EventManager", "SignupManager");
    }

    /**
     * The constructor of DataManager
     *
     * @param accountPath address of account database
     * @param eventPath   address of event database
     */
    public DataManager(String accountPath, String friendPath, String conversationPath, String eventPath, String signupPath) {
        this.accountPath = accountPath;
        this.eventPath = eventPath;
        this.conversationPath = conversationPath;
        this.friendPath = friendPath;
        this.signupPath = signupPath;
    }

    /**
     * The helper function that saves a particular Serializable.
     * @param filePath The address of the database
     * @param ser      The Serializable we are saving
     * @throws IOException Throw exception when failed to save.
     */
    private void saveSerializable(String filePath, Serializable ser) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(ser);
        output.close();
        buffer.close();
        file.close();
    }

    public void saveManager(String managerName, String filePath, Serializable manager) {
        try {
            saveSerializable(filePath, manager);
            System.out.println("Saved " + managerName);
        } catch (IOException e) {
            System.out.printf("Failed to save the %s.%n", managerName);
        }
    }

    /**
     * Read the AccountManager from database.
     *
     * @return The AccountManager from the file
     */
    public AccountManager readAccountManager() {
        try{
            return (AccountManager) readManager(accountPath);
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
     *
     * @return The EventManager from the file
     */
    public EventManager readEventManager() {
        try{
            return (EventManager) readManager(eventPath);
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
     *
     * @return The ConversationManager from the file
     */
    public ConversationManager readConversationManager() {
        try{
            InputStream file = new FileInputStream(conversationPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            ConversationManager cm = (ConversationManager) input.readObject();
            input.close();
            buffer.close();
            file.close();
            return cm;
        } catch (IOException e) {
            System.out.println("Cannot read the ConversationManager, creating a new ConversationManager");
            return new ConversationManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no ConversationManager in database, creating a new ConversationManager");
            return new ConversationManager();
        }
    }

    /**
     * Read the FriendManager from database.
     *
     * @return The FriendManager from the file
     */
    public FriendManager readFriendManager() {
        try {
            return (FriendManager) readManager(friendPath);
        } catch (IOException e) {
            System.out.println("Cannot read the FriendManager, creating a new FriendManager");
            return new FriendManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no FriendManager in database, creating a new FriendManager");
            return new FriendManager();
        }
    }

    /**
     * Read the SignupManager from database.
     *
     * @return The SignupManager from the file
     */
    public SignupManager readSignupManager() {
        try {
//            InputStream file = new FileInputStream(signupPath);
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//            SignupManager sm = (SignupManager) input.readObject();
//            input.close();
//            buffer.close();
//            file.close();
            System.out.println("Loaded " + signupPath);
            return (SignupManager) readManager(signupPath);
        } catch (IOException e) {
            System.out.println("Cannot read the SignupManager, creating a new SignupManager");
            return new SignupManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no SignupManager in database, creating a new SignupManager");
            return new SignupManager();
        }
    }

    private Object readManager(String path) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(path);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        Object ob = input.readObject();
        input.close();
        buffer.close();
        file.close();
        return ob;
    }
}

