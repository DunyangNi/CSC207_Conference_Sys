package entities.account;

public class VipHelper implements VipVisitor {
    @Override
    public boolean visit(Attendee a) { return false; }

    @Override
    public boolean visit(VipAttendee v) { return true; }
}
