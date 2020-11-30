package controller;
//To be deleted
import use_cases.ConversationManager;
import use_cases.AccountManager;
import use_cases.EventManager;
import presenter.*;

import java.util.*;

public class MessageController {
    protected String username;
    protected AccountManager accountManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;
    protected Presenter presenter = new TextPresenter();

    /**
     * Manages generic messaging functionality for user with given username
     * @param username user username
     * @param accountManager manages data of all accounts in program
     * @param conversationManager manages messaging functionality
     * @param eventManager manages event data
     */
    public MessageController(String username, AccountManager accountManager, ConversationManager conversationManager,
                             EventManager eventManager){
        this.username = username;
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
    }

    /**
     * sends a message to account with specified username
     * @param message the message to be sent
     * @param account recipient account username
     */
    public void messageAccount(String message, String account) {
        try{
            conversationManager.sendMessage(this.username, account, message);
        }
        catch(Exception e){
            this.presenter.displayPrompt(e.toString());
            this.presenter.displayPrompt("Something went wrong with messageAccount while messaging. Try again.");
        }
    }

    /**
     * sends a message to speaker with specified username
     * @param message message to be sent
     * @param speaker speaker username
     */
    public void messageSpeaker(String message, String speaker) {
        if (accountManager.containsSpeaker(speaker)){
            messageAccount(message, speaker);
        }
        else {
            this.presenter.displayPrompt("This speaker does not exist.");
        }

    }

    /**
     * sends a message to attendee with specified username
     * @param message message to be send
     * @param attendeeUsername attendee username
     */
    public void messageAttendee(String message, String attendeeUsername) {
        if (accountManager.containsAttendee(attendeeUsername)){
            messageAccount(message, attendeeUsername);
        }
        else {
            this.presenter.displayPrompt("This attendee does not exist.");
        }
    }

    /**
     * sends a message to all registered speakers
     * @param message message to be sent
     */
    public void messageAllSpeakers(String message) {
        try {
            Iterator<String> speakerUsernameIterator = this.accountManager.speakerUsernameIterator();
            if (!speakerUsernameIterator.hasNext())
                this.presenter.displayPrompt("There are no speakers to message."); // f
            while(speakerUsernameIterator.hasNext()) {
                messageSpeaker(message, speakerUsernameIterator.next());
            }
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
            this.presenter.displayPrompt("Something went wrong. Please try again.");
        }
    }

    /**
     * sends a message to all registered attendees
     * @param message message to be sent
     */
    public void messageAllAttendees(String message) {
        try {
            Iterator<String> attendeeUsernameIterator = this.accountManager.attendeeUsernameIterator();
            if (!attendeeUsernameIterator.hasNext())
                this.presenter.displayPrompt("There are no attendees to message."); // f
            while (attendeeUsernameIterator.hasNext()) {
                messageAttendee(message, attendeeUsernameIterator.next());
            }
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString());
            this.presenter.displayPrompt("Something went wrong. Please try again.");
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
                if (eventManager.isTalk(id))
                    selectedAttendeeUsernames.addAll(eventManager.fetchEventAttendeeList(id));
            }
            if (selectedAttendeeUsernames.isEmpty())
                this.presenter.displayPrompt("There are no attendees to message."); // f
            for (String attendeeUsername : selectedAttendeeUsernames) {
                messageAttendee(message, attendeeUsername);
            }
        }
        catch(Exception e) {
            this.presenter.displayPrompt(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
        }
    }
}
