package gateways;

import use_cases.event.LocationManager;

import java.io.*;

public class LocationDataManager implements DataReader, DataSaver {
    private final String locationPath;

    public LocationDataManager() {
        this("LocationManager");
    }

    public LocationDataManager(String locationPath) {
        this.locationPath = locationPath;
    }

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
