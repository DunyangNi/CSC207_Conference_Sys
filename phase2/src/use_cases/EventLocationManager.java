package use_cases;

import exceptions.*;
import entities.Location;
import exceptions.already_exists.LocationAlreadyExistsException;
import exceptions.not_found.LocationNotFoundException;
import exceptions.not_found.ObjectNotFoundException;

import java.io.Serializable;
import java.util.*;

import java.util.HashMap;

public class EventLocationManager implements Serializable {
    private HashMap<Integer, Location> locations = new HashMap<>();
    private int curID = 0; //the id of the next location to be added

    //filters

    public ArrayList<String> getLocationsWithPresentationScreens(ArrayList<String> nameList) throws ObjectNotFoundException {
        try{
            ArrayList<String> filtered = new ArrayList<>();
            for(String name: nameList) {
                Integer id = getIDAtName(name);
                Location location = this.locations.get(id);
                if(location.hasPresentationScreen()) {
                    filtered.add(name);
                }
            }
            return filtered;
        } catch(Exception e) {
            throw new LocationNotFoundException();
        }
    }
    public ArrayList<String> getLocationsWithSoundSystems(ArrayList<String> nameList) throws ObjectNotFoundException{
        try{
            ArrayList<String> filtered = new ArrayList<>();
            for(String name: nameList) {
                Integer id = getIDAtName(name);
                Location location = this.locations.get(id);
                if(location.hasSoundSystem() == true) {
                    filtered.add(name);
                }
            }
            return filtered;
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    public ArrayList<String> getLocationsWithInternet(ArrayList<String> nameList) throws ObjectNotFoundException{
        try{
            ArrayList<String> filtered = new ArrayList<>();
            for(String name: nameList) {
                Integer id = getIDAtName(name);
                Location location = this.locations.get(id);
                if(location.hasInternet() == true) {
                    filtered.add(name);
                }
            }
            return filtered;
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    //returns location names with numchairs >= i
    public ArrayList<String> filterByNumChairs(int i, ArrayList<String> nameList) throws ObjectNotFoundException {
        try{
            ArrayList<String> filtered = new ArrayList<>();
            for(String name: nameList) {
                Integer id = getIDAtName(name);
                Location location = this.locations.get(id);
                if(location.numChairs() >= i) {
                    filtered.add(name);
                }
            }
            return filtered;
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }

    }

    //returns location names with numtables >= i
    public ArrayList<String> filterByNumTables(int i, ArrayList<String> nameList) throws ObjectNotFoundException {
        try{
            ArrayList<String> filtered = new ArrayList<>();
            for(String name: nameList) {
                Integer id = getIDAtName(name);
                Location location = this.locations.get(id);
                if(location.numTables() >= i) {
                    filtered.add(name);
                }
            }
            return filtered;
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }

    }

    //returns location names with max occupancy >= i
    public ArrayList<String> filterByMaxOccupancy(int i, ArrayList<String> nameList) throws ObjectNotFoundException {
        try{
            ArrayList<String> filtered = new ArrayList<>();
            for(String name: nameList) {
                Integer id = getIDAtName(name);
                Location location = this.locations.get(id);
                if(location.getMaxOccupancy() >= i) {
                    filtered.add(name);
                }
            }
            return filtered;
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }

    }

    public void updateMaxOccupancy(int id, int status) throws NonPositiveIntegerException, LocationNotFoundException {
        if(status < 0) {
            throw new NonPositiveIntegerException();
        }
        else{
            try{
                this.locations.get(id).setMaxOccupancy(status);
            } catch(Exception e) {
                throw new LocationNotFoundException();
            }

        }
    }

    public void updateNumTables(int id, int status) throws LocationNotFoundException, NonPositiveIntegerException {
        if(status < 0) {
            throw new NonPositiveIntegerException();
        }
        else{
            try{
                this.locations.get(id).setNumTables(status);
            } catch(Exception e) {
                throw new LocationNotFoundException();
            }

        }
    }

    public void updateNumChairs(int id, int status) throws LocationNotFoundException, NonPositiveIntegerException {
        if(status < 0) {
            throw new NonPositiveIntegerException();
        }
        else{
            try{
                this.locations.get(id).setNumChairs(status);
            } catch(Exception e) {
                throw new LocationNotFoundException();
            }

        }
    }

    public void updateHasInternet(int id, boolean status) throws ObjectNotFoundException{
        try{
            this.locations.get(id).setHasInternet(status);
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    public void updateHasSoundSystem(int id, boolean status) throws ObjectNotFoundException{
        try{
            this.locations.get(id).setHasSoundSystem(status);
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    public void updateHasPresentationSystem(int id, boolean status) throws ObjectNotFoundException{
        try{
            this.locations.get(id).setHasPresentationScreen(status);
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    public void updateFurtherNotes(int id, String notes) throws ObjectNotFoundException{
        try{
            this.locations.get(id).setFurtherNotes(notes);
        } catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    public void removeLocation(Integer id) {
        this.locations.remove(id);
    }

    public void addNewLocation(String name, int maxOccupancy, int numTables, int numChairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) throws NonPositiveIntegerException, LocationAlreadyExistsException {
        if(maxOccupancy < 0){
            throw new NonPositiveIntegerException();
        }
        else if(numTables < 0){
            throw new NonPositiveIntegerException();
        }
        else if(numChairs < 0){
            throw new NonPositiveIntegerException();
        }
        else{
            for(Location location: this.locations.values()){
                if(location.getName().equals(name)){
                    throw new LocationAlreadyExistsException();
                }
            }
        }

        int id = this.curID;
        Location newLocation = new Location(name, id, maxOccupancy, numTables, numChairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
        this.locations.put(id, newLocation);
        this.curID += 1;
    }
    public String getNameAtID(int id) throws ObjectNotFoundException {
        try{
            return this.locations.get(id).getName();
        } catch(Exception e){
            throw new ObjectNotFoundException("Location");
        }
    }

    public Integer getIDAtName(String locationName) throws ObjectNotFoundException {
        for(Integer i: this.locations.keySet()) {
            if(this.locations.get(i).getName().equals(locationName)) {
                return new Integer(i);
            }
        }
        throw new ObjectNotFoundException("Location");
    }
    public Location getLocationAtID(int id) throws ObjectNotFoundException{
        try{
            return this.locations.get(id);
        }
        catch(Exception e){
            throw new ObjectNotFoundException("Invalid id");
        }
    }

    public ArrayList<Integer> getIDList(){
        return new ArrayList<>(this.locations.keySet());
    }

    public ArrayList<String> getNameList(){
        ArrayList<String> nameList = new ArrayList<>();
        for(Integer i: this.locations.keySet()) {
            nameList.add(this.locations.get(i).getName());
        }
        return nameList;
    }

    public String getLocationStringDescription(int id) throws ObjectNotFoundException{
        try{
            return this.locations.get(id).toString();
        }
        catch(Exception e){
            throw new ObjectNotFoundException("Invalid id");
        }
    }
    // returns [name,id,maxOccupancy,numTables,numChairs,hasInternet,hasSoundSystem,hasPresentationScreen,furtherNotes]
    public String[] getLocationCompactDescription(int id) throws ObjectNotFoundException{
        try{
            String[] desc = new String[9];
            Location location = this.locations.get(id);
            desc[0] = location.getName();
            desc[1] = location.getId().toString();
            desc[2] = location.getMaxOccupancy().toString();
            desc[3] = location.numTables().toString();
            desc[4] = location.numChairs().toString();
            desc[5] = location.hasInternet().toString();
            desc[6] = location.hasSoundSystem().toString();
            desc[7] = location.hasPresentationScreen().toString();
            desc[8] = location.furtherNotes();
            return desc;
        }
        catch(Exception e) {
            throw new ObjectNotFoundException("Location");
        }
    }

    public ArrayList<String> getLocations() {
        ArrayList<String> allLocations = new ArrayList<>();
        for (Location l : locations.values()) {
            allLocations.add(l.getName());
        }
        return allLocations;
    }

}
