package controller;

import Throwables.EmptyListException;
import Throwables.EventNotFoundException;
import Throwables.UserNameNotFoundException;
import Throwables.UserNotFoundException;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.SignupManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * The controller responsible for sending message to one ore more Attendees.
 */
public class MessageAttendeeController extends MessageAccountController{
    /**
     * Manages generic messaging functionality for user with given username
     *
     * @param username            user username
     * @param accountManager      manages data of all accounts in program
     * @param conversationManager manages messaging functionality
     * @param eventManager        manages event data
     * @param signupManager       manages signupfunctionality
     */
    public MessageAttendeeController(String username, AccountManager accountManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager) {
        super(username, accountManager, conversationManager, eventManager, signupManager);
    }

    /**
     * sends a message to attendee with specified username
     * @param message message to be send
     * @param attendeeUsername attendee username
     */
    public void messageAttendee(String message, String attendeeUsername) throws UserNotFoundException, UserNameNotFoundException {
        if (!accountManager.containsAttendee(attendeeUsername)){
            throw new UserNameNotFoundException();
        }
        messageAccount(message, attendeeUsername);
    }

    /**
     * sends a message to all registered attendees
     * @param message message to be sent
     */
    public void messageAllAttendees(String message) throws UserNotFoundException, UserNameNotFoundException, EmptyListException {

        Iterator<String> attendeeUsernameIterator = this.accountManager.attendeeUsernameIterator();
        if (!attendeeUsernameIterator.hasNext())
            throw new EmptyListException();
        while (attendeeUsernameIterator.hasNext()) {
            messageAttendee(message, attendeeUsernameIterator.next());
        }
    }

    /**
     * if the current user is a speaker, this method sends a given message to all attendees
     * at selected talks the current user is giving
     * @param selectedSpeakerTalks selected talks that the current user is speaking in
     * @param message message to be sent to attendees attending these talks
     */
    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) throws UserNotFoundException, UserNameNotFoundException, EmptyListException, EventNotFoundException {
        Set<String> selectedAttendeeUsernames = new HashSet<>();
        for (Integer id : selectedSpeakerTalks) {
            if (eventManager.isTalk(id))
                selectedAttendeeUsernames.addAll(signupManager.fetchTalkAttendeeList(id));
        }
        if (selectedAttendeeUsernames.isEmpty())
            throw new EmptyListException();
        for (String attendeeUsername : selectedAttendeeUsernames) {
            messageAttendee(message, attendeeUsername);
        }
    }
}
