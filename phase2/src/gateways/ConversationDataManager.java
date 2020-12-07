package gateways;

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