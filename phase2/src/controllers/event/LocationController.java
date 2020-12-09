package controllers.event;

import exceptions.already_exists.LocationAlreadyExistsException;
import gateways.DataManager;
import use_cases.event.LocationManager;

import java.util.ArrayList;

public class LocationController {
    private final LocationManager locationManager;
    public LocationController(DataManager dm){
        this.locationManager = dm.getLocationManager();
    }

    public boolean isNewLocation(String name) {
        try { locationManager.checkNewLocation(name); }
        catch (LocationAlreadyExistsException e) { return false; }
        return true;
    }
    
    public void addNewLocation(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) {
        this.locationManager.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
    }

    public ArrayList<String> getLocationsAsString() {
        return locationManager.getAllLocationsAsString();
    }
}
