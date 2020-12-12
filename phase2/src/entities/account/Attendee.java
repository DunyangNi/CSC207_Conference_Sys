package entities.account;

import java.util.ArrayList;

/**
 * Represents a <code>Attendee Account</code> in the system.
 */
public class Attendee extends Account implements VipAcceptor {
    private final ArrayList<Integer> eventsAttending = new ArrayList<>();

    /**
     * Constructs an instance of <code>Attendee</code> based on Strings of information.
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
    public ArrayList<Integer> getEventsAttending() {
        return eventsAttending;
    }

    /**
     * Returns true iff this <code>Attendee</code> is a <code>VIP Attendee</code>.
     *
     * @param v given <code>VIP Visitor</code>
     * @return this <code>Attendee</code> is a <code>VIP Attendee</code>
     */
    @Override
    public boolean accept(VipVisitor v) {
        return v.visit(this);
    }
}
