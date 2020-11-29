package controller;

import Throwables.*;
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
        if (myConversations.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * displays the numMessagesRequested most recent messages with the recipient
     * @param recipient person whose conversation with the user is being requested
     * @param numMessagesRequested an upper bound for the number of past messages requested to be seen
     */
    public ArrayList<String> viewMessagesFrom(String recipient, int numMessagesRequested) throws InvalidIntegerException, UserNotFoundException, UserNameNotFoundException, MessageNotFound, EmptyListException {
        ArrayList<String> conversations;
        if (numMessagesRequested < 0) {
            throw new InvalidIntegerException();
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
        if (conversations.isEmpty()){throw new EmptyListException();}
        return conversations;
    }

}
