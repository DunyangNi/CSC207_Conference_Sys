package use_cases;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import entities.*;

import java.util.Arrays;

public class EventManager implements Serializable {
    private ArrayList<Event> eventlist;
    private ArrayList<EventTalk> talklist;
    private ArrayList<String> locationlist;

    // (NEW!)
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof EventManager) {
            boolean sameEventList = eventlist.equals(((EventManager) obj).getEventlist());
            boolean sameTalkList = talklist.equals(((EventManager) obj).getTalklist());
            boolean sameLocationList = locationlist.equals(((EventManager) obj).getLocationlist());
            result = sameEventList && sameTalkList && sameLocationList;
        }
        return result;
    }

    // (NEW!)
    public ArrayList<Event> getEventlist() { return eventlist; }
    public ArrayList<EventTalk> getTalklist() { return talklist; }
    public ArrayList<String> getLocationlist() { return locationlist; }

    // (NEW!)
    public EventManager() {
        this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public EventManager(ArrayList<Event> eventlist, ArrayList<EventTalk> talklist, ArrayList<String> locations){
        this.eventlist = eventlist;
        this.talklist = talklist;
        this.locationlist = locations;
    }

    public void addLocation(String location) {
        if(!this.locationlist.contains(location)) {
            this.locationlist.add(location);
        }
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
        if(!this.locationlist.contains(location)) {
            return false;
        }
        for(Event event: eventlist){
            if (event.getLocation().equals(location) && CheckTimeOverlap(time, event.getTime())){
                return false;
            }
            if(event.getTopic().equals(topic)) {
                return false;
            }
        }
        eventlist.add(new Event(topic, time, location, organizer));
        return true;
    }

    /**
     * AddNewEvent: Checks for same location or same speaker be used in overlapping time
     * SINCE THIS IS AN EVENTTALK IT IS ADDED TO BOTH LISTS
     */

    public boolean AddNewEvent(String topic, Calendar time, String location, Account organizer, Account speaker){
        if(!this.locationlist.contains(location)) {
            return false;
        }
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
        if(!this.locationlist.contains(new_location)) {
            return false;
        }
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

    public ArrayList<String> fetchLocations() {
        return this.locationlist;
    }
    public EventTalk fetchTalk(String topic, Calendar time) {
        for(EventTalk talk: this.talklist) {
            if(talk.getTopic().equals(topic) && talk.getTime().compareTo(time) == 0) {
                return talk;
            }
        }
        throw new RuntimeException();
    }

    public Event fetchEvent(String topic, Calendar time) {
        for(Event event: this.eventlist) {
            if(event.getTopic().equals(topic) && event.getTime().compareTo(time) == 0) {
                return event;
            }
        }
        throw new RuntimeException();
    }

    public void cancelTalk(EventTalk talk) {
        for(Event event: this.eventlist) {
            if(event.getTopic().equals(talk.getTopic()) && event.getTime().compareTo(talk.getTime()) == 0) {
                this.eventlist.remove(event);
                break;
            }
        }
        for(EventTalk Talk: this.talklist) {
            if(Talk.getTopic().equals((talk.getTopic())) && Talk.getTime().compareTo(talk.getTime()) == 0){
                this.talklist.remove(Talk);
                break;
            }
        }
    }

    public EventTalk[] fetchSortedTalkList(ArrayList<EventTalk> talklist) {
        //talk list sorted by time in increasing order
        EventTalk[] eventarray = new EventTalk[talklist.size()];
        for(int i = 0; i<= talklist.size() - 1; i++) {
            eventarray[i] = talklist.get(i);
        }
        Arrays.sort(eventarray);
        return eventarray;
    }

    public Calendar[] fetchTimeSortedTalkTimes(ArrayList<EventTalk> talklist) {
        EventTalk[] eventarray = this.fetchSortedTalkList(talklist);
        Calendar[] sortedtimes = new Calendar[eventarray.length];
        for(int i = 0; i<= eventarray.length - 1; i++) {
            sortedtimes[i] = eventarray[i].getTime();
        }
        return sortedtimes;
    }

    public String[] fetchTimeSortedTalkTopics(ArrayList<EventTalk> talklist) {
        EventTalk[] eventarray = this.fetchSortedTalkList(talklist);
        String[] sortedtopics = new String[eventarray.length];
        for(int i = 0; i<= eventarray.length - 1; i++) {
            sortedtopics[i] = eventarray[i].getTopic();
        }
        return sortedtopics;
    }
    public String[] fetchTimeSortedTalkSpeakers(ArrayList<EventTalk> talklist) {
        EventTalk[] eventarray = this.fetchSortedTalkList(talklist);
        String[] sortedspeakernames = new String[eventarray.length];
        for(int i = 0; i<= eventarray.length - 1; i++) {
            sortedspeakernames[i] = eventarray[i].getSpeaker().getFirstName() + " " + eventarray[i].getSpeaker().getLastName();
        }
        return sortedspeakernames;
    }

    public String[] fetchTimeSortedLocations(ArrayList<EventTalk> talklist) {
        EventTalk[] eventarray = this.fetchSortedTalkList(talklist);
        String[] sortedlocations = new String[eventarray.length];
        for(int i = 0; i<= eventarray.length - 1; i++) {
            sortedlocations[i] = eventarray[i].getLocation();
        }
        return sortedlocations;
    }

    public boolean containsEvent(String topic, Calendar time) {
        for(Event event: this.eventlist) {
            if(event.getTopic().equals(topic) && event.getTime().compareTo(time) == 0) {
                return true;
            }
        }
        return false;
    }
    public boolean containsTalk(String topic, Calendar time) {
        for(EventTalk talk: this.talklist) {
            if(talk.getTopic().equals(topic) && talk.getTime().compareTo(time) == 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<EventTalk> fetchTalkList() {
        return this.talklist;
    }

    public ArrayList<String> getAttendeesAtEvent(String topic, Calendar time) {
        Event event = this.fetchEvent(topic, time);
        ArrayList<String> attendeeusernames = new ArrayList<>();
        ArrayList<Attendee> attendees = event.getAttendees();

        for(int i = 0; i<= attendees.size() - 1; i++) {
            attendeeusernames.add(attendees.get(i).getUsername());
        }
        return attendeeusernames;
    }


}
