package gateway;

import java.io.*;
import use_cases.*;

public class EventDataManager implements DataReader, DataSaver{
    private final String eventPath;

    public EventDataManager() {
        this("EventManager");
    }

    public EventDataManager(String eventPath) {
        this.eventPath = eventPath;
    }

    public EventManager readConversationManager() {
        try{
            return (EventManager) readObject(eventPath);
        } catch (IOException e) {
            System.out.println("Could not read AccountManager, creating a new AccountManager.");
            return new EventManager();
        } catch (ClassNotFoundException e) {
            System.out.println("AccountManager not found, creating a new AccountManager.");
            return new EventManager();
        }
    }
}
