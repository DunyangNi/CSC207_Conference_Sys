package controller;

import exceptions.NoRecipientsException;
import exceptions.EventNotFoundException;
import exceptions.UserNameNotFoundException;
import exceptions.UserNotFoundException;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;

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
     */
    public MessageAttendeeController(String username, AccountManager accountManager, ConversationManager conversationManager, EventManager eventManager) {
        super(username, accountManager, conversationManager, eventManager);
    }

    /**
     * sends a message to attendee with specified username
     * @param message message to be send
     * @param attendeeUsername attendee username
     * @throws UserNameNotFoundException if attendeeUsername is not found
     * @throws UserNotFoundException when the sender username is invalid
     */
    public void messageAttendee(String message, String attendeeUsername) throws UserNameNotFoundException, UserNotFoundException {
        if (!accountManager.containsAttendee(attendeeUsername)){
            throw new UserNameNotFoundException();
        }
        messageAccount(message, attendeeUsername);
    }

    /**
     * sends a message to all registered attendees
     * @param message message to be sent
     */
    public void messageAllAttendees(String message) throws UserNotFoundException, UserNameNotFoundException, NoRecipientsException {

        Iterator<String> attendeeUsernameIterator = this.accountManager.attendeeUsernameIterator();
        if (!attendeeUsernameIterator.hasNext())
            throw new NoRecipientsException("to message");
        while (attendeeUsernameIterator.hasNext()) {
            messageAttendee(message, attendeeUsernameIterator.next());
        }
    }

    /**
     * if the current user is a speaker, this method sends a given message to all attendees
     * at selected talks the current user is giving
     * @param selectedSpeakerTalks selected talks that the current user is speaking in
     * @param message message to be sent to attendees attending these talks
     * @throws UserNotFoundException if the sender username is invalid
     * @throws UserNameNotFoundException if the recipient username is not vaid
     * @throws NoRecipientsException if There is no one to send message
     * @throws EventNotFoundException if the event id is invalid
     */
    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) throws UserNotFoundException, UserNameNotFoundException, NoRecipientsException, EventNotFoundException {
        Set<String> selectedAttendeeUsernames = new HashSet<>();
        for (Integer id : selectedSpeakerTalks) {
            if (eventManager.isTalk(id))
                selectedAttendeeUsernames.addAll(eventManager.fetchEventAttendeeList(id));
        }
        if (selectedAttendeeUsernames.isEmpty())
            throw new NoRecipientsException("to message");
        for (String attendeeUsername : selectedAttendeeUsernames) {
            messageAttendee(message, attendeeUsername);
        }
    }
}
