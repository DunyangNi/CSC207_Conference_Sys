package use_cases;

import enums.EventType;
import exceptions.*;
import entities.Event;
import entities.Talk;
import java.io.Serializable;
import java.util.*;

/**
 * Represents the entire system of Events and their locations and speakers.
 */
public class EventManager implements Serializable {
    private HashMap<Integer, Event> events;
    private final ArrayList<String> speakers;
    private final EventModifier eventModifier = new EventModifier();
    private final EventChecker eventChecker = new EventChecker();
    private final EventFactory eventFactory = new EventFactory();
    private int assignEventID;
    private final EventLocationManager eventLocationManager;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Create an instance of <code>EventManager</code> given a HashMap of events, ArrayList of locations and speakers.
     *
     * @param events given <code>HashMap</code> of <code>Event</code> IDs to <code>Event</code> objects.
     * @param locations given <code>ArrayList</code> of location Strings
     * @param speakers given <code>ArrayList</code> of speaker usernames
     */
    public EventManager(HashMap<Integer, Event> events, ArrayList<String> locations, ArrayList<String> speakers, EventLocationManager eventLocationManager) {
        this.events = events;
        this.eventLocationManager = eventLocationManager;
        this.speakers = speakers;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    private boolean isFull(Integer id) {
        Event selectedEvent = events.get(id);
        return selectedEvent.getCapacity() == selectedEvent.getAttendees().size();
    }

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

    public void setEventCapacity(Integer id, Integer newCapacity) {
        Event selectedEvent = events.get(id);
        selectedEvent.setCapacity(newCapacity);
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
     * Attempts to return an <code>ArrayList</code> of usernames of Attendees associated with a <code>Event</code>.
     * @param id given ID of a <code>Event</code>
     * @return <code>ArrayList</code> of usernames of Attendees associated with a <code>Event</code>
     * @throws ObjectNotFoundException upon <code>Event</code> not being found.
     */
    public ArrayList<String> fetchEventAttendeeList(Integer id) throws ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        return events.get(id).getAttendees();
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
     * Adds a new event given its information
     *
     * @param type type of event
     * @param topic topic for a new event
     * @param time time for a new event
     * @param location location for a new event
     * @param organizer organizer for a new event
     * @param speakers <code>ArrayList</code> of arbitrary usernames of speakers
     * @param capacity maximum capacity for a new event
     * @return id for a new id which has been added
     * @throws ConflictException if <code>checkValidEvent</code> throws
     * @throws ObjectNotFoundException if <code>checkValidEvent</code> throws
     */
    public Integer addNewEvent(EventType type, String topic, Calendar time, String location, String organizer, ArrayList<String> speakers, Integer capacity, Boolean vipOnly) throws ConflictException, ObjectNotFoundException, InvalidEventTypeException {
        checkValidEvent(time, location);
        Event eventToAdd = eventFactory.CreateEvent(type, assignEventID++,topic, time, location, organizer, speakers, capacity, vipOnly);
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
    public void cancelEvent(Integer id) throws ObjectNotFoundException {
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
        ArrayList<String> locations = this.eventLocationManager.getNameList();
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
        ArrayList<String> locations = this.eventLocationManager.getNameList();
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

    /**
     * Returns true iff <code>Event</code> with given ID contains a given <code>Attendee</code> username.
     *
     * @param id given <code>Event</code> ID
     * @param attendee given <code>Attendee</code> username
     * @return <code>Event</code> contains a given <code>Attendee</code>
     */
    public boolean isSignedUp(Integer id, String attendee) {
        return events.get(id).getAttendees().contains(attendee);
    }

    /**
     * Attempts to add <code>Attendee</code> with given username to <code>Event</code> attendance with given ID.
     *
     * @param id given ID of an associated <code>Event</code>
     * @param attendee given username of <code>Attendee</code>
     * @throws ConflictException upon <code>Event</code> being full or <code>Attendee</code> is already signed up.
     * @throws ObjectNotFoundException upon <code>Event</code> not being found
     */
    public void addAttendee(Integer id, String attendee) throws ConflictException, ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event not found.");
        if (isFull(id))
            throw new ConflictException("Talk is full.");
        if (isSignedUp(id, attendee))
            throw new ConflictException("You are already signed up for this Talk.");
        events.get(id).getAttendees().add(attendee);
    }

    /**
     * Attempts to remove <code>Attendee</code> with given username from <code>Event</code> attendance with given ID.
     *
     * @param id given ID of an associated <code>Event</code>
     * @param attendee given username of <code>Attendee</code>
     * @throws ObjectNotFoundException upon <code>Event</code> or <code>Attendee</code> not being found
     */
    public void removeAttendee(Integer id, String attendee) throws ObjectNotFoundException {
        if (!events.containsKey(id))
            throw new ObjectNotFoundException("Event");
        if (!isSignedUp(id, attendee))
            throw new ObjectNotFoundException("Attendee");
        events.get(id).getAttendees().remove(attendee);
    }

    public boolean getVipRestriction(Integer id){
        return events.get(id).getVipOnly();
    }
}