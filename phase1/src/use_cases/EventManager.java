package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import entities.*;

import java.util.Arrays;
import java.util.HashMap;

public class EventManager implements Serializable {
    private HashMap<Integer, Event> events;
    private ArrayList<String> locations;
    private EventChecker checker = new EventChecker();
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

    // (NEW!)
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
        return checker.validEvent(topic, time, location, speaker, this.locations, getAllTalks(), getAllEvents());
    }

    /**
     * (NEW!) (Helper) Returns true iff Event is valid: no conflicting time or existing events.
     * @param topic given topic
     * @param time given time
     * @param location given location
     * @return true iff Event is valid: no conflicting time or existing events.
     */
    public boolean validEvent(String topic, Calendar time, String location) {
        return checker.validEvent(topic, time, location, this.locations, getAllEvents());
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

    public boolean ChangeTime(Integer id, Calendar newTime){
        Calendar curr_time = Calendar.getInstance();
        Event selectedEvent = events.get(id);
        if (curr_time.compareTo(selectedEvent.getTime()) >= 0 || curr_time.compareTo(newTime) >= 0) { return false; }
        for (Event event: getAllEvents()) {
            String eventLocation = event.getLocation(); Calendar eventTime = event.getTime();
            if (!event.equals(selectedEvent) && eventLocation.equals(selectedEvent.getLocation()) &&
                    CheckTimeOverlap(eventTime, newTime)) { return false; }
        }
        selectedEvent.setTime(newTime);
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

    // consider returning a copy of Locations to prevent any outside modification !
    public ArrayList<String> fetchLocations() {
        return this.locations;
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

    public boolean isTalk(Integer id) {
        return events.get(id) instanceof EventTalk;
    }

    public boolean isSpeakerOfTalk(Integer id, String speaker) {
        return isTalk(id) && getTalk(id).getSpeaker().equals(speaker);
    }

    public ArrayList<String> getAttendeesAtEvent(Integer id) {
        Event selectedEvent = events.get(id);
        return selectedEvent == null ? new ArrayList<>() : selectedEvent.getAttendees();
    }
}
