package entities.account;

/**
 * Represents a <code>VIP Attendee</code> in the system.
 */
public class VipAttendee extends Attendee {
    /**
     * Constructs an instance of <code>VIP Attendee</code> based on Strings of information
     *
     * @param username given username
     * @param password given password
     */
    public VipAttendee(String username, String password) {
        super(username, password);
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
