package entities.event;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Helper class to evaluate <code>Events</code> by type.
 */
public class EventHelper implements EventVisitor {
    /**
     * Returns an empty <code>ArrayList</code> of speaker usernames, since a <code>Networking</code> event
     * does not assign speakers.
     *
     * @param networking given <code>Networking</code> event
     * @return empty <code>ArrayList</code> of speakers
     */
    @Override
    public ArrayList<String> visitSpeakers(Networking networking) {
        return new ArrayList<>();
    }

    /**
     * Returns an <code>ArrayList</code> with only the speaker username from this <code>Talk</code>.
     *
     * @param talk given <code>Talk</code>
     * @return <code>ArrayList</code> with only the speaker from this <code>Talk</code>
     */
    @Override
    public ArrayList<String> visitSpeakers(Talk talk) {
        return new ArrayList<>(Collections.singletonList(talk.getSpeaker()));
    }

    /**
     * Returns the <code>ArrayList</code> of speaker usernames of this <code>Panel</code>.
     *
     * @param panel given <code>PanelDiscussion</code>
     * @return <code>ArrayList</code> of speaker usernames of this <code>Panel</code>
     */
    @Override
    public ArrayList<String> visitSpeakers(Panel panel) {
        return panel.getSpeakers();
    }

    /**
     * Returns an empty <code>ArrayList</code> of speaker usernames, since a <code>Event</code>
     * does not assign speakers.
     *
     * @param event given <code>Event</code>
     * @return empty <code>ArrayList</code> of speakers
     */
    @Override
    public ArrayList<String> visitSpeakers(Event event) {
        return new ArrayList<>();
    }

    /**
     * Returns <code>Networking</code> as a String representation.
     *
     * @param networking given <code>Networking</code> event.
     * @return <code>Networking</code> as a String representation
     */
    @Override
    public String visitType(Networking networking) {
        return "Networking Event";
    }

    /**
     * Returns <code>Talk</code> as a String representation.
     *
     * @param talk given <code>Talk</code>
     * @return <code>Talk</code> as a String representation
     */
    @Override
    public String visitType(Talk talk) {
        return "Talk";
    }

    /**
     * Returns <code>Panel</code> as a String representation.
     *
     * @param panel given <code>Panel</code>
     * @return <code>Panel</code> as a String representation
     */
    @Override
    public String visitType(Panel panel) {
        return "Panel Discussion";
    }

    /**
     * Returns <code>Event</code> as a String representation.
     *
     * @param event given <code>Event</code>
     * @return <code>Event</code> as a String representation
     */
    @Override
    public String visitType(Event event) {
        return "General Event";
    }
}
