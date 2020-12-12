package controllers.event;

import exceptions.already_exists.LocationAlreadyExistsException;
import gateways.DataManager;
import use_cases.event.LocationManager;

import java.util.ArrayList;

/**
 * A controller responsible for adding and managing locations
 */
public class LocationController {
    private final LocationManager locationManager;

    /**
     * Creates an instance of <code>LocationController </code> with given parameters.
     *
     * @param dm Datamanager containing all needed managers.
     */
    public LocationController(DataManager dm) {
        this.locationManager = dm.getLocationManager();
    }

    /**
     * @param name desired name to check
     * @return true iff there is already a location called name
     */
    public boolean isNewLocation(String name) {
        try {
            locationManager.checkNewLocation(name);
        } catch (LocationAlreadyExistsException e) {
            return false;
        }
        return true;
    }

    /**
     * Add a location based on the information given.
     *
     * @param name                  name to add
     * @param capacity              capacity to add
     * @param tables                table count to add
     * @param chairs                chair count to add
     * @param hasInternet           internet existence to add
     * @param hasSoundSystem        sound system existence to add
     * @param hasPresentationScreen presentation screen existence to add
     * @param furtherNotes          further notes to add
     */
    public void addNewLocation(String name, int capacity, int tables, int chairs, boolean hasInternet,
                               boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) {
        this.locationManager.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem,
                hasPresentationScreen, furtherNotes);
    }

    /**
     * @return a list of names of all locations store in the LocationManager
     */
    public ArrayList<String> getLocationsAsString() {
        return locationManager.getAllLocationsAsString();
    }
}
