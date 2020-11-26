package use_cases;

import Throwables.ConflictException;
import Throwables.IntegerOutOfBoundsException;
import Throwables.ObjectNotFoundException;
import entities.Location;
import java.util.*;

import java.util.HashMap;

public class LocationManager {
    private HashMap<Integer, Location> locations;
    private int curID = 0; //the id of the next location to be added

    public void addNewLocation(String name, int maxOccupancy, int numTables, int numChairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, String furtherNotes) throws ConflictException,IntegerOutOfBoundsException{
        if(maxOccupancy < 0){
            throw new IntegerOutOfBoundsException("Too few occupants");
        }
        else if(numTables < 0){
            throw new IntegerOutOfBoundsException("Too few tables");
        }
        else if(numChairs < 0){
            throw new IntegerOutOfBoundsException("Too few tables");
        }
        else{
            for(Location location: this.locations.values()){
                if(location.getName().equals(name)){
                    throw new ConflictException("Location with this name already exists");
                }
            }
        }

        int id = this.curID;
        Location newLocation = new Location(name, id, maxOccupancy, numTables, numChairs, hasInternet, hasSoundSystem, hasPresentationScreen, furtherNotes);
        this.locations.put(id, newLocation);
        this.curID += 1;
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
        ArrayList<Integer> idList = new ArrayList<>();
        for(Integer i: this.locations.keySet()) {
            idList.add(i);
        }
        return idList;
    }

    public String getLocationDescription(int id) throws ObjectNotFoundException{
        try{
            return this.locations.get(id).toString();
        }
        catch(Exception e){
            throw new ObjectNotFoundException("Invalid id");
        }
    }


}
