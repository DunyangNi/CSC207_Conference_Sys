package controllers.message;

import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import gateways.DataManager;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

import java.util.ArrayList;
import java.util.Iterator;

public class MessageController {
    protected String username;
    protected AccountManager am;
    protected ConversationManager cm;
    protected ContactManager ctm;
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
        this.ctm = dm.getContactManager();
        this.username = dm.getUsername();
    }

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
     */
    public void messageAllAttendees(String message) throws AccountNotFoundException, NoRecipientsException {
        Iterator<String> attendeeUsernameIterator = this.am.attendeeUsernameIterator();
        if (!attendeeUsernameIterator.hasNext()) throw new NoRecipientsException();
        while (attendeeUsernameIterator.hasNext()) { messageAccount(attendeeUsernameIterator.next(), message); }
    }

    /**
     * if the current user is a speaker, this method sends a given message to all attendees
     * at selected talks the current user is giving
     *
     * @param selectedEvents selected talks that the current user is speaking in
     * @param message              message to be sent to attendees attending these talks
     */
    public void messageEventAttendees(ArrayList<Integer> selectedEvents, String message) throws RecipientNotFoundException, ContactNotFoundException, NoRecipientsException, EventNotFoundException {
        ArrayList<String> selectedAttendees = new ArrayList<>();
        for (Integer id : selectedEvents) {
            if (em.isSpeakerOfEvent(id, this.username)) selectedAttendees.addAll(em.fetchEventAttendeeList(id));
        }
        if (selectedAttendees.isEmpty()) throw new NoRecipientsException();
        else for (String attendee : selectedAttendees) { messageAccount(attendee, message); }
    }

    /**
     * sends a message to all registered speakers
     *
     * @param message message to be sent
     */
    public void messageAllSpeakers(String message) throws AccountNotFoundException, NoRecipientsException {
        Iterator<String> speakerUsernameIterator = this.am.speakerUsernameIterator();
        if (!speakerUsernameIterator.hasNext()) throw new NoRecipientsException();
        while (speakerUsernameIterator.hasNext()) { messageAccount(speakerUsernameIterator.next(), message); }
    }
}
