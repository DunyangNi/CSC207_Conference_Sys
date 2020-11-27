package entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents any talk in the system, where a talk is a type of event.
 */
public class Talk extends Event implements Serializable {
    private String speaker;


    public Talk(Integer id, String topic, Calendar time, String location, String organizer, String speaker, Integer capacity, Boolean vipOnly) {
        super(id, topic, time, location, organizer, capacity, vipOnly);
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
