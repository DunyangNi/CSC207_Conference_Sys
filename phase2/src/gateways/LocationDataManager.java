package gateways;

import use_cases.event.LocationManager;

import java.io.*;

/**
 * Reads and saves location data and creates LocationManager
 * Fields:
 * locationPath: Path of file storing location data
 */
public class LocationDataManager implements DataReader, DataSaver {
    private final String locationPath;

    /**
     * Empty constructor. Sets path as EventManager
     */
    public LocationDataManager() {
        this("LocationManager");
    }

    /**
     * Non-empty constructor. Sets path.
     * @param locationPath given path
     */
    public LocationDataManager(String locationPath) {
        this.locationPath = locationPath;
    }

    /**
     * From the path, attempt to read and create a LocationManager.
     * Create a new LocationManager if reading does not succeed.
     */
    public LocationManager readManager() {
        try {
            return (LocationManager) readObject(locationPath);
        } catch (IOException e) {
            System.out.println("Could not read LocationManager, creating a new LocationManager.");
            return new LocationManager();
        } catch (ClassNotFoundException e) {
            System.out.println("LocationManager not found, creating a new LocationManager.");
            return new LocationManager();
        }
    }
}
