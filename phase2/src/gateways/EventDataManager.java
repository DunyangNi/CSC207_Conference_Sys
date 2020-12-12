package gateways;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import use_cases.event.LocationManager;
import use_cases.event.EventManager;

/**
 * Reads and saves event data and creates EventManager
 * Fields:
 * eventPath: Path of file storing event data
 */
public class EventDataManager implements DataReader, DataSaver{
    private final String eventPath;

    /**
     * Empty constructor. Sets path as EventManager
     */
    public EventDataManager() {
        this("EventManager");
    }

    /**
     * Non-empty constructor. Sets path.
     * @param eventPath given path
     */
    public EventDataManager(String eventPath) {
        this.eventPath = eventPath;
    }

    /**
     * From the path, attempt to read and create an EventManager.
     * Create a new EventManager if reading does not succeed.
     */
    public EventManager readManager() {
        try{
            return (EventManager) readObject(eventPath);
        } catch (IOException e) {
            System.out.println("Could not read EventManager, creating a new EventManager.");
            return new EventManager(new HashMap<>());
        } catch (ClassNotFoundException e) {
            System.out.println("EventManager not found, creating a new EventManager.");
            return new EventManager(new HashMap<>());
        }
    }
}
