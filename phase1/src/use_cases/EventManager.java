package use_cases;

import Throwables.ConflictException;
import Throwables.ObjectNotFoundException;
import entities.Event;
import entities.Talk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class EventManager implements Serializable {
    private HashMap<Integer, Event> events;
    private ArrayList<String> locations;
    private ArrayList<String> speakers;
    private EventModifier eventModifier = new EventModifier();
    private EventChecker eventChecker = new EventChecker();
    private int assignEventID;

    public EventManager() {
        this(new HashMap<>(), new ArrayList<>(), new ArrayList<>());
    }

    public EventManager(HashMap<Integer, Event> events, ArrayList<String> locations, ArrayList<String> speakers) {
        this.events = events;
        this.locations = locations;
        this.speakers = speakers;
    }

    public void addSpeakerKey(String speaker) {
        speakers.add(speaker);
    }

    public HashMap<Integer, Event> getEvents() {
        return events;
    }

    public void setEvents(HashMap<Integer, Event> events) {
        this.events = events;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    public ArrayList<Event> fetchEventList() {
        return new ArrayList<>(events.values());
    }

    public Talk fetchTalk(Integer id) {
        Event selectedTalk = events.get(id);
        return selectedTalk instanceof Talk ? (Talk) selectedTalk : null;
    }

    public ArrayList<Talk> fetchTalkList() {
        ArrayList<Talk> talks = new ArrayList<>();
        for (Event e : fetchEventList()) {
            if (e instanceof Talk) {
                talks.add((Talk) e);
            }
        }
        return talks;
    }

    public ArrayList<Talk> fetchSpeakerTalks(String speaker) {
        ArrayList<Talk> speakerTalks = new ArrayList<>();
        for (Talk e : fetchTalkList()) {
            if (e.getSpeaker().equals(speaker))
                speakerTalks.add(e);
        }
        return speakerTalks;
    }

    public HashMap<String[], Calendar> fetchSortedTalks(ArrayList<Talk> selectedTalks) {
        Talk[] selectedTalksToSort = selectedTalks.toArray(new Talk[0]);
        Arrays.sort(selectedTalksToSort);
        HashMap<String[], Calendar> sortedSelectedTalks = new HashMap<>();
        String[] eventInfo;
        for (Talk e : selectedTalksToSort) {
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

    public HashMap<String[], Calendar> fetchSortedTalks() {
        return fetchSortedTalks(fetchTalkList());
    }

    public HashMap<String[], Calendar> fetchSortedTalks(String speaker) {
        return fetchSortedTalks(fetchSpeakerTalks(speaker));
    }

    public void addNewLocation(String location) throws ConflictException {
        if (this.locations.contains(location))
            throw new ConflictException("Location " + location + " already exists.");
        this.locations.add(location);
    }

    public Integer addNewEvent(String topic, Calendar time, String location, String organizer) throws ConflictException, ObjectNotFoundException {
        checkValidEvent(time, location);
        Event eventToAdd = new Event(assignEventID++, topic, time, location, organizer);
        events.put(eventToAdd.getId(), eventToAdd);
        return eventToAdd.getId();

    }

    public Integer addNewTalk(String topic, Calendar time, String location, String organizer, String speaker) throws ConflictException, ObjectNotFoundException {
        if (!speakers.contains(speaker))
            throw new ObjectNotFoundException("Speaker " + speaker);
        checkValidTalk(time, location, speaker);
        Talk eventToAdd = new Talk(assignEventID++, topic, time, location, organizer, speaker);
        events.put(eventToAdd.getId(), eventToAdd);
        return eventToAdd.getId();
    }

    public void cancelTalk(Integer id) throws ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Talk");
        if (!(events.get(id) instanceof Talk))
            throw new ObjectNotFoundException("Talk");
        Event talkToCancel = events.get(id);
        if (!(Calendar.getInstance().compareTo(talkToCancel.getTime()) >= 0))
            events.remove(id);
    }

    // is it used?
    public void changeTopic(Integer id, String new_topic) throws ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        eventModifier.ChangeTopic(events.get(id), new_topic);
    }

    public void changeTime(Integer id, Calendar newTime) throws ObjectNotFoundException, ConflictException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        Event selectedEvent = events.get(id);
        if (selectedEvent instanceof Talk)
            checkValidTalk(newTime, selectedEvent.getLocation(), ((Talk) selectedEvent).getSpeaker());
        else
            checkValidEvent(newTime, selectedEvent.getLocation());
        eventModifier.ChangeTime(events.get(id), newTime);
    }

    public void changeLocation(Integer id, String newLocation) throws ObjectNotFoundException, ConflictException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        Event selectedEvent = events.get(id);
        if (selectedEvent instanceof Talk)
            checkValidTalk(selectedEvent.getTime(), newLocation, ((Talk) selectedEvent).getSpeaker());
        else
            checkValidEvent(selectedEvent.getTime(), newLocation);
        eventModifier.ChangeLocation(events.get(id), newLocation);
    }

    // is it used?
    public void changeOrganizer(Integer id, String new_organizer) throws ObjectNotFoundException{
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        eventModifier.ChangeOrganizer(events.get(id), new_organizer);
    }

    public void checkValidEvent(Calendar time, String location) throws ConflictException, ObjectNotFoundException {
        eventChecker.checkValidEvent(time, location, locations, fetchEventList());
    }

    public void checkValidTalk(Calendar time, String location, String speaker) throws ConflictException, ObjectNotFoundException {
        eventChecker.checkValidTalk(time, location, speaker, locations, fetchTalkList(), fetchEventList());
    }

    public boolean isTalk(Integer id) {
        return events.get(id) instanceof Talk;
    }

    public boolean isSpeakerOfTalk(Integer id, String speaker) {
        return isTalk(id) && fetchTalk(id).getSpeaker().equals(speaker);
    }
}