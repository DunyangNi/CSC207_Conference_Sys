package entities.event;

import entities.account.VipVisitor;

public interface VipAcceptor {
    boolean accept(VipVisitor v);
}
