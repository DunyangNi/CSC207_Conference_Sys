package controllers.message;

import exceptions.*;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;

import java.util.ArrayList;
import java.util.Set;

public class ConversationController {
    private final ConversationManager cm;
    private final AccountManager am;
    private final ContactManager ctm;
    private final String username;
    Set<String> myConversations;

    public ConversationController(DataManager dm){
        this.am = dm.getAccountManager();
        this.cm = dm.getConversationManager();
        this.ctm = dm.getContactManager();
        this.username = dm.getUsername();
    }

    public boolean isEmpty() throws UserNotFoundException {
        myConversations = cm.getAllConversationRecipients(username);
        return myConversations.isEmpty();
    }

    public Set<String> getAllUserConversationRecipients() {
        return cm.getAllConversationRecipients(username);
    }

    public boolean contactable(String recipient){
        if (am.containsAttendee(username) && am.containsAttendee(recipient)) {
            if (!ctm.getContactList(username).contains(recipient)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> viewMessagesFrom(String recipient, int numMessagesRequested) throws NonPositiveIntegerException,
            NoMessagesException, MessageNotFoundException, RecipientNotFoundException {
        ArrayList<String> messagesRetrieved;
        if (numMessagesRequested < 0) {
            throw new NonPositiveIntegerException();
        } else {
            String message;
            ArrayList<Integer> conversation = cm.getConversationMessages(this.username, recipient);
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
