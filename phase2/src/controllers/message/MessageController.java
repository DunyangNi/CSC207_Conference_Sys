package controllers.message;

import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import gateways.DataManager;
import use_cases.message.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.message.ContactManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Controls generic messaging functionality
 */
public class MessageController {
    protected String username;
    protected AccountManager am;
    protected ConversationManager cm;
    protected ContactManager ctm;
    protected EventManager em;

    /**
     * Creates an instance of <code>LocationController </code> with given parameters.
     *
     * @param dm Datamanager containing all needed managers.
     */
    public MessageController(DataManager dm) {
        this.am = dm.getAccountManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.ctm = dm.getContactManager();
        this.username = dm.getUsername();
    }

    /**
     * Attempts to send a message to the given recipient.
     *
     * @param recipient desired recipient
     * @param message   desired message
     * @throws RecipientNotFoundException when recipient does not exist
     * @throws ContactNotFoundException   when a non-existent contact is looked up
     */
    public void messageAccount(String recipient, String message) throws RecipientNotFoundException, ContactNotFoundException {
        if (am.containsAttendee(username) && am.containsAttendee(recipient)) {
            boolean canSend = ctm.getContactList(username).contains(recipient) ||
                    cm.getAllConversationRecipients(username).contains(recipient);
            if (!canSend) throw new ContactNotFoundException();
        }
        if (!am.containsAccount(recipient)) throw new RecipientNotFoundException();
        cm.sendMessage(username, recipient, message);
    }

    /**
     * sends a message to all registered attendees
     *
     * @param message message to be sent
     * @throws AccountNotFoundException when a non-existent account is looked up
     * @throws NoRecipientsException    when there are no recipients that you can send the message to
     */
    public void messageAllAttendees(String message) throws AccountNotFoundException, NoRecipientsException {
        Iterator<String> attendeeUsernameIterator = this.am.attendeeUsernameIterator();
        if (!attendeeUsernameIterator.hasNext()) throw new NoRecipientsException();
        while (attendeeUsernameIterator.hasNext()) {
            messageAccount(attendeeUsernameIterator.next(), message);
        }
    }

    /**
     * if the current user is a speaker, this method sends a given message to all attendees
     * at selected talks the current user is giving
     *
     * @param selectedEvents selected talks that the current user is speaking in
     * @param message        message to be sent to attendees attending these talks
     * @throws RecipientNotFoundException when a non-existent recipient is referred to
     * @throws ContactNotFoundException   when a non-existent contact is referred to
     * @throws NoRecipientsException      when there are no recipients that you can send the message to
     * @throws EventNotFoundException     when one of the events is non-existent
     */
    public void messageEventAttendees(ArrayList<Integer> selectedEvents, String message) throws RecipientNotFoundException,
            ContactNotFoundException, NoRecipientsException, EventNotFoundException {
        ArrayList<String> selectedAttendees = new ArrayList<>();
        for (Integer id : selectedEvents) {
            if (em.isSpeakerOfEvent(id, this.username)) selectedAttendees.addAll(em.fetchEventAttendeeList(id));
        }
        if (selectedAttendees.isEmpty()) throw new NoRecipientsException();
        else for (String attendee : selectedAttendees) {
            messageAccount(attendee, message);
        }
    }

    /**
     * sends a message to all registered speakers
     *
     * @param message message to be sent
     * @throws AccountNotFoundException when a non-existent account is referred to
     * @throws NoRecipientsException    when there are no recipients that you can send the message to
     */
    public void messageAllSpeakers(String message) throws AccountNotFoundException, NoRecipientsException {
        Iterator<String> speakerUsernameIterator = this.am.speakerUsernameIterator();
        if (!speakerUsernameIterator.hasNext()) throw new NoRecipientsException();
        while (speakerUsernameIterator.hasNext()) {
            messageAccount(speakerUsernameIterator.next(), message);
        }
    }
}
