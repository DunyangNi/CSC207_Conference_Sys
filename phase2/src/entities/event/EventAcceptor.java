package entities.event;

import java.util.ArrayList;

public interface EventAcceptor {
    ArrayList<String> acceptSpeakers(EventVisitor e);
    String acceptType(EventVisitor e);
}
