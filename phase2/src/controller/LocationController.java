package controller;

import exceptions.ObjectAlreadyExistsException;
import exceptions.IntegerOutOfBoundsException;
import use_cases.EventManager;

public class LocationController {
    private EventManager eventManager;
    public LocationController(EventManager eventManager){
        this.eventManager = eventManager;
    }
    /**
     * adds a new allowed location where events can take place to the database
     * @param location location to be added
     * @throws IntegerOutOfBoundsException if occupants or table/chairs are too small
     * @throws ObjectAlreadyExistsException if the room name already exist
     */
    public void addNewLocation(String location) throws IntegerOutOfBoundsException, ObjectAlreadyExistsException {

        this.eventManager.addNewLocation(location);

    }
}