package gateway;

import use_cases.*;
import java.io.*;

/**
 * The gateway class that can save and read data from files
 */

public class DataManager {
    private String accountPath;
    private String eventPath;
    private String conversationPath;
    private String friendPath;
    private String signupPath;

    /**
     * The constructor of DataManager
     * @param accountPath address of account database
     * @param eventPath address of event database
     */
    public DataManager(String accountPath, String friendPath, String conversationPath, String eventPath, String signupPath){
        this.accountPath = accountPath;
        this.eventPath = eventPath;
        this.conversationPath = conversationPath;
        this.friendPath = friendPath;
        this.signupPath = signupPath;
    }

    /**
     * The helper function that saves a particular Serializable.
     * @param filePath The address of the database
     * @param ser The Serializable we are saving
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
        try { saveSerializable(filePath, manager); }
        catch (IOException e) { System.out.printf("Failed to save the %s.%n", managerName); }
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
     * @return The FriendManager from the file
     */
    public FriendManager readFriendManager() {
        try {
            InputStream file = new FileInputStream(friendPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            FriendManager fm = (FriendManager) input.readObject();
            input.close();
            buffer.close();
            file.close();
            return fm;
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
     * @return The SignupManager from the file
     */
    public SignupManager readSignupManager() {
        try {
            InputStream file = new FileInputStream(signupPath);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            SignupManager fm = (SignupManager) input.readObject();
            input.close();
            buffer.close();
            file.close();
            return fm;
        } catch (IOException e) {
            System.out.println("Cannot read the SignupManager, creating a new SignupManager");
            return new SignupManager();
        } catch (ClassNotFoundException e) {
            System.out.println("There is no SignupManager in database, creating a new SignupManager");
            return new SignupManager();
        }
    }
}

