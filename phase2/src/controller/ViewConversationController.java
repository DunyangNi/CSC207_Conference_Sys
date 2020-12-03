package controller;

import exceptions.*;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import use_cases.ConversationManager;

import java.util.ArrayList;
import java.util.Set;

public class ViewConversationController {
    private ConversationManager conversationManager;
    private String username;
    Set<String> myConversations;

    public ViewConversationController(ConversationManager conversationManager, String username){
        this.conversationManager = conversationManager;
        this.username = username;
    }

    public boolean isEmpty() throws UserNotFoundException {
        myConversations = conversationManager.getAllUserConversationRecipients(username);
        return myConversations.isEmpty();
    }


    public ArrayList<String> viewMessagesFrom(String recipient, int numMessagesRequested) throws NonPositiveIntegerException, NoMessagesException, UserNotFoundException, MessageNotFoundException, RecipientNotFoundException {
        ArrayList<String> conversations;
        if (numMessagesRequested < 0) {
            throw new NonPositiveIntegerException();
        } else {
            String msgToPrint;
            ArrayList<Integer> messages = conversationManager.getConversationMessages(this.username, recipient);
            conversations = new ArrayList<>();
            int numMessagesRetrieved = Math.min(numMessagesRequested, messages.size());
            for (int i = numMessagesRetrieved; i > 0; i--) {
                msgToPrint = conversationManager.messageToString(messages.get(messages.size() - i)); // implemented fix
                conversations.add(msgToPrint);
            }
        }
        if (conversations.isEmpty()){throw new NoMessagesException();}
        return conversations;
    }

}
