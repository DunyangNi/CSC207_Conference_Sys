package entities;

import java.util.ArrayList;

public class Organizer extends Account {

    private ArrayList<Event> organizerEvents;

    public Organizer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    // TODO: 11/07/20 Ask what this is for
    public boolean isAttendee(){return false;}
    
    public boolean isOrganizer(){return true;}
    
    
    
    
    
    public ArrayList<Event> getOrganizerEvents() {
        return organizerEvents;
    }

    public void setOrganizerEvents(ArrayList<Event> organizerEvents) {
        this.organizerEvents = organizerEvents;
    }
    
}
