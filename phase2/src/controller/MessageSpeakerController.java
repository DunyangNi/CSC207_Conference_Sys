package controller;


import exceptions.NoRecipientsException;
import exceptions.UserNameNotFoundException;
import exceptions.UserNotFoundException;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;

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
     */
    public MessageSpeakerController(String username, AccountManager accountManager, ConversationManager conversationManager, EventManager eventManager) {
        super(username, accountManager, conversationManager, eventManager);
    }
    /**
     * sends a message to speaker with specified username
     * @param message message to be sent
     * @param speaker speaker username
     * @throws UserNameNotFoundException When the speaker name is invalid
     * @throws UserNotFoundException when the sender username is not found
     */
    public void messageSpeaker(String message, String speaker) throws UserNameNotFoundException, UserNotFoundException {
        if (!accountManager.containsSpeaker(speaker)){
            throw new UserNameNotFoundException();
        }
        messageAccount(message, speaker);
    }

    /**
     * sends a message to all registered speakers
     * @param message message to be sent
     * @throws UserNotFoundException if the sender username not found
     * @throws UserNameNotFoundException if the recipient name is invalid
     * @throws NoRecipientsException if there is no one to send
     */
    public void messageAllSpeakers(String message) throws UserNotFoundException, UserNameNotFoundException, NoRecipientsException {

        Iterator<String> speakerUsernameIterator = this.accountManager.speakerUsernameIterator();
        if (!speakerUsernameIterator.hasNext())
            throw new NoRecipientsException("to message");
        while(speakerUsernameIterator.hasNext()) {
            messageSpeaker(message, speakerUsernameIterator.next());
        }
    }
}
