package controllers.event;

import exceptions.NoSuggestedLocationsException;
import exceptions.RequirementMismatchException;
import exceptions.already_exists.LocationAlreadyExistsException;
import exceptions.not_found.LocationNotFoundException;
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

    public boolean isExistingLocation(String name) {
        try { locationManager.checkExistingLocation(name); }
        catch (LocationNotFoundException e) { return false; }
        return true;
    }
    
    public void addNewLocation(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) {
        this.locationManager.addNewLocation(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
    }

    public boolean locationMeetsRequirements(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) {
        try { locationManager.checkLocationMeetsRequirements(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen); }
        catch (RequirementMismatchException e) { return false; }
        return true;
    }

    public ArrayList<String> getSuggestedLocations(int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) throws NoSuggestedLocationsException {
        ArrayList<String> suggestedLocations = locationManager.getSuggestedLocations(capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen);
        if (suggestedLocations.isEmpty()) throw new NoSuggestedLocationsException();
        return suggestedLocations;
    }

    public ArrayList<String> getLocationsAsString() {
        return locationManager.getAllLocationsAsString();
    }
}
