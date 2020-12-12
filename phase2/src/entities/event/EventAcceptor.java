package entities.event;

import java.util.ArrayList;

/**
 * An interface for evaluating <code>Events</code> by their type.
 */
public interface EventAcceptor {
    ArrayList<String> acceptSpeakers(EventVisitor e);
    String acceptType(EventVisitor e);
}
