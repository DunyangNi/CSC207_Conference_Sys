package entities.event;

import java.util.ArrayList;
import java.util.Collections;

public class EventHelper implements EventVisitor {
    @Override
    public ArrayList<String> visitSpeakers(Networking networking) {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> visitSpeakers(Talk talk) {
        return new ArrayList<>(Collections.singletonList(talk.getSpeaker()));
    }

    @Override
    public ArrayList<String> visitSpeakers(Panel panel) {
        return panel.getSpeakers();
    }

    @Override
    public ArrayList<String> visitSpeakers(Event event) {
        return new ArrayList<>();
    }

    @Override
    public String visitType(Networking n) {
        return "Networking Event";
    }

    @Override
    public String visitType(Talk t) {
        return "Talk";
    }

    @Override
    public String visitType(Panel p) {
        return "Panel Discussion";
    }

    @Override
    public String visitType(Event event) {
        return "General Event";
    }
}
