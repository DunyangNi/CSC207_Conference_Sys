package entities.account;

/**
 * An interface for evaluating <code>VIP Attendees</code>.
 */
public interface VipAcceptor {
    boolean accept(VipVisitor v);
}
