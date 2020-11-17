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
    private EventModifier eventModifier = new EventModifier();

    public EventManager() {
        this(new HashMap<>(), new ArrayList<>());
    }

    public EventManager(HashMap<Integer, Event> events, ArrayList<String> locations) {
        this.events = events;
        this.locations = locations;
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

    public ArrayList<String> fetchTalkAttendeeList(Integer id) throws ObjectNotFoundException {
        if (!this.isTalk(id)) {
            throw new ObjectNotFoundException();
        }
        Event selectedEvent = events.get(id);
        return eventModifier.getAttendees(selectedEvent);
    }

    public ArrayList<Talk> fetchSpeakerTalks(String speaker) {
        ArrayList<Talk> speakerTalks = new ArrayList<>();
        for (Talk e : fetchTalkList()) {
            if (e.getSpeaker().equals(speaker)) {
                speakerTalks.add(e);
            }
        }
        return speakerTalks;
    }

    public HashMap<String[], Calendar> fetchSortedTalks(ArrayList<Talk> selectedTalks) {
        // Convert to sorted array
        Talk[] selectedTalksToSort = selectedTalks.toArray(new Talk[0]);
        Arrays.sort(selectedTalksToSort);
        // Assemble Tuples of Information
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
        if (!this.locations.contains(location)) {
            this.locations.add(location);
        } else {
            throw new ConflictException("Location already exists");
        }
    }

    public Integer addNewEvent(String topic, Calendar time, String location, String organizer) throws ConflictException {
        if (isValidEvent(time, location, locations, fetchEventList())) {
            Event eventToAdd = new Event(topic, time, location, organizer);
            events.put(eventToAdd.getId(), eventToAdd);
            return eventToAdd.getId();
        }
        throw new ConflictException("Event conflicts with another");
    }

    public Integer addNewTalk(String topic, Calendar time, String location, String organizer, String speaker) throws ConflictException {
        if (isValidTalk(time, location, locations, speaker, fetchTalkList(), fetchEventList())) {
            // create a new event and add it to events
            Talk eventToAdd = new Talk(topic, time, location, organizer, speaker);
            events.put(eventToAdd.getId(), eventToAdd);
            return eventToAdd.getId();
        }
        throw new ConflictException("addNewTalk");
    }

    public void cancelTalk(Integer id) throws ObjectNotFoundException {
        if (!events.containsKey(id)) {
            throw new ObjectNotFoundException();
        }
        if (!(events.get(id) instanceof Talk)) {
            throw new ObjectNotFoundException();
        }
        Event talkToCancel = events.get(id);
        if (talkToCancel instanceof Talk && !(Calendar.getInstance().compareTo(talkToCancel.getTime()) >= 0))
            events.remove(id);
    }

    public void changeTopic(Integer id, String new_topic) {
        eventModifier.ChangeTopic(events.get(id), new_topic);
    }

    public void changeTime(Integer id, Calendar newTime) throws ObjectNotFoundException, ConflictException {
        try {
            if (!events.containsKey(id)) {
                throw new ObjectNotFoundException();
            }
            eventModifier.ChangeTime(events.get(id), newTime, fetchEventList());
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean changeLocation(Integer id, String new_location) {
        return eventModifier.ChangeLocation(events.get(id), new_location, this.locations, fetchEventList());
    }

    public void changeOrganizer(Integer id, String new_organizer) {
        eventModifier.ChangeOrganizer(events.get(id), new_organizer);
    }

    public boolean isValidEvent(Calendar time, String location, ArrayList<String> locations, ArrayList<Event> events) throws ConflictException {
        // check if location is valid
        if (!locations.contains(location)) {
            throw new ConflictException("Location");
        }
        // check if time is valid
        if (!(9 <= time.get(Calendar.HOUR_OF_DAY) && time.get(Calendar.HOUR_OF_DAY) <= 16)) {
            throw new ConflictException("Time");
        }
        // check if any conflicting events or events already existing
        for (Event event : events) {
            if (event.getLocation().equals(location) && event.getTime().equals(time)) {
                throw new ConflictException("Location + Time");
            }
        }
        return true;
    }

    public boolean isValidTalk(Calendar time, String location, ArrayList<String> locations, String speaker, ArrayList<Talk> talks, ArrayList<Event> events) throws ConflictException {
        // TODO: 11/17/20 Prevent double booking a speaker
        return isValidEvent(time, location, locations, events);
    }

    public boolean isTalk(Integer id) {
        return events.get(id) instanceof Talk;
    }

    public boolean isSpeakerOfTalk(Integer id, String speaker) {
        return isTalk(id) && fetchTalk(id).getSpeaker().equals(speaker);
    }
}