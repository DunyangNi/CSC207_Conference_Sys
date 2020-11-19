package gateway;

import use_cases.*;
import java.io.*;

/**
 * Manages reading and writing data stored in each Manager
 * through serializable (.ser) files.
 */
public class DataManager {
    private final String accountPath;
    private final String eventPath;
    private final String conversationPath;
    private final String friendPath;
    private final String signupPath;

    /**
     * Creates a <code>DataManager</code> with default file paths to each Manager's .ser file.
     */
    public DataManager() {
        this("AccountManager", "FriendManager", "ConversationManager", "EventManager", "SignupManager");
    }

    /**
     * Creates a <code>DataManager</code> with specified file paths for each Manager's .ser file.
     *
     * @param accountPath given path to the AccountManager .ser file
     * @param friendPath given path to the FriendManager .ser file
     * @param conversationPath given path to the ConversationManager .ser file
     * @param eventPath given path to the EventManager .ser file
     * @param signupPath given path to the SignupManager .ser file
     */
    public DataManager(String accountPath, String friendPath, String conversationPath, String eventPath, String signupPath) {
        this.accountPath = accountPath;
        this.eventPath = eventPath;
        this.conversationPath = conversationPath;
        this.friendPath = friendPath;
        this.signupPath = signupPath;
    }

    /**
     * Attempts to save a <code>Serializable</code> object to a .ser file in a specified file path.
     *
     * An output stream is opened and the <code>Serializable</code> object is written to
     * the file path specified if the file does not exist, or overwrites to the path
     * otherwise. The output stream is immediately closed afterwards.
     *
     * @param filePath given path to the .ser file
     * @param ser the <code>Serializable</code> object
     * @throws IOException upon an I/O related exception
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

    /**
     * Saves a <code>Serializable</code> Manager object to a .ser file in a specified file path.
     *
     * Helper method <code>saveSerializable()</code> is used here. Output of success or failure
     * will be sent to the console.
     *
     * @param managerName name of manager to save
     * @param filePath given file path for the .ser file
     * @param manager a <code>Serializable</code> manager object
     */
    public void saveManager(String managerName, String filePath, Serializable manager) {
        try {
            saveSerializable(filePath, manager);
            System.out.println("Saved " + managerName);
        } catch (IOException e) {
            System.out.printf("Failed to save the %s.%n", managerName);
        }
    }

    /**
     * Attempts to read and return a <code>Object</code> stored in a .ser file.
     *
     * @param path given path to .ser file associated with <code>Object</code>
     * @return the <code>Object</code> stored in the .ser file
     * @throws IOException upon an I/O related exception
     * @throws ClassNotFoundException upon no <code>Object</code> being recognized
     */
    private Object readObject(String path) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(path);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        Object ob = input.readObject();
        input.close();
        buffer.close();
        file.close();
        return ob;
    }

    /**
     * Reads and returns the <code>AccountManager</code> from the file path specified
     * for its .ser file as an object. Otherwise, returns a new empty <code>AccountManager</code>.
     *
     * @return an <code>AccountManager</code> object
     */
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

    /**
     * Reads and returns the <code>EventManager</code> from the file path specified
     * for its .ser file as an object. Otherwise, returns a new empty <code>EventManager</code>.
     *
     * @return an <code>EventManager</code> object
     */
    public EventManager readEventManager() {
        try{
            return (EventManager) readObject(eventPath);
        } catch (IOException e) {
            System.out.println("Could not read EventManager, creating a new EventManager.");
            return new EventManager();
        } catch (ClassNotFoundException e) {
            System.out.println("EventManager not found, creating a new EventManager.");
            return new EventManager();
        }
    }

    /**
     * Reads and returns the <code>ConversationManager</code> from the file path specified
     * for its .ser file as an object. Otherwise, returns a new empty <code>ConversationManager</code>.
     *
     * @return an <code>ConversationManager</code> object
     */
    public ConversationManager readConversationManager() {
        try{
            return (ConversationManager) readObject(conversationPath);
        } catch (IOException e) {
            System.out.println("Could not read ConversationManager, creating a new ConversationManager.");
            return new ConversationManager();
        } catch (ClassNotFoundException e) {
            System.out.println("ConversationManager not found, creating a new ConversationManager.");
            return new ConversationManager();
        }
    }

    /**
     * Reads and returns the <code>FriendManager</code> from the file path specified
     * for its .ser file as an object. Otherwise, returns a new empty <code>FriendManager</code>.
     *
     * @return an <code>FriendManager</code> object
     */
    public FriendManager readFriendManager() {
        try {
            return (FriendManager) readObject(friendPath);
        } catch (IOException e) {
            System.out.println("Could not read FriendManager, creating a new FriendManager.");
            return new FriendManager();
        } catch (ClassNotFoundException e) {
            System.out.println("FriendManager not found, creating a new FriendManager.");
            return new FriendManager();
        }
    }

    /**
     * Reads and returns the <code>SignupManager</code> from the file path specified
     * for its .ser file as an object. Otherwise, returns a new empty <code>SignupManager</code>.
     *
     * @return an <code>SignupManager</code> object
     */
    public SignupManager readSignupManager() {
        try {
            return (SignupManager) readObject(signupPath);
        } catch (IOException e) {
            System.out.println("Could not read SignupManager, creating a new SignupManager.");
            return new SignupManager();
        } catch (ClassNotFoundException e) {
            System.out.println("SignupManager not found, creating a new SignupManager.");
            return new SignupManager();
        }
    }
}

