package entities;

import java.io.Serializable;

public class Attendee extends Account implements Serializable {

    public Attendee(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    // TODO: 11/07/20 Ask what this is for
    public boolean isAttendee(){return true;}

    public boolean isOrganizer(){return false;}
}
