package entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents any talk in the system, where a talk is a type of event.
 */
public class Talk extends Event implements Serializable {
    private String speaker;

    /**
     * Creates an instance of <code>Talk</code> with an assigned integer ID and given information.
     *
     * @param id assigned ID
     * @param topic given topic
     * @param time given time
     * @param locationID given location id
     * @param organizer given <code>Organizer</code> username
     * @param speaker given <code>Speaker</code> username
     */
    public Talk(Integer id, String topic, Calendar time, int locationID, String organizer, String speaker) {
        super(id, topic, time, locationID, organizer);
        this.speaker = speaker;
    }

    /**
     * Compares a given <code>Object</code> with this <code>Talk</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Talk</code>.
     *
     * @param other other <code>Object</code> presumed <code>Talk</code> to compare
     * @return the given <code>Object</code> matches this <code>Talk</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Talk) {
            Talk o = (Talk)other;
            return super.equals(other) && getSpeaker().equals(o.getSpeaker());
        }
        return false;
    }

    /**
     * @return speaker of this <code>Talk</code>
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Sets a new speaker for this <code>Talk</code>.
     *
     * @param speaker the username of new speaker for this <code>Talk</code>
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
