package controllers.message;


import exceptions.NoRecipientsException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.SpeakerNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateway.DataManager;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

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
    public MessageSpeakerController(DataManager dm) {
        super(dm);
    }

    public void messageSpeaker(String message, String speaker) throws SpeakerNotFoundException, RecipientNotFoundException, UserNotFoundException {
        if (!accountManager.containsSpeaker(speaker)){
            throw new SpeakerNotFoundException();
        }
        messageAccount(message, speaker);
    }

    public void messageAllSpeakers(String message) throws UserNotFoundException, SpeakerNotFoundException, RecipientNotFoundException, NoRecipientsException {

        Iterator<String> speakerUsernameIterator = this.accountManager.speakerUsernameIterator();
        if (!speakerUsernameIterator.hasNext())
            throw new NoRecipientsException();
        while(speakerUsernameIterator.hasNext()) {
            messageSpeaker(message, speakerUsernameIterator.next());
        }
    }
}
