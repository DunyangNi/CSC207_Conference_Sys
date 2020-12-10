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

public class EventController {
    private final String username;
    private final EventManager eventManager;
    private final AccountManager accountManager;
    private final LocationManager locationManager;

    public EventController(DataManager dm) {
        this.username = dm.getUsername();
        this.eventManager = dm.getEventManager();
        this.accountManager = dm.getAccountManager();
        this.locationManager = dm.getLocationManager();
    }

    public void createEvent(EventTypeEnum type, String topic, Calendar time, String location, ArrayList<String> speakers, Integer capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen, Boolean vipOnly)
            throws OutOfScheduleException, SpeakerIsBusyException, InvalidEventTypeException, LocationInUseException {
        eventManager.addNewEvent(type, topic, time, location, this.username, speakers, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen, vipOnly);
    }

    public void rescheduleEvent(Integer id, Calendar newTime) throws OutOfScheduleException, SpeakerIsBusyException, LocationInUseException, EventNotFoundException {
        this.eventManager.changeTime(id, newTime);
    }

    public void cancelEvent(Integer id) throws EventNotFoundException { this.eventManager.cancelEvent(id); }

    public ArrayList<String> getAllEvents() {
        return eventManager.getAllSortedEvents();
    }

    public ArrayList<String> getAttendeeEvents() {
        ArrayList<Integer> selectedEventIDs = accountManager.getAttendeeEvents(username);
        return eventManager.getSortedEventsByID(selectedEventIDs);
    }

    public ArrayList<String> getSpeakerEvents() { return eventManager.getSpeakerSortedEvents(username); }

    public void checkValidSpeaker(EventTypeEnum eventType, ArrayList<String> speakers) throws SpeakerNotFoundException, NotEnoughSpeakersException {
        if (eventType == TALK) {
            if (!accountManager.containsSpeaker(speakers.get(0))) throw new SpeakerNotFoundException();
        } else if (eventType == PANEL_DISCUSSION) {
            if (speakers.size() < 2) throw new NotEnoughSpeakersException();
            for (String speaker : speakers) { if (!accountManager.containsSpeaker(speaker)) throw new SpeakerNotFoundException(); }
        }
    }

    /**
     * Signs up user for a <code>Event</code> of a given ID.
     *
     * @param id given ID of <code>Event</code>
     */
    public void signupForEvent(Integer id) throws VipRestrictedException, EventNotFoundException,
            EventIsFullException, AlreadySignedUpException {
        if ((!accountManager.getVipStatus(username)) && eventManager.getVipRestriction(id)) { throw new VipRestrictedException(); }
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

    public boolean locationMeetsRequirements(String name, int capacity, int tables, int chairs, boolean hasInternet, boolean hasSoundSystem, boolean hasPresentationScreen) {
        try { locationManager.checkLocationMeetsRequirements(name, capacity, tables, chairs, hasInternet, hasSoundSystem, hasPresentationScreen); }
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