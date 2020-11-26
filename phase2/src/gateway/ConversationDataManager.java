package gateway;

import java.io.*;
import use_cases.*;

public class ConversationDataManager implements DataReader, DataSaver{
    private final String conversationPath;

    public ConversationDataManager() {
        this("ConversationManager");
    }

    public ConversationDataManager(String conversationPath) {
        this.conversationPath = conversationPath;
    }

    public ConversationManager readConversationManager() {
        try{
            return (ConversationManager) readObject(conversationPath);
        } catch (IOException e) {
            System.out.println("Could not read AccountManager, creating a new AccountManager.");
            return new ConversationManager();
        } catch (ClassNotFoundException e) {
            System.out.println("AccountManager not found, creating a new AccountManager.");
            return new ConversationManager();
        }
    }
}
