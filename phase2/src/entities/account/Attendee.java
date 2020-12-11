package entities.account;

import entities.event.VipAcceptor;

import java.util.ArrayList;

/**
 * Represents a <code>Attendee Account</code> in the system.
 */
public class Attendee extends Account implements VipAcceptor {
    private final ArrayList<Integer> eventsAttending = new ArrayList<>();

    /**
     * Constructs an instance of <code>Attendee</code> based on Strings of information
     *
     * @param username given username
     * @param password given password
     */
    public Attendee(String username, String password) {
        super(username, password);
    }

    /**
     * @return IDs of events this <code>Attendee</code> is attending
     */
    public ArrayList<Integer> getEventsAttending() { return eventsAttending; }

    @Override
    public boolean accept(VipVisitor v) { return v.visit(this); }
}
