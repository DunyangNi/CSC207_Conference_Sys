package controllers.message;

import exceptions.*;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import use_cases.message.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.message.ContactManager;

import java.util.ArrayList;
import java.util.Set;

/**
 * A controller that manages conversations
 */
public class ConversationController {
    private final ConversationManager cm;
    private final AccountManager am;
    private final ContactManager ctm;
    private final String username;
    Set<String> myConversations;

    /**
     * Creates an instance of <code>ConversationController </code> with given parameters.
     *
     * @param dm Datamanager containing all needed managers.
     */
    public ConversationController(DataManager dm) {
        this.am = dm.getAccountManager();
        this.cm = dm.getConversationManager();
        this.ctm = dm.getContactManager();
        this.username = dm.getUsername();
    }

    /**
     * @return True iff the user does not have any conversations
     * @throws UserNotFoundException if a non-existent user is referred to in the process
     */
    public boolean isEmpty() throws UserNotFoundException {
        myConversations = cm.getAllConversationRecipients(username);
        return myConversations.isEmpty();
    }

    /**
     * @return A list of usernames of all recipients in conversations that the user participates in.
     */
    public Set<String> getAllUserConversationRecipients() {
        return cm.getAllConversationRecipients(username);
    }

    /**
     * @param recipient name of user who you want to contact
     * @return True iff the recipient is eligible to be contacted by the user, which happens when
     * the user with username username has the user with username recipient as a contact
     */
    public boolean contactable(String recipient) {
        if (am.containsAttendee(username) && am.containsAttendee(recipient)) {
            if (!ctm.getContactList(username).contains(recipient)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to return a list of *numMessagesRequested* messages from sender.
     * Returns all messages if too many messages are requested.
     *
     * @param sender               Desired sender
     * @param numMessagesRequested Number of messages requested to view
     * @return list of messages from the sender
     * @throws NonPositiveIntegerException when numMessagesRequested is non-positive
     * @throws NoMessagesException         when there are no messages from sender
     * @throws MessageNotFoundException    when a non-existent message is looked up
     * @throws RecipientNotFoundException  when sender does not exist
     */
    public ArrayList<String> viewMessagesFrom(String sender, int numMessagesRequested) throws
            NonPositiveIntegerException,
            NoMessagesException, MessageNotFoundException, RecipientNotFoundException {
        ArrayList<String> messagesRetrieved;
        if (numMessagesRequested < 0) {
            throw new NonPositiveIntegerException();
        } else {
            String message;
            ArrayList<Integer> conversation = cm.getConversationMessages(this.username, sender);
            messagesRetrieved = new ArrayList<>();
            int numMessagesRetrieved = Math.min(numMessagesRequested, conversation.size());
            for (int i = numMessagesRetrieved; i > 0; i--) {
                message = cm.messageToString(conversation.get(conversation.size() - i)); // implemented fix
                messagesRetrieved.add(message);
            }
        }
        if (messagesRetrieved.isEmpty()) {
            throw new NoMessagesException();
        }
        return messagesRetrieved;
    }

}
