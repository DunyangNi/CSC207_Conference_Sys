package controllers.message;

import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.EventNotFoundException;
import gateways.DataManager;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Iterator;

public class MessageController {
    protected String username;
    protected AccountManager am;
    protected ConversationManager cm;
    protected EventManager em;

    /**
     * Manages generic messaging functionality for user with given username
     *
     * @param dm
     */
    public MessageController(DataManager dm) {
        this.am = dm.getAccountManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
    }

    public void messageAccount(String accountUsername, String message) throws AccountNotFoundException {
        if (!am.containsAccount(accountUsername)) {
            throw new AccountNotFoundException();
        }
        cm.sendMessage(username, accountUsername, message);
    }

//    /**
//     * sends a message to attendee with specified username
//     *
//     * @param message          message to be send
//     * @param attendeeUsername attendee username
//     */
//    public void messageAttendee(String message, String attendeeUsername) throws AccountNotFoundException {
//        if (!am.containsAttendee(attendeeUsername)) {
//            throw new AttendeeNotFoundException();
//        }
//        messageAccount(message, attendeeUsername);
//    }

    /**
     * sends a message to all registered attendees
     *
     * @param message message to be sent
     */
    public void messageAllAttendees(String message) throws AccountNotFoundException, NoRecipientsException {
        Iterator<String> attendeeUsernameIterator = this.am.attendeeUsernameIterator();
        if (!attendeeUsernameIterator.hasNext())
            throw new NoRecipientsException();
        while (attendeeUsernameIterator.hasNext()) {
            messageAccount(attendeeUsernameIterator.next(), message);
        }
    }

    /**
     * if the current user is a speaker, this method sends a given message to all attendees
     * at selected talks the current user is giving
     *
     * @param selectedTalks selected talks that the current user is speaking in
     * @param message              message to be sent to attendees attending these talks
     */
    public void messageTalkAttendees(ArrayList<Integer> selectedTalks, String message) throws AccountNotFoundException, NoRecipientsException, EventNotFoundException {
        ArrayList<String> selectedAttendees = new ArrayList<>();

        for (Integer id : selectedTalks) {
            if (em.isSpeakerOfEvent(id, this.username)) {
                selectedAttendees.addAll(em.fetchEventAttendeeList(id));
            }
        }

        if (selectedAttendees.isEmpty()) {
            throw new NoRecipientsException();
        } else {
            for (String attendee : selectedAttendees) {
                messageAccount(attendee, message);
            }
        }
    }

//    /**
//     * sends a message to speaker with specified username
//     *
//     * @param message message to be sent
//     * @param speaker speaker username
//     */
//    public void messageSpeaker(String message, String speaker) throws AccountNotFoundException {
//        if (!am.containsSpeaker(speaker)) {
//            throw new SpeakerNotFoundException();
//        }
//        messageAccount(message, speaker);
//    }

    /**
     * sends a message to all registered speakers
     *
     * @param message message to be sent
     */
    public void messageAllSpeakers(String message) throws AccountNotFoundException, NoRecipientsException {

        Iterator<String> speakerUsernameIterator = this.am.speakerUsernameIterator();
        if (!speakerUsernameIterator.hasNext())
            throw new NoRecipientsException();
        while (speakerUsernameIterator.hasNext()) {
            messageAccount(speakerUsernameIterator.next(), message);
        }
    }
}
