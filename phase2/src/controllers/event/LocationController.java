package controllers.event;

import exceptions.already_exists.ObjectAlreadyExistsException;
import exceptions.NonPositiveIntegerException;
import gateway.DataManager;
import use_cases.event.EventManager;

import java.util.ArrayList;

public class LocationController {
    private final EventManager em;
    public LocationController(DataManager dm){
        this.em = dm.getEventManager();
    }
    /**
     * adds a new allowed location where events can take place to the database
     * @param location location to be added
     * @throws NonPositiveIntegerException if occupants or table/chairs are too small
     * @throws ObjectAlreadyExistsException if the room name already exist
     */
    public void addNewLocation(String location) throws NonPositiveIntegerException, ObjectAlreadyExistsException {
        this.em.addNewLocation(location);
    }

    public ArrayList<String> getLocations() {
        return em.getLocations();
    }
}
