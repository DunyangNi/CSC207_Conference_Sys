package controllers.event;

import enums.EventTypeEnum;
import exceptions.*;
import exceptions.conflict.*;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.LocationNotFoundException;
import exceptions.not_found.SpeakerNotFoundException;
import gateways.DataManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;
import use_cases.event.LocationManager;

import java.util.ArrayList;
import java.util.Calendar;

import static enums.EventTypeEnum.PANEL_DISCUSSION;
import static enums.EventTypeEnum.TALK;

/**
 * Represents a controller responsible for event/signup management.
 */
public class EventController {
    private final String username;
    private final EventManager eventManager;
    private final AccountManager accountManager;
    private final LocationManager locationManager;

    /**
     * Creates an instance of <code>EventController</code> with given <code>DataManager</code>.
     *
     * @param dm given <code>DataManager</code> containing an <code>EventManager</code>, <code>AccountManager</code>,
     *           and <code>LocationManager</code>.
     */
    public EventController(DataManager dm) {
        this.username = dm.getUsername();
        this.eventManager = dm.getEventManager();
        this.accountManager = dm.getAccountManager();
        this.locationManager = dm.getLocationManager();
    }

    /**
     * Attempts to create a <code>Event</code> of a specific type and given information and requirements.
     *
     * @param type                       an enum representing the event type desired
     * @param topic                      desired topic
     * @param time                       desired time
     * @param location                   desired location
     * @param speakers                   desired speakers
     * @param capacity                   desired capacity
     * @param tables                     desired table count
     * @param chairs                     desired chair count
     * @param requiresInternet           desired internet requirement
     * @param requiresSoundSystem        desired sound system requirement
     * @param requiresPresentationScreen desired presentation screen requirement
     * @param vipOnly                    desired VIP restriction status
     * @throws OutOfScheduleException    when selected time is outside of schedule (9 AM to 4 PM)
     * @throws SpeakerIsBusyException    to prevent double booking of speaker
     * @throws InvalidEventTypeException when type is not a valid event type
     * @throws LocationInUseException    to prevent double booking of location
     */
    public void createEvent(EventTypeEnum type, String topic, Calendar time, String location,
                            ArrayList<String> speakers, Integer capacity, int tables, int chairs,
                            boolean requiresInternet, boolean requiresSoundSystem,
                            boolean requiresPresentationScreen, Boolean vipOnly)
            throws OutOfScheduleException, SpeakerIsBusyException, InvalidEventTypeException, LocationInUseException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers,
                capacity, tables, chairs, requiresInternet, requiresSoundSystem, requiresPresentationScreen, vipOnly);
    }

    /**
     * Attempts to reschedule a <code>Event</code>.
     *
     * @param id      ID of desired event
     * @param newTime desired time
     * @throws EventNotFoundException when the event ID is invalid (No such event)
     * @throws OutOfScheduleException when selected time is outside of schedule (9 AM to 4 PM)
     * @throws SpeakerIsBusyException to prevent double booking of speaker
     * @throws LocationInUseException to prevent double booking of location
     */
    public void rescheduleEvent(Integer id, Calendar newTime) throws OutOfScheduleException, SpeakerIsBusyException,
            LocationInUseException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }

    /**
     * Attempts to cancel a <code>Event</code>.
     *
     * @param id ID of desired event
     * @throws EventNotFoundException when id is invalid
     */
    public void cancelEvent(Integer id) throws EventNotFoundException {
        this.eventManager.cancelEvent(id);
    }

    /**
     * @return sorted list of all events
     */
    public ArrayList<String> getAllEvents() {
        return eventManager.getAllSortedEvents();
    }

    /**
     * @return sorted list of events that the current account is attending (when applicable)
     */
    public ArrayList<String> getAttendeeEvents() {
        ArrayList<Integer> selectedEventIDs = accountManager.getAttendeeEvents(username);
        return eventManager.getSortedEventsByID(selectedEventIDs);
    }

    /**
     * @return sorted list of events that the current account is talking at (when applicable)
     */
    public ArrayList<String> getSpeakerEvents() {
        return eventManager.getSpeakerSortedEvents(username);
    }

    /**
     * Check if a list of speakers exist and can be used in a type of event
     *
     * @param eventType an enum representing the event type
     * @param speakers  list of usernames of speakers
     * @throws SpeakerNotFoundException when one of the given speakers is not registered in the system
     * @throws NotEnoughSpeakersException when the given list does not have enough speakers for the event type
     */
    public void checkValidSpeaker(EventTypeEnum eventType, ArrayList<String> speakers) throws SpeakerNotFoundException,
            NotEnoughSpeakersException {
        if (eventType == TALK) {
            if (!accountManager.containsSpeaker(speakers.get(0))) throw new SpeakerNotFoundException();
        } else if (eventType == PANEL_DISCUSSION) {
            if (speakers.size() < 2) throw new NotEnoughSpeakersException();
            for (String speaker : speakers) {
                if (!accountManager.containsSpeaker(speaker))
                    throw new SpeakerNotFoundException();
            }
        }
    }

    /**
     * Signs up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     * @throws VipRestrictedException when the event is VIP only and signing up would violate this
     * @throws EventNotFoundException when the event ID is invalid (No such event)
     * @throws EventIsFullException when the event is full
     * @throws AlreadySignedUpException when the user is already signed up to the event
     */
    public void signupForEvent(Integer id) throws VipRestrictedException, EventNotFoundException,
            EventIsFullException, AlreadySignedUpException {
        if ((!accountManager.isVipAttendee(username)) && eventManager.getVipRestriction(id))
            throw new VipRestrictedException();
        else {
            eventManager.addAttendee(id, username);
            accountManager.addEventToAttend(id, username);
        }
    }

    /**
     * Cancels signing up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     * @throws EventNotFoundException when the event ID is invalid (No such event)
     * @throws AttendeeNotFoundException when there isn't an attendee signed up the event with the given username
     */
    public void cancelSignupForEvent(Integer id) throws EventNotFoundException, AttendeeNotFoundException {
        eventManager.removeAttendee(id, username);
        accountManager.removeEventToAttend(id, username);
    }

    /**
     * Returns true iff a given <code>Location</code> meets the specific requirements (of an <code>Event</code>).
     *
     * @param name                       given name of <code>Location</code>
     * @param capacity                   capacity requirement
     * @param tables                     tables requirement
     * @param chairs                     chairs requirement
     * @param requiresInternet           internet requirement
     * @param requiresSoundSystem        sound system requirement
     * @param requiresPresentationScreen presentation screen requirement
     * @return given <code>Location</code> meets the specific requirements
     */
    public boolean locationMeetsRequirements(String name, int capacity, int tables, int chairs,
                                             boolean requiresInternet, boolean requiresSoundSystem,
                                             boolean requiresPresentationScreen) {
        try {
            locationManager.checkLocationMeetsRequirements(name, capacity, tables, chairs, requiresInternet,
                    requiresSoundSystem, requiresPresentationScreen);
        } catch (RequirementMismatchException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns an <code>ArrayList</code> of String representations of <code>Locations</code> that meet the requirements
     * (of an <code>Event</code>).
     *
     * @param capacity                   capacity requirement
     * @param tables                     tables requirement
     * @param chairs                     chairs requirement
     * @param requiresInternet           internet requirement
     * @param requiresSoundSystem        sound system requirement
     * @param requiresPresentationScreen presentation screen requirement
     * @return <code>ArrayList</code> of String representations of <code>Locations</code> that meet the requirements
     * @throws NoSuggestedLocationsException when no locations meet the requirements
     */
    public ArrayList<String> getSuggestedLocations(int capacity, int tables, int chairs, boolean requiresInternet,
                                                   boolean requiresSoundSystem, boolean requiresPresentationScreen)
            throws NoSuggestedLocationsException {
        ArrayList<String> suggestedLocations = locationManager.getSuggestedLocations(capacity, tables, chairs,
                requiresInternet, requiresSoundSystem, requiresPresentationScreen);
        if (suggestedLocations.isEmpty()) throw new NoSuggestedLocationsException();
        return suggestedLocations;
    }

    /**
     * Returns true iff given name is tied to an existing <code>Location</code>.
     *
     * @param name given name of supposed <code>Location</code>
     * @return given name is tied to an existing <code>Location</code>
     */
    public boolean isExistingLocation(String name) {
        try {
            locationManager.checkExistingLocation(name);
        } catch (LocationNotFoundException e) {
            return false;
        }
        return true;
    }
}