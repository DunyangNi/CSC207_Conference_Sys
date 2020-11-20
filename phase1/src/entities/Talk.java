package entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a talk at a conference. A talk is a kind of event.
 *
 * Fields:
 * speaker: the username of the speaker who is giving the talk. A talk must
 * only have one speaker
 *
 * Superclass: Event
 */
public class Talk extends Event implements Serializable {
    private String speaker;

    //------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------

    /**
     * Create an Talk with topic, time, speaker, and organizer.
     * @param topic topic for the talk
     * @param time time for the talk
     * @param speaker speaker for the talk
     * @param organizer organizer for the talk
     */
    public Talk(Integer id, String topic, Calendar time, String location, String organizer, String speaker) {
        super(id, topic, time, location, organizer);
        this.speaker = speaker;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Compares for equality with another object.
     *
     * @param other other object to compare
     * @return True if other is a Talk and has the same Event and speaker are matched.
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Talk){
            Talk o = (Talk)other;
            return super.equals(other) &&
                    getSpeaker().equals(o.getSpeaker());
        }
        return false;
    }

    //------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------

    /**
     * @return speaker
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Sets a new speaker for the talk.
     *
     * @param speaker the intended new speaker for the talk
     */

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
