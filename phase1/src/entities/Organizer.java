package entities;

import java.util.ArrayList;

public class Organizer extends Account {

    private ArrayList<Integer> organizerEvents = new ArrayList<>();

    public Organizer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    // TODO: 11/07/20 Ask what this is for
    public boolean isAttendee(){return false;}
    
    public boolean isOrganizer(){return true;}
    
    
    
    
    
    public ArrayList<Integer> getOrganizerEvents() {
        return organizerEvents;
    }

    public void setOrganizerEvents(ArrayList<Integer> organizerEvents) {
        this.organizerEvents = organizerEvents;
    }
    
}
