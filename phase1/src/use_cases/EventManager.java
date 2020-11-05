package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import entities.EventTalk;
import entities.Event;
import entities.Account;
import entities.Organizer;
import entities.Speaker;

public class EventManager implements Serializable {
    private ArrayList<Event> eventlist;
    private ArrayList<EventTalk> talklist;

    private ArrayList<String> locationlist;

    public EventManager(ArrayList<Event> eventlist, ArrayList<EventTalk> talklist, ArrayList<String> locations){
        this.eventlist = eventlist;
        this.talklist = talklist;
        this.locationlist = locations;
    }

    public void addLocation(String location) {
        this.locationlist.add(location);
    }





    /**
     * GetEndTime:
     * Given an event start time, generate a new Calendar representing
     * the end time. WE ASSUME THAT EVENTS LAST ONE HOUR ACCORDING TO SPECIFICATIONS;
     * FOR COMPARISON PURPOSES WE SAY THAT IT ENDS 59 MINUTES AFTER START
     */

    public Calendar GetEndTime(Calendar eventtime){
        Calendar end_time = (Calendar) eventtime.clone();
        end_time.add(Calendar.MINUTE, +59);
        return end_time;
    }

    /**
     * CheckTimeOverlap:
     * Check of two hypothetical events have time conflict
     */

    public boolean CheckTimeOverlap(Calendar time_1, Calendar time_2){
        Calendar endtime_1 = GetEndTime(time_1);
        Calendar endtime_2 = GetEndTime(time_2);
        return !(time_1.after(endtime_2) || endtime_1.before(time_2));
    }
    /*
     * NOTE: THESE METHODS WILL CHECK IF TIME CONFLICTS ARE CREATED:
     * THEY WILL REJECT (AND GIVE SIGNAL OF FAILURE) WHEN CONFLICTS
     * WOULD BE CREATED
     * Overloading is used; two versions of AddNewEvent
     */

    /**
     * AddNewEvent: Checks for same location be used in overlapping time
     */

    public boolean AddNewEvent(String topic, Calendar time, String location, Account organizer){
        for(Event event: eventlist){
            if (event.getLocation().equals(location) && CheckTimeOverlap(time, event.getTime())){
                return false;
            }
            if(event.getTopic().equals(topic)) {
                return false;
            }
        }
        eventlist.add(new Event(topic, time, location, organizer));
        if(!this.locationlist.contains(location)) {
            locationlist.add(location);
        }
        return true;
    }

    /**
     * AddNewEvent: Checks for same location or same speaker be used in overlapping time
     * SINCE THIS IS AN EVENTTALK IT IS ADDED TO BOTH LISTS
     */

    public boolean AddNewEvent(String topic, Calendar time, String location, Account organizer, Account speaker){
        for(Event event: eventlist) {
            if (event.getLocation().equals(location) && CheckTimeOverlap(time, event.getTime())) {
                return false;
            }
            if(event.getTopic().equals(topic)) {
                return false;
            }
        }

        for(EventTalk talk: talklist) {
            if (talk.getSpeaker().equals(speaker) && CheckTimeOverlap(time, talk.getTime())) {
                return false;
            }
            if(talk.getTopic().equals(topic)) {
                return false;
            }
        }

        eventlist.add(new EventTalk(topic, time, location, organizer, speaker));
        talklist.add(new EventTalk(topic, time, location, organizer, speaker));
        if(!this.locationlist.contains(location)) {
            locationlist.add(location);
        }
        return true;
    }

    public void ChangeTopic(Event event_to_change, String new_topic){
        event_to_change.setTopic(new_topic);
    }

    /**
     * ChangeTime: Checks for conlicts due to same location be used in overlapping time
     */

    public boolean ChangeTime(Event event_to_change, Calendar new_time){
        Calendar curr_time = Calendar.getInstance();
        if(curr_time.compareTo(event_to_change.getTime()) >= 0) {
            return false;
        }
        if(curr_time.compareTo(new_time) >= 0) {
            return false;
        }
        for(Event event: eventlist) {
            String location1 = event.getLocation();
            String location2 = event_to_change.getLocation();
            Calendar time1 = event.getTime();

            if (event != event_to_change && location1.equals(location2) && CheckTimeOverlap(time1, new_time)) {
                return false;
            }
        }
        event_to_change.setTime(new_time);
        return true;
    }

    /**
     * ChangeTime: Checks for conflicts due to same location be used in overlapping time
     */

    public boolean ChangeLocation(Event event_to_change, String new_location) {
        for(Event event: eventlist) {
            String location1 = event.getLocation();
            Calendar time1 = event.getTime();
            Calendar time2 = event_to_change.getTime();

            if (event != event_to_change && location1.equals(new_location) && CheckTimeOverlap(time1, time2)) {
                return false;
            }
        }
        event_to_change.setLocation(new_location);
        if(!this.locationlist.contains(new_location)) {
            locationlist.add(new_location);
        }
        return true;
    }

    public void ChangeOrganizer(Event event_to_change, Organizer new_organizer){
        event_to_change.setOrganizer(new_organizer);
    }

    /**
     * ChangeTime: Checks for conflicts due to same speaker being used in overlapping time
     */

    public boolean ChangeSpeaker(EventTalk talk_to_change, Speaker new_speaker){
        for(EventTalk talk: talklist) {
            Calendar time1 = talk.getTime();
            Calendar time2 = talk_to_change.getTime();

            if (talk != talk_to_change && time1.equals(time2) && talk.getSpeaker().equals(talk_to_change.getSpeaker())){
                return false;
            }
        }
        talk_to_change.setSpeaker(new_speaker);
        return true;
    }

    public EventTalk fetchTalk(String topic) {
        for(EventTalk talk: this.talklist) {
            if(talk.getTopic().equals(topic)) {
                return talk;
            }
        }
        throw new RuntimeException();
    }

    public void cancelTalk(EventTalk talk) {
        Calendar time = Calendar.getInstance();
        if(time.compareTo(talk.getTime()) >= 0) {
            return;
        }

        for(Event event: this.eventlist) {
            if(event.getTopic().equals(talk.getTopic())) {
                this.eventlist.remove(event);
                break;
            }
        }
        for(EventTalk Talk: this.talklist) {
            if(Talk.getTopic().equals((talk.getTopic()))){
                this.talklist.remove(Talk);
                break;
            }
        }
    }
}
