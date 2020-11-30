package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/*
    Represents any Panel Discussion in the system, where a Panel discussion
    is a type of event with multiple speakers.
 */
public class PanelDiscussion extends Event implements Serializable{
    private ArrayList<String> speakers;

    public PanelDiscussion(
            Integer id, String topic, Calendar time, String location,
            String organizer, ArrayList<String> speakers, Integer capacity,
            Boolean vipOnly)
    {
        super(id, topic, time, location, organizer, capacity, vipOnly);
        setSpeakers(speakers);
    }

    /**
     * Compares a given <code>Object</code> with this <code>PanelDiscussion</code>. Returns
     * true iff the given <code>Object</code> matches this <code>PanelDiscussion</code>.
     *
     * @param other other <code>Object</code> presumed <code>PanelDiscussion</code> to compare
     * @return the given <code>Object</code> matches this <code>PanelDiscussion</code>
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof PanelDiscussion) {
            PanelDiscussion o = (PanelDiscussion)other;

            if ( speakers.size() == o.getSpeakers().size()) {
                ArrayList<String> speakersClone1 = (ArrayList<String>)getSpeakers().clone();
                ArrayList<String> speakersClone2 = (ArrayList<String>)o.getSpeakers().clone();

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

    /**
     * @return speakers of this <code>PanelDiscussion</code>
     */
    public ArrayList<String> getSpeakers() {
        return speakers;
    }

    /**
     * Sets new speakers for this <code>PanelDiscussion</code>.
     *
     * @param speakers the username of speakers for this <code>PanelDiscussion</code>
     */
    public void setSpeakers(ArrayList<String> speakers) {
        if (speakers == null){
            this.speakers = new ArrayList<String>();
        }
        else {
            this.speakers = (ArrayList<String>) speakers.clone();
        }
    }

    /**
     * Append a new speaker for this <code>PanelDiscussion</code>.
     *
     * @param speaker the username of speaker to append for this <code>PanelDiscussion</code>
     */
    public void appendSpeaker(String speaker) {
        if (speakers == null){
            this.speakers = new ArrayList<String>();
            this.speakers.add(speaker);
        }
        else{
            if (!speakers.contains(speaker)){
                this.speakers.add(speaker);
            }
            else{ }// already exists. Nothing to do
        }
    }

    /**
     * Removes a speaker
     * @param speaker a speaker to remove
     */
    public void removeSpeaker(String speaker) {
        if (speakers != null && speakers.contains(speaker)){
            speakers.remove(speaker);
        }
    }

    /*

    // TEST
    public static void main(String args[]){
        Calendar c = Calendar.getInstance();
        PanelDiscussion p1 = new PanelDiscussion(
        1, "t1", c, "l1", "o1", null, 3, false);
        PanelDiscussion p2 = new PanelDiscussion(
        1, "t1", c, "l1", "o1", null, 3, false);

        p1.appendSpeaker("a3");
        p1.appendSpeaker("a2");
        p1.appendSpeaker("a1");

        p2.appendSpeaker("a2");
        p2.appendSpeaker("a3");
        p2.appendSpeaker("a1");

        System.out.println(p1.equals(p2));
        System.out.println("p1: " + p1.getSpeakers());
        System.out.println("p2: " + p2.getSpeakers());

        p1.appendSpeaker("a3");
        p1.appendSpeaker("a4");
        System.out.println("p1: " + p1.getSpeakers());

        p1.removeSpeaker("a3");
        p1.removeSpeaker("a9");
        System.out.println("p1: " + p1.getSpeakers());
    }
    */
}
