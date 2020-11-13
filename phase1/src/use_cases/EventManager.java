package use_cases;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import entities.*;

import java.util.Arrays;
import java.util.HashMap;

public class EventManager implements Serializable {
    private HashMap<Integer, Event> events;
    private ArrayList<String> locations;

    // (NEW!)
    //@Override
    //public boolean equals(Object obj) {
    //    boolean result = false;
    //    if (obj instanceof EventManager) {
    //        boolean sameEventList = eventlist.equals(((EventManager) obj).getEventlist());
    //        boolean sameTalkList = talklist.equals(((EventManager) obj).getTalklist());
    //        boolean samelocations = locations.equals(((EventManager) obj).getlocations());
    //        result = sameEventList && sameTalkList && samelocations;
    //    }
    //    return result;
    //}

    // (NEW!)
    //public ArrayList<Event> getEventlist() { return eventlist; }
    //public ArrayList<EventTalk> getTalklist() { return talklist; }
    //public ArrayList<String> getlocations() { return locations; }

    // (NEW!)
    public EventManager() {
        this(new HashMap<>(), new ArrayList<>());
    }

    public EventManager(HashMap<Integer, Event> events, ArrayList<String> locations){
        this.events = events;
        this.locations = locations;
    }

    public boolean addLocation(String location) {
        if (!this.locations.contains(location)) {
            this.locations.add(location);
            return true;
        }
        return false;
    }

    /**
     * CheckTimeOverlap:
     * Check of two hypothetical events have time conflict
     * WE ASSUME THAT EVENTS LAST ONE HOUR ACCORDING TO SPECIFICATIONS AND THAT THEY START
     * AT START OF HOUR
     */
    public boolean CheckTimeOverlap(Calendar time_1, Calendar time_2){
        return time_1.compareTo(time_2) == 0;
    }

    public ArrayList<EventTalk> getAllTalks() {
        ArrayList<EventTalk> talks = new ArrayList<>();
        for (Event e : getAllEvents()) {
            if (e instanceof EventTalk) { talks.add((EventTalk) e); }
        }
        return talks;
    }

    public ArrayList<Event> getAllEvents() { return new ArrayList<>(events.values()); }

    // (NEW!) possible merge with fetchEvent
    public EventTalk getTalk(Integer id) {
        Event selectedTalk = events.get(id);
        return selectedTalk instanceof EventTalk ? (EventTalk) selectedTalk : null;
    }

    /*
     * NOTE: THESE METHODS WILL CHECK IF TIME CONFLICTS ARE CREATED:
     * THEY WILL REJECT (AND GIVE SIGNAL OF FAILURE) WHEN CONFLICTS
     * WOULD BE CREATED
     * Overloading is used; two versions of AddNewEvent() and validEvent() (helper)
     */

    /**
     * (NEW!) (Helper) Returns true iff EventTalk is valid: no conflicting time or existing events and talks.
     * @param topic given topic
     * @param time given time
     * @param location given location
     * @param speaker given speaker
     * @return true iff EventTalk is valid: no conflicting time or existing events and talks.
     */
    public boolean validEvent(String topic, Calendar time, String location, String speaker) {
        // call general helper
        if (validEvent(topic, time, location)) {
            // check talks
            for(EventTalk talk: getAllTalks()) {
                if (talk.getSpeaker().equals(speaker) && CheckTimeOverlap(time, talk.getTime())) { return false; }
                if (talk.getTopic().equals(topic)) { return false; }
                // possible extension (double booking speaker in two locations)
            }
            return true;
        }
        return false;
    }

    /**
     * (NEW!) (Helper) Returns true iff Event is valid: no conflicting time or existing events.
     * @param topic given topic
     * @param time given time
     * @param location given location
     * @return true iff Event is valid: no conflicting time or existing events.
     */
    public boolean validEvent(String topic, Calendar time, String location) {
        // check if location is valid
        if (!this.locations.contains(location)) { return false; }
        // check if any conflicting events or events already existing
        for(Event event: getAllEvents()) {
            if (event.getLocation().equals(location) && CheckTimeOverlap(time, event.getTime())){ return false; }
            if (event.getTopic().equals(topic)) { return false; }
        }
        return true;
    }

    /**
     * AddNewEvent: Checks for same location be used in overlapping time
     */
    public boolean AddNewEvent(String topic, Calendar time, String location, String organizer){
        if (validEvent(topic, time, location)) {
            Event eventToAdd = new Event(topic, time, location, organizer);
            events.put(eventToAdd.getId(), eventToAdd);
            return true;
        }
        return false;
    }

    /**
     * AddNewEvent: Checks for same location or same speaker be used in overlapping time
     * SINCE THIS IS AN EVENTTALK IT IS ADDED TO BOTH LISTS
     * (NEW!) Updates the associated Speaker's speakerTalks
     */

