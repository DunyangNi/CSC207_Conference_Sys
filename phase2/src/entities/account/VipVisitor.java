package entities.account;

/**
 * An interface for evaluating <code>VIP Attendees</code>.
 */
public interface VipVisitor {
    boolean visit(Attendee a);
    boolean visit(VipAttendee v);
}
