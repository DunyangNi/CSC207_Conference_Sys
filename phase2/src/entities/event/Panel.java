package entities.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/*
    Represents any Panel Discussion in the system, where a Panel discussion
    is a type of event with multiple speakers.
 */
public class Panel extends Event implements Serializable, EventAcceptor {
    private ArrayList<String> speakers;

    public Panel(
            Integer id, String topic, Calendar time, String location,
            String organizer, ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen,
            Boolean vipOnly)
    {
        super(id, topic, time, location, organizer, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
        setSpeakers(speakers);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Panel</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Panel</code>.
     *
     * @param other other <code>Object</code> presumed <code>Panel</code> to compare
     * @return the given <code>Object</code> matches this <code>Panel</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Panel) {
            Panel o = (Panel)other;

            if (speakers.size() == o.getSpeakers().size()) {
                ArrayList<String> speakersClone1 = new ArrayList<>(getSpeakers());
                ArrayList<String> speakersClone2 = new ArrayList<>(o.getSpeakers());
                Collections.sort(speakersClone1);
                Collections.sort(speakersClone2);
                return super.equals(other) && speakersClone1.equals(speakersClone2);
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Event ID: " + getId() + "\n" +
                "Event Type: Panel Discussion\n" +
                "VIPs Only: " + getVipOnly() + "\n" +
                "Topic: " + getTopic() + "\n" +
                "Speakers: " + String.join(", ", getSpeakers()) + "\n" +
                "Location: " + getLocation() + "\n" +
                "Time: " + getTime().getTime().toString() + "\n" +
                "Organizer: " + getOrganizer();
    }

    /**
     * @return speakers of this <code>Panel</code>
     */
    public ArrayList<String> getSpeakers() {
        return speakers;
    }

    /**
     * Sets new speakers for this <code>Panel</code>.
     *
     * @param speakers the username of speakers for this <code>Panel</code>
     */
    public void setSpeakers(ArrayList<String> speakers) { this.speakers = speakers; }

    /**
     * Append a new speaker for this <code>Panel</code>.
     *
     * @param speaker the username of speaker to append for this <code>Panel</code>
     */
    public void addSpeaker(String speaker) {
        if (!speakers.contains(speaker))
            this.speakers.add(speaker);
    }

    /**
     * Removes a speaker
     * @param speaker a speaker to remove
     */
    public void removeSpeaker(String speaker) { speakers.remove(speaker); }

    @Override
    public ArrayList<String> acceptSpeakers(EventVisitor e) {
        return e.visitSpeakers(this);
    }

    @Override
    public String acceptType(EventVisitor e) {
        return e.visitType(this);
    }
}
