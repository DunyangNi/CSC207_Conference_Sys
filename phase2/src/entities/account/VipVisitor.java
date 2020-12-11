package entities.account;

public interface VipVisitor {
    boolean visit(Attendee a);
    boolean visit(VipAttendee v);
}
