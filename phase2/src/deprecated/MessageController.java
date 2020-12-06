package deprecated;
//To be deleted
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;

import java.util.*;

public class MessageController {
    protected String username;
    protected AccountManager am;
    protected ConversationManager cm;
    protected EventManager em;
    protected OldPresenter oldPresenter = new TextOldPresenter();
    /**
     * Manages generic messaging functionality for user with given username
     * @param username user username
     * @param am manages data of all accounts in program
     * @param cm manages messaging functionality
     * @param em manages event data
     */
    public MessageController(String username, AccountManager am, ConversationManager cm,
                             EventManager em){
        this.username = username;
        this.am = am;
        this.cm = cm;
        this.em = em;
    }

    public void messageAccount(String message, String account) throws UserNotFoundException, RecipientNotFoundException {
        cm.sendMessage(this.username, account, message);
    }

    /**
     * sends a message to speaker with specified username
     * @param message message to be sent
     * @param speaker speaker username
     */
    public void messageSpeaker(String message, String speaker) throws UserNotFoundException, RecipientNotFoundException {
        cm.sendMessage(this.username, speaker, message);
    }

    /**
     * sends a message to attendee with specified username
     * @param message message to be send
     * @param attendeeUsername attendee username
     */
    public void messageAttendee(String message, String attendeeUsername) throws UserNotFoundException, RecipientNotFoundException {
        cm.sendMessage(this.username, attendeeUsername, message);
    }

    /**
     * sends a message to all registered speakers
     * @param message message to be sent
     */
    public void messageAllSpeakers(String message) {
        try {
            Iterator<String> speakerUsernameIterator = this.am.speakerUsernameIterator();
            if (!speakerUsernameIterator.hasNext())
                this.oldPresenter.displayPrompt("There are no speakers to message."); // f
            while(speakerUsernameIterator.hasNext()) {
                messageSpeaker(message, speakerUsernameIterator.next());
            }
        }
        catch(Exception e) {
            this.oldPresenter.displayPrompt(e.toString());
            this.oldPresenter.displayPrompt("Something went wrong. Please try again.");
        }
    }

    /**
     * sends a message to all registered attendees
     * @param message message to be sent
     */
    public void messageAllAttendees(String message) {
        try {
            Iterator<String> attendeeUsernameIterator = this.am.attendeeUsernameIterator();
            if (!attendeeUsernameIterator.hasNext())
                this.oldPresenter.displayPrompt("There are no attendees to message."); // f
            while (attendeeUsernameIterator.hasNext()) {
                messageAttendee(message, attendeeUsernameIterator.next());
            }
        }
        catch(Exception e) {
            this.oldPresenter.displayPrompt(e.toString());
            this.oldPresenter.displayPrompt("Something went wrong. Please try again.");
        }
    }

    /**
     * if the current user is a speaker, this method sends a given message to all attendees
     * at selected talks the current user is giving
     * @param selectedSpeakerTalks selected talks that the current user is speaking in
     * @param message message to be sent to attendees attending these talks
     */
    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) {
        try {
            Set<String> selectedAttendeeUsernames = new HashSet<>();
            for (Integer id : selectedSpeakerTalks) {
                if (em.isTalk(id))
                    selectedAttendeeUsernames.addAll(em.fetchEventAttendeeList(id));
            }
            if (selectedAttendeeUsernames.isEmpty())
                this.oldPresenter.displayPrompt("There are no attendees to message."); // f
            for (String attendeeUsername : selectedAttendeeUsernames) {
                messageAttendee(message, attendeeUsername);
            }
        }
        catch(Exception e) {
            this.oldPresenter.displayPrompt(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
        }
    }
}
