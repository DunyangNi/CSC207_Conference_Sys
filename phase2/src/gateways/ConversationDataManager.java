package gateways;

import java.io.*;

import use_cases.message.ConversationManager;

/**
 * Reads and saves conversation data and creates ConversationManager
 * Fields:
 * conversationPath: Path of file storing conversation data
 */
public class ConversationDataManager implements DataReader, DataSaver{
    private final String conversationPath;

    /**
     * Empty constructor. Sets path as ConversationManager
     */
    public ConversationDataManager() {
        this("ConversationManager");
    }

    /**
     * Non-empty constructor. Sets path.
     * @param conversationPath given path
     */
    public ConversationDataManager(String conversationPath) {
        this.conversationPath = conversationPath;
    }

    /**
     * From the path, attempt to read and create a ConversationManager.
     * Create a new ConversationManager if reading does not succeed.
     */
    public ConversationManager readManager() {
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
}
