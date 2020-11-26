package use_cases;

import Throwables.ConflictException;
import Throwables.IntegerOutOfBoundsException;
import Throwables.ObjectNotFoundException;
import entities.Location;
import java.util.*;

import java.util.HashMap;

public class LocationManager {
    private HashMap<Integer, Location> locations = new HashMap<>();
    private int curID = 0; //the id of the next location to be added


    public void removeLocation(Integer id) {
        this.locations.remove(id);
    }

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


}
