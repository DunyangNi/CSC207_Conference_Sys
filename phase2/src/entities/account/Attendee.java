package entities.account;

import java.util.ArrayList;

/**
 * Represents a <code>Attendee Account</code> in the system.
 */
public class Attendee extends Account {
    private final ArrayList<Integer> eventsAttending = new ArrayList<>();

    /**
     * Constructs an instance of <code>Attendee</code> based on Strings of information
     *
     * @param username given username
     * @param password given password
     * @param firstName given first name
     * @param lastName given last name
     */
    public Attendee(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    /**
     * @return IDs of events this <code>Attendee</code> is attending
     */
    public ArrayList<Integer> getEventsAttending() { return eventsAttending; }

}
