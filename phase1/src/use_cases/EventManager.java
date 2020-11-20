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

/**
 * Represents the entire system of Events and their locations and speakers.
 */
public class EventManager implements Serializable {
    private HashMap<Integer, Event> events;
    private ArrayList<String> locations;
    private ArrayList<String> speakers;
    private EventModifier eventModifier = new EventModifier();
    private EventChecker eventChecker = new EventChecker();
    private int assignEventID;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Create an empty <code>EventManager</code>
     */
    public EventManager() {
        this(new HashMap<>(), new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Create a <code>EventManager</code> given a list of events, locations, and speakers
     */
    public EventManager(HashMap<Integer, Event> events, ArrayList<String> locations, ArrayList<String> speakers) {
        this.events = events;
        this.locations = locations;
        this.speakers = speakers;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * @param speaker speaker to add
     */
    public void addSpeakerKey(String speaker) {
        speakers.add(speaker);
    }

    /**
     * @return  <code>HashMap</code> for events
     */
    public HashMap<Integer, Event> getEvents() {
        return events;
    }

    /**
     * @param events a list of events to add
     */
    public void setEvents(HashMap<Integer, Event> events) {
        this.events = events;
    }

    /**
     * @return <code>ArrayList</code> for locations
     */
    public ArrayList<String> getLocations() {
        return locations;
    }

    /**
     * @param locations a list of locations to add
     */
    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }

    /**
     * Gets a list of events without their ids.
     * @return <code>ArrayList</code> for events
     */
    public ArrayList<Event> fetchEventList() {
        return new ArrayList<>(events.values());
    }

    /**
     * Gets the corresponding talk given an id
     *
     * @param id id to search
     * @return <code>Talk</code> or null
     */
    public Talk fetchTalk(Integer id) {
        Event selectedTalk = events.get(id);
        return selectedTalk instanceof Talk ? (Talk) selectedTalk : null;
    }

    /**
     * Gets a list of talks
     *
     * @return <code>ArrayList</code> for talks
     */
    public ArrayList<Talk> fetchTalkList() {
        ArrayList<Talk> talks = new ArrayList<>();
        for (Event e : fetchEventList()) { // get only Talks
            if (e instanceof Talk) {
                talks.add((Talk) e);
            }
        }
        return talks;
    }

    /**
     * Gets a list of talks which a speaker gives
     *
     * @param speaker a speaker who will give talks
     * @return <code>ArrayList</code> for talks
     */
    public ArrayList<Talk> fetchSpeakerTalks(String speaker) {
        ArrayList<Talk> speakerTalks = new ArrayList<>();
        for (Talk e : fetchTalkList()) {    // get only talks which the speaker gives
            if (e.getSpeaker().equals(speaker))
                speakerTalks.add(e);
        }
        return speakerTalks;
    }

    /**
     * Stores a list of talks to <code>Hashmap</code> in ascending order, which key is string
     * representation of a talk and value is the talk event time.
     *
     * @param selectedTalks a list of talks to be added in ascending order
     * @return <code>HashMap</code> for talks
     */
    public HashMap<String[], Calendar> fetchSortedTalks(ArrayList<Talk> selectedTalks) {
        // Converts from ArrayList to Array, and sorts the array
        Talk[] selectedTalksToSort = selectedTalks.toArray(new Talk[0]);
        Arrays.sort(selectedTalksToSort);

        // Creates a hashtable which key is string representation of a talk and value is its time
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

    /**
     * Gets talks in ascending order
     *
     * @return <code>HashMap</code> for talks
     */
    public HashMap<String[], Calendar> fetchSortedTalks() {
        return fetchSortedTalks(fetchTalkList());
    }

    /**
     * Gets talks in ascending order which a speaker gives
     *
     * @param speaker to search
     * @return <code>HashMap</code> for talks
     */
    public HashMap<String[], Calendar> fetchSortedTalks(String speaker) {
        return fetchSortedTalks(fetchSpeakerTalks(speaker));
    }

    /**
     * Adds a new location if valid
     *
     * @param location a new location to add
     * @throws ConflictException if a new location is invalid
     */
    public void addNewLocation(String location) throws ConflictException {
        if (this.locations.contains(location))
            throw new ConflictException("Location " + location + " already exists.");
        this.locations.add(location);
    }

    /**
     * Adds a new event given its information
     *
     * @param topic topic for a new event
     * @param time time for a new event
     * @param location location for a new event
     * @param organizer organizer for a new event
     * @return id for a new id which has been added
     * @throws ConflictException if <code>checkValidEvent</code> throws
     * @throws ObjectNotFoundException if <code>checkValidEvent</code> throws
     */
    public Integer addNewEvent(String topic, Calendar time, String location, String organizer) throws ConflictException, ObjectNotFoundException {
        checkValidEvent(time, location);
        Event eventToAdd = new Event(assignEventID++, topic, time, location, organizer);
        events.put(eventToAdd.getId(), eventToAdd);
        return eventToAdd.getId();

    }

    /**
     * Adds a new event given its information
     * @param topic topic for a new event
     * @param time time for a new event
     * @param location location for a new event
     * @param organizer organizer for a new event
     * @param speaker speaker for a new event
     * @return id for a new id which has been added
     * @throws ConflictException if <code>checkValidEvent</code> throws or a speaker is invalid
     * @throws ObjectNotFoundException if <code>checkValidEvent</code> throws
     */
    public Integer addNewTalk(String topic, Calendar time, String location, String organizer, String speaker) throws ConflictException, ObjectNotFoundException {
        if (!speakers.contains(speaker))
            throw new ObjectNotFoundException("Speaker " + speaker);
        checkValidTalk(time, location, speaker);
        Talk eventToAdd = new Talk(assignEventID++, topic, time, location, organizer, speaker);
        events.put(eventToAdd.getId(), eventToAdd);
        return eventToAdd.getId();
    }

    /**
     * Cancels a talk given an id to search
     *
     * @param id id to be cancelled
     * @throws ObjectNotFoundException if the id is invalid or is associated with non-talk
     */
    public void cancelTalk(Integer id) throws ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Talk");
        if (!(events.get(id) instanceof Talk))
            throw new ObjectNotFoundException("Talk");
        Event talkToCancel = events.get(id);
        if (!(Calendar.getInstance().compareTo(talkToCancel.getTime()) >= 0))
            events.remove(id);
    }

    /**
     * Changes a topic to new topic given an id to search
     *
     * @param id id to search
     * @param new_topic new topic
     * @throws ObjectNotFoundException if the id is invalid
     */
    public void changeTopic(Integer id, String new_topic) throws ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        eventModifier.ChangeTopic(events.get(id), new_topic);
    }

    /**
     * Changes time to new time given an id to search
     *
     * @param id id to search
     * @param newTime new time
     * @throws ObjectNotFoundException if the id is invalid
     * @throws ConflictException if <code>checkValidTalk</code> or <code>checkValidEvent</code>
     * throw
     */
    public void changeTime(Integer id, Calendar newTime) throws ObjectNotFoundException, ConflictException {
        // is id valid?
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");

        // Validation
        Event selectedEvent = events.get(id);
        if (selectedEvent instanceof Talk)
            checkValidTalk(newTime, selectedEvent.getLocation(), ((Talk) selectedEvent).getSpeaker());
        else
            checkValidEvent(newTime, selectedEvent.getLocation());
        eventModifier.ChangeTime(events.get(id), newTime);
    }

    /**
     * Changes a location to a new location
     * @param id id to search
     * @param newLocation new location
     * @throws ObjectNotFoundException if the id is invalid
     * @throws ConflictException if <code>checkValidTalk</code> or <code>checkValidEvent</code>
     * throw
     */
    public void changeLocation(Integer id, String newLocation) throws ObjectNotFoundException, ConflictException {
        // is id valid?
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");

        // Validation
        Event selectedEvent = events.get(id);
        if (selectedEvent instanceof Talk)
            checkValidTalk(selectedEvent.getTime(), newLocation, ((Talk) selectedEvent).getSpeaker());
        else
            checkValidEvent(selectedEvent.getTime(), newLocation);
        eventModifier.ChangeLocation(events.get(id), newLocation);
    }

    /**
     * Changes an organizer to a new organizer
     *
     * @param id id to search
     * @param new_organizer new organizer
     * @throws ObjectNotFoundException if id is invalid
     */
    public void changeOrganizer(Integer id, String new_organizer) throws ObjectNotFoundException{
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        eventModifier.ChangeOrganizer(events.get(id), new_organizer);
    }

    /**
     * Validates given time and location
     *
     * @param time time to check
     * @param location location to check
     * @throws ConflictException if <code>checkValidEvent</code> throws
     * @throws ObjectNotFoundException if <code>checkValidEnt</code> throws
     */
    public void checkValidEvent(Calendar time, String location) throws ConflictException, ObjectNotFoundException {
        eventChecker.checkValidEvent(time, location, locations, fetchEventList());
    }

    /**
     * Validates given time, location, and speaker
     *
     * @param time time to check
     * @param location location to check
     * @param speaker speaker to check
     * @throws ConflictException if <code>checkValidEvent</code> throws
     * @throws ObjectNotFoundException if <code>checkValidEnt</code> throws
     */
    public void checkValidTalk(Calendar time, String location, String speaker) throws ConflictException, ObjectNotFoundException {
        eventChecker.checkValidTalk(time, location, speaker, locations, fetchTalkList(), fetchEventList());
    }

    /**
     * Validates if a given id is talk or not
     *
     * @param id id to check
     * @return True if the id is associated with a talk. False otherwise.
     */
    public boolean isTalk(Integer id) {
        return events.get(id) instanceof Talk;
    }

    /**
     * Validates if a speaker gives a talk associated with the given id
     *
     * @param id id to be used to find a talk
     * @param speaker speaker to check
     * @return True if the speaks gives a talk associated with the id. False otherwise
     */
    public boolean isSpeakerOfTalk(Integer id, String speaker) {
        return isTalk(id) && fetchTalk(id).getSpeaker().equals(speaker);
    }
}