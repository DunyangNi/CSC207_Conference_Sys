package gateway;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import use_cases.*;

public class EventDataManager implements DataReader, DataSaver{
    private final String eventPath;

    public EventDataManager() {
        this("EventManager");
    }

    public EventDataManager(String eventPath) {
        this.eventPath = eventPath;
    }

    public EventManager readManager() {
        try{
            return (EventManager) readObject(eventPath);
        } catch (IOException e) {
            System.out.println("Could not read EventManager, creating a new EventManager.");
            return new EventManager(new HashMap<>(), new ArrayList<>(), new EventLocationManager());
        } catch (ClassNotFoundException e) {
            System.out.println("EventManager not found, creating a new EventManager.");
            return new EventManager(new HashMap<>(), new ArrayList<>(), new EventLocationManager());
        }
    }
}
