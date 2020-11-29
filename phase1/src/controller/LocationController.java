package controller;

import Throwables.AlreadyExistException;
import Throwables.ConflictException;
import use_cases.EventManager;

public class LocationController {
    private EventManager eventManager;
    public LocationController(EventManager eventManager){
        this.eventManager = eventManager;
    }
    /**
     * adds a new allowed location where events can take place to the database
     * @param location location to be added
     */
    public void addNewLocation(String location) throws AlreadyExistException {

        this.eventManager.addNewLocation(location);

    }
}
