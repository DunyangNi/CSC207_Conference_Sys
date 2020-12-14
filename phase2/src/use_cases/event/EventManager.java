package use_cases.event;

import entities.event.*;
import enums.EventTypeEnum;
import exceptions.*;
import gateways.HTMLWritable;
import exceptions.conflict.AlreadySignedUpException;
import exceptions.conflict.EventIsFullException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents the entire system of Events and their locations and speakers.
 */
public class EventManager implements Serializable, HTMLWritable {
    private final HashMap<Integer, Event> events;
    private final EventModifier eventModifier = new EventModifier();
    private final EventChecker eventChecker = new EventChecker();
    private final EventFactory eventFactory = new EventFactory();
    private int assignEventID;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    public EventManager(HashMap<Integer, Event> events) {
        this.events = events;
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * @return True iff event is full (Attendees fill capacity)
     * @param id ID of desired event to inspect
     */

    private boolean eventIsFull(Integer id) {
        Event selectedEvent = events.get(id);
        return selectedEvent.getCapacity() == selectedEvent.getAttendees().size();
    }

    /**
     * @return list of events
     */

    public ArrayList<Event> fetchEventList() {
        return new ArrayList<>(events.values());
    }

    /**
     * Attempts to return an <code>ArrayList</code> of usernames of Attendees associated with a <code>Event</code>.
     * @param id given ID of a <code>Event</code>
     * @return <code>ArrayList</code> of usernames of Attendees associated with a <code>Event</code>
     * @throws EventNotFoundException upon <code>Event</code> not being found.
     */
    public ArrayList<String> fetchEventAttendeeList(Integer id) throws EventNotFoundException {
        if (!events.containsKey(id)) throw new EventNotFoundException();
        return events.get(id).getAttendees();
    }

    /**
     * @return list of events that a speaker is talking at
     * @param speaker username of desired speaker
     */

    private ArrayList<Event> getSpeakerEvents(String speaker) {
        ArrayList<Event> speakerTalks = new ArrayList<>();
        EventVisitor visitor = new EventHelper();
        for (Event e : fetchEventList()) { if (e.acceptSpeakers(visitor).contains(speaker)) speakerTalks.add(e); }
        return speakerTalks;
    }

    /**
     * @return list of sorted events
     * @param selectedEvents a list of events that we want to sort
     */

    private ArrayList<String> getSortedEvents(ArrayList<Event> selectedEvents) {
        Event[] selectedTalksToSort = selectedEvents.toArray(new Event[0]);
        Arrays.sort(selectedTalksToSort);
        ArrayList<String> sortedSelectedEvents = new ArrayList<>();
        for (Event e : selectedTalksToSort) {
            if (Calendar.getInstance().compareTo(e.getTime()) < 0) sortedSelectedEvents.add(e.toString());
        }
        return sortedSelectedEvents;
    }

    /**
     * @return list of sorted events by event ID (Increasing order)
     * @param eventIDs list of event IDs which we want to sort
     */

    public ArrayList<String> getSortedEventsByID(ArrayList<Integer> eventIDs) {
        ArrayList<Event> selectedEvents = new ArrayList<>();
        for (Integer id : eventIDs) { selectedEvents.add(events.get(id)); }
        return getSortedEvents(selectedEvents);
    }

    /**
     * @return list of all events in Eventmanager, now sorted
     */

    public ArrayList<String> getAllSortedEvents() { return getSortedEvents(fetchEventList()); }

    /**
     * @return sorted list of events where a speaker is talking at
     * @param speaker username of desired speaker
     */

    public ArrayList<String> getSpeakerSortedEvents(String speaker) { return getSortedEvents(getSpeakerEvents(speaker)); }

    /** Attempts to add a new event with the given information.
     * @param type an enum representing the type of the desired event
     * @param topic desired topic
     * @param time desired time
     * @param location desired location
     * @param organizer desired organizer
     * @param speakers list of desired speakers
     * @param capacity desired capacity
     * @param tables desired table count
     * @param chairs desired chair count
     * @param hasInternet desired internet requirement
     * @param hasSoundSystem desired sound system requirement
     * @param hasPresentationScreen desired presentation screen requirement
     * @param vipOnly desired VIP restriction status
     *
     * @throws InvalidEventTypeException when type is an invalid event type
     * @throws OutOfScheduleException when time is an invalid event time
     * @throws LocationInUseException when location is already in use at the desired time
     * @throws SpeakerIsBusyException when speaker is already at another event at the desired time
     */

    public void addNewEvent(EventTypeEnum type,
                            String topic, Calendar time, String location, String organizer,
                            ArrayList<String> speakers, Integer capacity, int tables, int chairs,
                            boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen,
                            Boolean vipOnly) throws InvalidEventTypeException, OutOfScheduleException,
            LocationInUseException, SpeakerIsBusyException {
        checkValidEvent(time, location, speakers);
        Event eventToAdd = eventFactory.getEvent(type, assignEventID++,topic, time, location, organizer, speakers,
                capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
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

    /**
     * Changes the time of an event.
     *
     * @param id id of event
     * @param newTime desired time
     * @throws OutOfScheduleException when newTime is an invalid time
     * @throws LocationInUseException when location is already in use at the desired time
     * @throws SpeakerIsBusyException when speaker is at another talk at desired time
     * @throws EventNotFoundException when id is not valid
     */

    public void changeTime(Integer id, Calendar newTime) throws OutOfScheduleException, LocationInUseException, SpeakerIsBusyException, EventNotFoundException {
        if (!events.containsKey(id)) throw new EventNotFoundException();
        Event selectedEvent = events.get(id);
        EventVisitor visitor = new EventHelper();
        ArrayList<String> selectedSpeakers = new ArrayList<>(selectedEvent.acceptSpeakers(visitor));
        checkValidEvent(newTime, selectedEvent.getLocation(), selectedSpeakers);
        eventModifier.ChangeTime(events.get(id), newTime);
    }

    /**
     * Checks if a hypothetical event given a time, location and list of speakers would be valid (no conflict)
     *
     * @param time hypothetical time
     * @param location hypothetical location
     * @param speakers list of hypothetical speakers
     *
     * @throws OutOfScheduleException when the event time is invalid
     * @throws LocationInUseException when the hypothetical event would cause a double booking of a location
     * @throws SpeakerIsBusyException when the hypothetical event would cause a double booking of a speaker
     */

    public void checkValidEvent(Calendar time, String location, ArrayList<String> speakers) throws OutOfScheduleException,
            LocationInUseException, SpeakerIsBusyException {
        eventChecker.checkValidEvent(time, location, speakers, fetchEventList());
    }

    /**
     * @return True iff speaker is talking at event with ID id
     * @param id ID of desired event
     * @param speaker username of desired speaker
     */

    public boolean isSpeakerOfEvent(Integer id, String speaker) {
        EventVisitor visitor = new EventHelper();
        return events.get(id).acceptSpeakers(visitor).contains(speaker);
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
     * Add an attendee to an event
     * @param id ID of desired event
     * @param attendee username of desired attendee
     * @throws EventIsFullException when event is full
     * @throws EventNotFoundException when id is invalid
     * @throws AlreadySignedUpException when attendee already signed up
     */

    public void addAttendee(Integer id, String attendee) throws EventIsFullException, EventNotFoundException, AlreadySignedUpException {
        if (!events.containsKey(id))
            throw new EventNotFoundException();
        if (eventIsFull(id))
            throw new EventIsFullException();
        if (isSignedUp(id, attendee))
            throw new AlreadySignedUpException();
        events.get(id).getAttendees().add(attendee);
    }

    /**
     * Remove an attendee from an event
     * @param id ID of desired event
     * @param attendee username of desired attendee
     * @throws EventNotFoundException when id is invalid
     * @throws AttendeeNotFoundException when attendee parameter is invalid
     */

    public void removeAttendee(Integer id, String attendee) throws EventNotFoundException, AttendeeNotFoundException {
        if (!events.containsKey(id))
            throw new EventNotFoundException();
        if (!isSignedUp(id, attendee))
            throw new AttendeeNotFoundException();
        events.get(id).getAttendees().remove(attendee);
    }

    /**
     * @return True iff event is VIP only
     * @param id ID of desired event
     */

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
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        StringBuilder html = new StringBuilder();
        html.append("<table border='1' style='border-collapse:collapse'>");
        html.append("<caption>").append(getHTMLTitle()).append("</caption>");
        html.append("<tr>");
        html.append("<th> ID        </th>");
        html.append("<th> SPEAKER   </th>");
        html.append("<th> TYPE      </th>");
        html.append("<th> TOPIC     </th>");
        html.append("<th> LOCATION  </th>");
        html.append("<th> TIME      </th>");
        html.append("<th> CAPACITY  </th>");
        html.append("<th> VIP ONLY  </th>");
        html.append("<th> CAPACITY  </th>");
        html.append("<th> TABLE     </th>");
        html.append("<th> INTERNET  </th>");
        html.append("<th> SOUND     </th>");
        html.append("<th> SCREEN    </th>");
        html.append("<th> ORGANIZER </th>");
        html.append("</tr>");
        for (Event evt: fetchEventList()) {
            EventVisitor visitor = new EventHelper();
            String eventType = evt.acceptType(visitor);
            StringBuilder eventSpeakers = new StringBuilder();
            for (String speaker : evt.acceptSpeakers(visitor)) { eventSpeakers.append(speaker).append(", "); }
            if (eventSpeakers.length() != 0) eventSpeakers.delete(eventSpeakers.length()-2, eventSpeakers.length());
            html.append("<tr>");
            html.append("<td>").append(evt.getId()).append("</td>");
            html.append("<td>").append(eventSpeakers).append("</td>");
            html.append("<td>").append(eventType).append("</td>");
            html.append("<td>").append(evt.getTopic()).append("</td>");
            html.append("<td>").append(evt.getLocation()).append("</td>");
            html.append("<td>").append(df.format(evt.getTime().getTime())).append("</td>");
            html.append("<td>").append(evt.getCapacity()).append("</td>");
            html.append("<td>").append(evt.getVipOnly()).append("</td>");
            html.append("<td>").append(evt.getCapacity()).append("</td>");
            html.append("<td>").append(evt.getTables()).append("</td>");
            html.append("<td>").append(evt.getRequiresInternet()).append("</td>");
            html.append("<td>").append(evt.getRequiresSoundSystem()).append("</td>");
            html.append("<td>").append(evt.getRequiresPresentationScreen()).append("</td>");
            html.append("<td>").append(evt.getOrganizer()).append("</td>");
            html.append("</tr>");
        }
        html.append("</table>");
        return html.toString();
    }
}