package controllers.message;

import exceptions.*;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import use_cases.ConversationManager;

import java.util.ArrayList;
import java.util.Set;

public class ConversationController {
    private final ConversationManager cm;
    private final String username;
    Set<String> myConversations;

    public ConversationController(DataManager dm){
        this.cm = dm.getConversationManager();
        this.username = dm.getUsername();
    }

    public boolean isEmpty() throws UserNotFoundException {
        myConversations = cm.getAllUserConversationRecipients(username);
        return myConversations.isEmpty();
    }

    public Set<String> getAllUserConversationRecipients() throws UserNotFoundException {
        return cm.getAllUserConversationRecipients(username);
    }


    public ArrayList<String> viewMessagesFrom(String recipient, int numMessagesRequested) throws NonPositiveIntegerException,
            NoMessagesException, UserNotFoundException, MessageNotFoundException, RecipientNotFoundException {
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