    public boolean AddNewEvent(String topic, Calendar time, String location, String organizer, String speaker) {
        if (validEvent(topic, time, location, speaker)) {
            // create a new event and add it to events
            EventTalk eventToAdd = new EventTalk(topic, time, location, organizer, speaker);
            events.put(eventToAdd.getId(), eventToAdd);
            return true;
        }
        return false;
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
        for(Event event: getAllEvents()) {
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
        if(!this.locations.contains(new_location)) {
            return false;
        }
        for(Event event: getAllEvents()) {
            String location1 = event.getLocation();
            Calendar time1 = event.getTime();
            Calendar time2 = event_to_change.getTime();

            if (event != event_to_change && location1.equals(new_location) && CheckTimeOverlap(time1, time2)) {
                return false;
            }
        }
        event_to_change.setLocation(new_location);
        if(!this.locations.contains(new_location)) {
            locations.add(new_location);
        }
        return true;
    }

    public void ChangeOrganizer(Event event_to_change, String new_organizer){
        event_to_change.setOrganizer(new_organizer);
    }

    /**
     * ChangeTime: Checks for conflicts due to same speaker being used in overlapping time
     */

    public boolean ChangeSpeaker(EventTalk talk_to_change, String new_speaker){
        for(EventTalk talk: getAllTalks()) {
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
        return this.locations;
    }

    // Temporary fix subject to removal
    public Integer fetchTalkID(String topic, Calendar time) {
        for(EventTalk talk: getAllTalks()) {
            if(talk.getTopic().equals(topic) && talk.getTime().compareTo(time) == 0) {
                return talk.getId();
            }
        }
        throw new RuntimeException();
    }


    // consider changing into id parameter
    public EventTalk fetchTalk(String topic, Calendar time) {
        for(EventTalk talk: getAllTalks()) {
            if(talk.getTopic().equals(topic) && talk.getTime().compareTo(time) == 0) {
                return talk;
            }
        }
        throw new RuntimeException();
    }

    // consider changing into id parameter
    public Event fetchEvent(String topic, Calendar time) {
        for(Event event: getAllEvents()) {
            if(event.getTopic().equals(topic) && event.getTime().compareTo(time) == 0) {
                return event;
            }
        }
        throw new RuntimeException();
    }

    public void cancelTalk(Integer id) {
        Event talkToCancel = events.get(id);
        if (talkToCancel instanceof EventTalk && !(Calendar.getInstance().compareTo(talkToCancel.getTime()) >= 0)) {
            events.remove(id);
        }
    }

    // (Helper) (NEW!)
    public ArrayList<EventTalk> fetchSpeakerTalks(String speaker) {
        ArrayList<EventTalk> speakerTalks = new ArrayList<>();
        for (EventTalk e : getAllTalks()) { if (e.getSpeaker().equals(speaker)) { speakerTalks.add(e); } }
        return speakerTalks;
    }

    // (NEW!)
    public HashMap<String[], Calendar> fetchSortedTalks(ArrayList<EventTalk> selectedTalks) {
        // Convert to sorted array
        EventTalk[] selectedTalksToSort = selectedTalks.toArray(new EventTalk[0]);
        Arrays.sort(selectedTalksToSort);
        // Assemble Tuples of Information
        HashMap<String[], Calendar> sortedSelectedTalks = new HashMap<>();
        String[] eventInfo;
        for (EventTalk e : selectedTalksToSort) {
            eventInfo = new String[5];
            eventInfo[0] = e.getTopic();
            eventInfo[1] = e.getSpeaker();
            eventInfo[2] = e.getLocation();
            eventInfo[3] = e.getTime().getTime().toString();
            eventInfo[4] = String.valueOf(e.getId());
            sortedSelectedTalks.put(eventInfo, e.getTime());
        }
        return sortedSelectedTalks;
    }

    // (NEW!)
    public HashMap<String[], Calendar> fetchSortedTalks() {
        return fetchSortedTalks(getAllTalks());
    }

    // (NEW!)
    public HashMap<String[], Calendar> fetchSortedTalks(String speaker) {
        return fetchSortedTalks(fetchSpeakerTalks(speaker));
    }


    public boolean containsEvent(String topic, Calendar time) {
        for(Event event: getAllEvents()) {
            if(event.getTopic().equals(topic) && event.getTime().compareTo(time) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean containsTalk(String topic, Calendar time) {
        for(EventTalk talk: getAllTalks()) {
            if(talk.getTopic().equals(topic) && talk.getTime().compareTo(time) == 0) {
                return true;
            }
        }
        return false;
    }

    // consider changing into id parameter
    public ArrayList<String> getAttendeesAtEvent(String topic, Calendar time) {
        Event event = this.fetchEvent(topic, time);
        return event == null ? new ArrayList<>() : event.getAttendees();
    }


}
