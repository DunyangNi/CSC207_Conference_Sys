package use_cases.event;

import entities.event.Location;
import exceptions.RequirementMismatchException;
import exceptions.already_exists.LocationAlreadyExistsException;
import exceptions.not_found.LocationNotFoundException;

import java.io.Serializable;
import java.util.*;
import java.util.HashMap;

public class LocationManager implements Serializable {
    private final HashMap<String, Location> locations;

    public LocationManager(HashMap<String, Location> locations) { this.locations = locations; }

    public LocationManager() { this(new HashMap<>()); }

    public void addNewLocation(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) {
        Location newLocation = new Location(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
        this.locations.put(name, newLocation);
    }

    // (NEW!)
    public void checkExistingLocation(String name) throws LocationNotFoundException {
        if (this.locations.get(name) == null) throw new LocationNotFoundException();
    }

    public void checkNewLocation(String name) throws LocationAlreadyExistsException {
        if (this.locations.get(name) != null) throw new LocationAlreadyExistsException();
    }

    private boolean locationMeetsRequirements(Location location, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) {
        return location.getCapacity() >= capacity &&
                location.getTables() >= tables &&
                location.getChairs() >= chairs &&
                (!hasInternet || location.getHasInternet().equals(true)) &&
                (!hasSoundSystem || location.getHasSoundSystem().equals(true)) &&
                (!hasPresentationScreen || location.getHasPresentationScreen().equals(true));
    }

    public void checkLocationMeetsRequirements(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) throws RequirementMismatchException {
        Location selectedLocation = locations.get(name);
        if (!locationMeetsRequirements(selectedLocation, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen)) throw new RequirementMismatchException();
    }

    public ArrayList<String> getSuggestedLocations(int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) {
        ArrayList<String> suggestedLocationStrings = new ArrayList<>();
        for (Location location : locations.values()) {
            if (locationMeetsRequirements(location, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen))
                suggestedLocationStrings.add(location.toString());
        }
        return suggestedLocationStrings;
    }

    public void setLocationCapacity(String name, Integer newCapacity) { locations.get(name).setCapacity(newCapacity); }

    public void setLocationTables(String name, Integer newTables) { locations.get(name).setTables(newTables); }

    public void setLocationChairs(String name, Integer newChairs) { locations.get(name).setChairs(newChairs); }

    public void setLocationHasInternet(String name, Boolean newHasInternet) { locations.get(name).setHasInternet(newHasInternet); }

    public void setLocationHasSoundSystem(String name, Boolean newHasSoundSystem) { locations.get(name).setHasSoundSystem(newHasSoundSystem); }

    public void setLocationHasPresentationScreen(String name, Boolean newHasPresentationScreen) {
        locations.get(name).setHasPresentationScreen(newHasPresentationScreen);
    }

    public void setLocationFurtherNotes(String name, String newFurtherNotes) { locations.get(name).setFurtherNotes(newFurtherNotes); }

    public ArrayList<String> getAllLocationsAsString() {
        ArrayList<String> locationStrings = new ArrayList<>();
        for (Location l : locations.values()) { locationStrings.add(l.toString()); }
        return locationStrings;
    }

}
