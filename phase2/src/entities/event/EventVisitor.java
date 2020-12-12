package entities.event;

import java.util.ArrayList;

/**
 * An interface for evaluating <code>Events</code> by their type.
 */
public interface EventVisitor {
    ArrayList<String> visitSpeakers(Networking n);
    ArrayList<String> visitSpeakers(Talk t);
    ArrayList<String> visitSpeakers(Panel p);
    ArrayList<String> visitSpeakers(Event e);
    String visitType(Networking n);
    String visitType(Talk t);
    String visitType(Panel p);
    String visitType(Event e);
}
