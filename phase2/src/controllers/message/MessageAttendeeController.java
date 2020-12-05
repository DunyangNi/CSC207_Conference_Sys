package controllers.message;

import exceptions.*;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
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

    public void messageAttendee(String message, String attendeeUsername) throws AttendeeNotFoundException, UserNotFoundException, RecipientNotFoundException {
        if (!accountManager.containsAttendee(attendeeUsername)){
            throw new AttendeeNotFoundException();
        }
        messageAccount(message, attendeeUsername);
    }

    /**
     * sends a message to all registered attendees
     * @param message message to be sent
     */
    public void messageAllAttendees(String message) throws UserNotFoundException, AttendeeNotFoundException, RecipientNotFoundException, NoRecipientsException {

        Iterator<String> attendeeUsernameIterator = this.accountManager.attendeeUsernameIterator();
        if (!attendeeUsernameIterator.hasNext())
            throw new NoRecipientsException();
        while (attendeeUsernameIterator.hasNext()) {
            messageAttendee(message, attendeeUsernameIterator.next());
        }
    }

    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) throws UserNotFoundException, AttendeeNotFoundException, RecipientNotFoundException, NoRecipientsException, EventNotFoundException {
        Set<String> selectedAttendeeUsernames = new HashSet<>();
        for (Integer id : selectedSpeakerTalks) {
            if (eventManager.isTalk(id))
                selectedAttendeeUsernames.addAll(eventManager.fetchEventAttendeeList(id));
        }
        if (selectedAttendeeUsernames.isEmpty())
            throw new NoRecipientsException();
        for (String attendeeUsername : selectedAttendeeUsernames) {
            messageAttendee(message, attendeeUsername);
        }
    }
}
