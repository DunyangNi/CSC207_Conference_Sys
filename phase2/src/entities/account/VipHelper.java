package entities.account;

/**
 * Helper class to evaluate if an <code>Attendee</code> is a <code>VIP Attendee</code>.
 */
public class VipHelper implements VipVisitor {
    /**
     * Returns false upon evaluating <code>Attendee</code> as <code>VIP Attendee</code>.
     *
     * @param a given <code>Attendee</code>
     * @return false
     */
    @Override
    public boolean visit(Attendee a) {
        return false;
    }

    /**
     * Returns true upon evaluating <code>VIP Attendee</code> as <code>VIP Attendee</code>.
     *
     * @param v given <code>VIP Attendee</code>
     * @return true
     */
    @Override
    public boolean visit(VipAttendee v) {
        return true;
    }
}
