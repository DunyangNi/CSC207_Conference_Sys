package gateways;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import use_cases.event.LocationManager;
import use_cases.event.EventManager;

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
            return new EventManager(new HashMap<>());
        } catch (ClassNotFoundException e) {
            System.out.println("EventManager not found, creating a new EventManager.");
            return new EventManager(new HashMap<>());
        }
    }
}
