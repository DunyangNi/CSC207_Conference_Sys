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
 * Represents a controller responsible for event/signup management
 *
 * Fields:
 * username: The username of the user of the program
 * EventManager: Stores event information
 * AccountManager: Stores account information
 * LocationManager: Stores location information
 */
public class EventController {
    private final String username;
    private final EventManager eventManager;
    private final AccountManager accountManager;
    private final LocationManager locationManager;

    /**
     * Creates an instance of <code>EventController </code> with given parameters.
     *
     * @param dm Datamanager containing all needed managers.
     */

    public EventController(DataManager dm) {
        this.username = dm.getUsername();
        this.eventManager = dm.getEventManager();
        this.accountManager = dm.getAccountManager();
        this.locationManager = dm.getLocationManager();
    }

    /**
     * Attempts to create an event.
     *
     * @param type an enum representing the event type desired
     * @param topic desired topic
     * @param time desired time
     * @param location desired location
     * @param speakers desired speakers
     * @param capacity desired capacity
     * @param tables desired table count
     * @param chairs desired chair count
     * @param hasInternet desired internet requirement
     * @param hasSoundSystem desired sound system requirement
     * @param hasPresentationScreen desired presentation screen requirement
     * @param vipOnly desired VIP restriction status
     *
     * @throws OutOfScheduleException when time is invalid
     * @throws SpeakerIsBusyException to prevent double booking of speaker
     * @throws InvalidEventTypeException when type is not a valid event type
     * @throws LocationInUseException to prevent double booking of location
     */

    public void createEvent(EventTypeEnum type, String topic, Calendar time, String location,
                            ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet,
                            boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly)
            throws OutOfScheduleException, SpeakerIsBusyException, InvalidEventTypeException, LocationInUseException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers,
                capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
    }

    /**
     * Attempts to reschedule an event.
     *
     * @param id ID of desired event
     * @param newTime desired time
     *
     * @throws OutOfScheduleException when time is invalid
     * @throws SpeakerIsBusyException to prevent double booking of speaker
     * @throws LocationInUseException to prevent double booking of location
     */

    public void rescheduleEvent(Integer id, Calendar newTime) throws OutOfScheduleException, SpeakerIsBusyException,
            LocationInUseException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }

    /**
     * Attempts to cancel an event.
     *
     * @param id ID of desired event
     *
     * @throws EventNotFoundException when id is invalid
     */

    public void cancelEvent(Integer id) throws EventNotFoundException { this.eventManager.cancelEvent(id); }

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

    public ArrayList<String> getSpeakerEvents() { return eventManager.getSpeakerSortedEvents(username); }

    /**
     * Check if a list of speakers exist and can be used in a type of event
     * @param eventType an enum representing the event type
     * @param speakers list of usernames of speakers
     */

    public void checkValidSpeaker(EventTypeEnum eventType, ArrayList<String> speakers) throws SpeakerNotFoundException,
            NotEnoughSpeakersException {
        if (eventType == TALK) {
            if (!accountManager.containsSpeaker(speakers.get(0))) throw new SpeakerNotFoundException();
        } else if (eventType == PANEL_DISCUSSION) {
            if (speakers.size() < 2) throw new NotEnoughSpeakersException();
            for (String speaker : speakers) { if (!accountManager.containsSpeaker(speaker))
                throw new SpeakerNotFoundException(); }
        }
    }

    /**
     * Signs up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void signupForEvent(Integer id) throws VipRestrictedException, EventNotFoundException,
            EventIsFullException, AlreadySignedUpException {
        if ((!accountManager.isVipAttendee(username)) && eventManager.getVipRestriction(id)) throw new VipRestrictedException();
        else {
            eventManager.addAttendee(id, username);
            accountManager.addEventToAttend(id, username);
        }
    }

    /**
     * Cancels signing up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void cancelSignupForEvent(Integer id) throws EventNotFoundException, AttendeeNotFoundException {
        eventManager.removeAttendee(id, username);
        accountManager.removeEventToAttend(id, username);
    }


    public boolean locationMeetsRequirements(String name, int capacity, int tables, int chairs, boolean hasInternet,
                                             boolean hasSoundSystem, boolean hasPresentationScreen) {
        try { locationManager.checkLocationMeetsRequirements(name, capacity, tables, chairs, hasInternet,
                hasSoundSystem, hasPresentationScreen); }
        catch (RequirementMismatchException e) { return false; }
        return true;
    }

    public ArrayList<String> getSuggestedLocations(int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) throws NoSuggestedLocationsException {
        ArrayList<String> suggestedLocations = locationManager.getSuggestedLocations(capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen);
        if (suggestedLocations.isEmpty()) throw new NoSuggestedLocationsException();
        return suggestedLocations;
    }

    public boolean isExistingLocation(String name) {
        try { locationManager.checkExistingLocation(name); }
        catch (LocationNotFoundException e) { return false; }
        return true;
    }
}