package entities;

import java.util.ArrayList;

public class Speaker extends Account {

    private ArrayList<EventTalk> speakerTalks;

    public Speaker(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }





    public ArrayList<EventTalk> getSpeakerTalks() {
        return speakerTalks;
    }

    public void setSpeakerTalks(ArrayList<EventTalk> speakerTalks) {
        this.speakerTalks = speakerTalks;
    }
}
