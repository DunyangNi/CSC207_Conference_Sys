package controller;

import Throwables.EmptyListException;
import Throwables.UserNameNotFoundException;
import Throwables.UserNotFoundException;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.SignupManager;

import java.util.Iterator;
/**
 * The controller responsible for sending message to one or more Speakers.
 */
public class MessageSpeakerController extends MessageAccountController{
    /**
     * Manages generic messaging functionality for user with given username
     *
     * @param username            user username
     * @param accountManager      manages data of all accounts in program
     * @param conversationManager manages messaging functionality
     * @param eventManager        manages event data
     * @param signupManager       manages signupfunctionality
     */
    public MessageSpeakerController(String username, AccountManager accountManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager) {
        super(username, accountManager, conversationManager, eventManager, signupManager);
    }
    /**
     * sends a message to speaker with specified username
     * @param message message to be sent
     * @param speaker speaker username
     */
    public void messageSpeaker(String message, String speaker) throws UserNotFoundException, UserNameNotFoundException {
        if (!accountManager.containsSpeaker(speaker)){
            throw new UserNameNotFoundException();
        }
        messageAccount(message, speaker);
    }

    /**
     * sends a message to all registered speakers
     * @param message message to be sent
     */
    public void messageAllSpeakers(String message) throws UserNotFoundException, UserNameNotFoundException, EmptyListException {

        Iterator<String> speakerUsernameIterator = this.accountManager.speakerUsernameIterator();
        if (!speakerUsernameIterator.hasNext())
            throw new EmptyListException();
        while(speakerUsernameIterator.hasNext()) {
            messageSpeaker(message, speakerUsernameIterator.next());
        }
    }
}
