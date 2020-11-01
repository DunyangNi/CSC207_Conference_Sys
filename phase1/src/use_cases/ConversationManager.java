package use_cases;

import java.util.ArrayList;
import entities.Conversation;
import entities.Message;
import entities.Account;

/**
 * ConversationManager manages a given Conversation.
 *
 * <pre>
 * Use Case ConversationManager
 * Responsibilities:
 *      Can add a given Message to a given Conversation
 *
 * Collaborators:
 *      Conversation, Message
 *
 * Representation Invariants:
 *      Sender and recipients are all valid participants in the
 *          Conversation.
 *      Each message is in reply to a previous message, except
 *          the first one.
 * </pre>
 */
public class ConversationManager {
    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Adds given Message to given Conversation.
     *
     * @param conversation given Conversation
     * @param message given Message
     */
    public void sendMessage(Conversation conversation, Message message) {
        // Get list of Messages from Conversation
        ArrayList<Message> messages = conversation.getMessages();
        // Add new message to Conversation
        messages.add(message);
        // Set new list of Messages to Conversation
        conversation.setMessages(messages);
    }

    //------------------------------------------------------------
    // Test
    //------------------------------------------------------------

    public static void main(String[] args){
        // Create two accounts
        Account a1 = new Account("johndoe", "pass123", "John", "Doe");
        Account a2 = new Account("janedoe", "pass456", "Jane", "Doe");
        ArrayList<Account> recipients1 = new ArrayList<>();
        ArrayList<Account> recipients2 = new ArrayList<>();
        recipients1.add(a2);
        recipients2.add(a1);

        // Create a message
        Message m1 = new Message(a1, recipients1, "How are you?");
        Message m2 = new Message(a2, recipients2, "I am fine.", m1);
        ArrayList<Account> messengers = new ArrayList<>();
        messengers.add(a1);
        messengers.add(a2);
        Conversation c1 = new Conversation(messengers, m1);

        // Create a ConversationManager and send a Message
        ConversationManager v1 = new ConversationManager();
        v1.sendMessage(c1, m2);
        System.out.println(c1.getMessages());
    }
}
