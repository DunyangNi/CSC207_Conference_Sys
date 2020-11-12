package use_cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import entities.*;

/**
 * ConversationManager manages a given Conversation between a sender Account and a recipient Account.
 *
 * <pre>
 * Use Case ConversationManager
 * Responsibilities:
 *      Can send a message from a sender Account to a recipient Account
 *      Can add a given Message to a given Conversation
 *      Can check whether Message recipient is in sender's friends list
 *
 * Collaborators:
 *      Conversation, Message, Account
 * </pre>
 */
public class ConversationManager {
    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * (NEW!) Returns the string representation of a Conversation between two given Accounts.
     *  If no such conversation exists, returns the empty string.
     * @param current given Account to search for Conversation
     * @param recipient given recipient Account of a Conversation
     * @return string representation of selected Conversation.
     */
    public static String getConversationString(Account current, Account recipient) {
        Conversation selectConversation = current.getConversations().get(recipient.getUsername());
        if (selectConversation != null)
            return selectConversation.toString();
        else
            return "";
    }

    public static ArrayList<String> getConversationArrayList(Account current, Account recipient) {
        Conversation selectConversation = current.getConversations().get(recipient.getUsername());
        if(selectConversation != null) {
            return selectConversation.toArrayList();
        }
        else {
            return new ArrayList<String>();
        }
    }

    /**
     * Sends a message from a sender Account to a recipient Account
     * @param sender given sender Account
     * @param recipient given recipient Account
     * @param message given String content for message
     */
    public static void sendMessage(Account sender, Account recipient, String message) {
        if (validRecipient(sender, recipient)) {
            HashMap<String, Conversation> senderConversations = sender.getConversations();
            HashMap<String, Conversation> recipientConversations = recipient.getConversations();
            Conversation givenConversation = senderConversations.get(recipient.getUsername());
            Message newMessage = new Message(sender, recipient, message);
            // if we don't assume a Conversation is automatically instantiated for all friends.
            if (givenConversation == null) {
                ArrayList<Account> participants = new ArrayList<>(Arrays.asList(sender, recipient));
                givenConversation = new Conversation(participants, newMessage);
                senderConversations.put(recipient.getUsername(), givenConversation);
                recipientConversations.put(sender.getUsername(), givenConversation);
            }
            else {
                addMessageToConversation(givenConversation, newMessage);
            }
            sender.setConversations(senderConversations);
            recipient.setConversations(recipientConversations);
        }
    }

    /**
     * Adds given Message to given Conversation, assigning msgToReply if needed.
     * @param conversation given Conversation
     * @param message given Message
     */
    public static void addMessageToConversation(Conversation conversation, Message message) {
        // Get list of Messages from Conversation
        ArrayList<Message> existingMessages = conversation.getMessages();
        // Assign message to reply to
        if (existingMessages.size() != 0) {
            Message msgToReply = existingMessages.get(existingMessages.size()-1);
            message.setMsgToReply(msgToReply);
        }
        // Add new message to Conversation
        existingMessages.add(message);
        // Set new list of Messages to Conversation
        conversation.setMessages(existingMessages);
    }

    /**
     * (TO BE REVISED!) Returns whether two Accounts are in each other's friend list or not.
     * @param a1 given Account 1
     * @param a2 given Account 2
     * @return whether a1 and a2 are in each other's friend list or not.
     */
    public static boolean validRecipient(Account a1, Account a2) {
        // return (a1.getFriendsList().containsValue(a2) && a2.getFriendsList().containsValue(a1));
        return true; // for a later fix/extension
    }
}
