package entities;

import java.util.ArrayList;

public class Organizer extends Account {

    private ArrayList<Integer> organizerEvents = new ArrayList<>();

    public Organizer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public ArrayList<Integer> getOrganizerEvents() {
        return organizerEvents;
    }

    public void setOrganizerEvents(ArrayList<Integer> organizerEvents) {
        this.organizerEvents = organizerEvents;
    }
}
