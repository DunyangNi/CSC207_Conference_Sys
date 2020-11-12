package entities;

import java.util.ArrayList;

public class Speaker extends Account {

    private ArrayList<Integer> speakerTalks = new ArrayList<Integer>();

    public Speaker(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }





    public ArrayList<Integer> getSpeakerTalks() {
        return speakerTalks;
    }

    public void setSpeakerTalks(ArrayList<Integer> speakerTalks) {
        this.speakerTalks = speakerTalks;
    }
}
