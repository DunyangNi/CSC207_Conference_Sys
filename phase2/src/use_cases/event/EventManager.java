package use_cases.event;

import entities.event.PanelDiscussion;
import enums.EventTypeEnum;
import exceptions.*;
import entities.event.Event;
import entities.event.Talk;
import gateways.HTMLWritable;
import exceptions.conflict.AlreadySignedUpException;
import exceptions.conflict.EventIsFullException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents the entire system of Events and their locations and speakers.
 */
public class EventManager implements Serializable, HTMLWritable {
    private final HashMap<Integer, Event> events;
    private final ArrayList<String> speakers;
    private final EventModifier eventModifier = new EventModifier();
    private final EventChecker eventChecker = new EventChecker();
    private final EventFactory eventFactory = new EventFactory();
    private int assignEventID;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Create an instance of <code>EventManager</code> given a HashMap of events, ArrayList of locations and speakers.
     *
     * @param events given <code>HashMap</code> of <code>Event</code> IDs to <code>Event</code> objects.
     * @param speakers given <code>ArrayList</code> of speaker usernames
     */
    public EventManager(HashMap<Integer, Event> events, ArrayList<String> speakers, LocationManager locationManager) {
        this.events = events;
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


    // TODO: REVISE THIS
    public ArrayList<Event> fetchEventList() {
        return new ArrayList<>(events.values());
    }

    // TODO: REVISE THIS
    public Talk fetchTalk(Integer id) {
        Event selectedTalk = events.get(id);
        return selectedTalk instanceof Talk ? (Talk) selectedTalk : null;
    }

    // TODO: REVISE THIS
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
     * @throws EventNotFoundException upon <code>Event</code> not being found.
     */
    public ArrayList<String> fetchEventAttendeeList(Integer id) throws EventNotFoundException {
        if (!events.containsKey(id))
            throw new EventNotFoundException();
        return events.get(id).getAttendees();
    }

    // TODO: REVISE THIS
    public ArrayList<Event> fetchSpeakerTalks(String speaker) {
        ArrayList<Event> speakerTalks = new ArrayList<>();
        for (Talk e : fetchTalkList()) {    // get only talks which the speaker gives
            if (e.getSpeaker().equals(speaker))
                speakerTalks.add(e);
        }
        return speakerTalks;
    }

    // TODO: REVISE THIS
    public HashMap<String[], Calendar> fetchSortedTalks(ArrayList<Event> selectedTalks) {
        // Converts from ArrayList to Array, and sorts the array
        Event[] selectedTalksToSort = selectedTalks.toArray(new Event[0]);
        Arrays.sort(selectedTalksToSort);

        // Creates a hashtable which key is string representation of a talk and value is its time
        HashMap<String[], Calendar> sortedSelectedTalks = new HashMap<>();
        String[] eventInfo;
        for (Event e : selectedTalksToSort) {
            eventInfo = new String[5];
            eventInfo[0] = e.getTopic();
            eventInfo[1] = "DOES NOT WORK";
            eventInfo[2] = e.getLocation();
            eventInfo[3] = e.getTime().getTime().toString();
            eventInfo[4] = String.valueOf(e.getId());
            sortedSelectedTalks.put(eventInfo, e.getTime());
        }
        return sortedSelectedTalks;
    }

    // TODO: REVISE THIS
    public HashMap<String[], Calendar> fetchSortedTalks() {
        return fetchSortedTalks(fetchEventList());
    }

    // TODO: REVISE THIS
    public HashMap<String[], Calendar> fetchSortedTalks(String speaker) {
        return fetchSortedTalks(fetchSpeakerTalks(speaker));
    }

    public void addNewEvent(EventTypeEnum type, String topic, Calendar time, String location, String organizer, ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly) throws InvalidEventTypeException, OutOfScheduleException, LocationInUseException, SpeakerIsBusyException {
        checkValidEvent(time, location, speakers);
        Event eventToAdd = eventFactory.CreateEvent(type, assignEventID++,topic, time, location, organizer, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
        events.put(eventToAdd.getId(), eventToAdd);
    }

    /**
     * Cancels a event given an id to search
     *
     * @param id id to be cancelled
     * @throws EventNotFoundException if the id is invalid
     */
    public void cancelEvent(Integer id) throws EventNotFoundException {
        if (!events.containsKey(id)) throw new EventNotFoundException();
        Event talkToCancel = events.get(id);
        if (!(Calendar.getInstance().compareTo(talkToCancel.getTime()) >= 0)) events.remove(id);
    }

    public void changeTime(Integer id, Calendar newTime) throws OutOfScheduleException, LocationInUseException, SpeakerIsBusyException, EventNotFoundException {
        if (!events.containsKey(id)) throw new EventNotFoundException();
        Event selectedEvent = events.get(id);
        ArrayList<String> selectedSpeakers = new ArrayList<>();
        if (selectedEvent instanceof Talk) selectedSpeakers.add(((Talk) selectedEvent).getSpeaker());
        else if (selectedEvent instanceof PanelDiscussion) selectedSpeakers.addAll(((PanelDiscussion) selectedEvent).getSpeakers());
        checkValidEvent(newTime, selectedEvent.getLocation(), selectedSpeakers);
        eventModifier.ChangeTime(events.get(id), newTime);
    }

    public void checkValidEvent(Calendar time, String location, ArrayList<String> speakers) throws OutOfScheduleException, LocationInUseException, SpeakerIsBusyException {
        eventChecker.checkValidEvent(time, location, speakers, fetchEventList());
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

    public void addAttendee(Integer id, String attendee) throws EventIsFullException, EventNotFoundException, AlreadySignedUpException {
        if (!events.containsKey(id))
            throw new EventNotFoundException();
        if (isFull(id))
            throw new EventIsFullException();
        if (isSignedUp(id, attendee))
            throw new AlreadySignedUpException();
        events.get(id).getAttendees().add(attendee);
    }

    public void removeAttendee(Integer id, String attendee) throws EventNotFoundException, AttendeeNotFoundException {
        if (!events.containsKey(id))
            throw new EventNotFoundException();
        if (!isSignedUp(id, attendee))
            throw new AttendeeNotFoundException();
        events.get(id).getAttendees().remove(attendee);
    }

    public boolean getVipRestriction(Integer id){
        return events.get(id).getVipOnly();
    }

    /**
     * @return a html file name whose name must not contain spaces
     */
    @Override public String getHTMLFileName() {
        return "EventSchedule.html";
    }

    /**
     * @return a title for a generated HTML
     */
    @Override public String getHTMLTitle() {
        return "Event Schedule";
    }

    /**
     * @return body for a generated HTML
     */
    @Override public String getHTMLBody() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String html = "";
        html += "<table border='1' style='border-collapse:collapse'>";
        html += "<caption>" + getHTMLTitle() + "</caption>";
        html += "<tr>";
        html += "<th> ID        </th>";
        html += "<th> TOPIC     </th>";
        html += "<th> LOCATION  </th>";
        html += "<th> TIME      </th>";
        html += "<th> CAPACITY  </th>";
        html += "<th> VIP ONLY  </th>";
        html += "<th> ORGANIZER </th>";
        html += "</tr>";
        for (Event evt: fetchEventList()){
            html += "<tr>";
            html += "<td>" + evt.getId()        + "</td>";
            html += "<td>" + evt.getTopic()     + "</td>";
            html += "<td>" + evt.getLocation()  + "</td>";
            html += "<td>" + df.format(evt.getTime().getTime()) + "</td>";
            html += "<td>" + evt.getCapacity()  + "</td>";
            html += "<td>" + evt.getVipOnly()   + "</td>";
            html += "<td>" + evt.getOrganizer() + "</td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }
}