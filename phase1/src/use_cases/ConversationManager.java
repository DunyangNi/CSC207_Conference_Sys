package use_case;
import java.util.ArrayList;
import java.util.HashSet;
import entities.*;

/**
 * ConversationManager manages a given Conversation.
 *
 * <pre>
 * Use Case ConversationManager
 * Responsibilities:
 *      Can add a given Message to a given Conversation
 *      Can check whether Message sender and recipients match Conversation participants or not
 *      Can check whether Message to send is in reply to an existing Message in the Conversation or not
 *
 * Collaborators:
 *      Conversation, Message
 * </pre>
 */
public class ConversationManager {
    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Adds given Message to given Conversation.
     * Does nothing if Message sender and recipients
     *  do not match Conversation participants
     * Does nothing if Message is not the first in Conversation
     *  and is not in reply to a previous message in Conversation
     * @param conversation given Conversation
     * @param message given Message
     */
    public void sendMessage(Conversation conversation, Message message) {
        if (validParticipants(conversation, message) && validReply(conversation, message)) {
            // Get list of Messages from Conversation
            ArrayList<Message> messages = conversation.getMessages();
            // Add new message to Conversation
            messages.add(message);
            // Set new list of Messages to Conversation
            conversation.setMessages(messages);
        }
    }

    /**
     * Returns whether Message sender and recipients match Conversation participants or not
     * @param conversation given Conversation
     * @param message given Message
     * @return whether Message sender and recipients match Conversation participants or not
     */
    public boolean validParticipants(Conversation conversation, Message message) {
        ArrayList<Account> messageParticipants = new ArrayList<>(message.getReceiver());
        messageParticipants.add(message.getSender());
        return new HashSet<>(conversation.getMessengers()).equals(new HashSet<>(messageParticipants));
    }

    /**
     * Returns whether Message to send is in reply to an existing Message in the Conversation or not
     * @param conversation given Conversation
     * @param message given Message
     * @return whether Message to send is in reply to an existing Message in the Conversation or not
     */
    public boolean validReply(Conversation conversation, Message message) {
        Message msgToReply = message.getMsgToReply();
        ArrayList<Message> messages = conversation.getMessages();
        if (msgToReply != null) { for (Message m : messages) { if (m.equals(msgToReply)) { return true; } } }
        else { return messages.size() == 0; }
        return false;
    }

    //------------------------------------------------------------
    // Test
    //------------------------------------------------------------

    public static void main(String[] args){
        // Create two accounts
        Account a1 = new Account("johndoe", "pass123", "John", "Doe");
        Account a2 = new Account("janedoe", "pass456", "Jane", "Doe");
        Account a3 = new Account("lucydoe", "pass987", "Lucy", "Doe");
        ArrayList<Account> recipients1 = new ArrayList<>();
        ArrayList<Account> recipients2 = new ArrayList<>();
        ArrayList<Account> recipients3 = new ArrayList<>();
        recipients1.add(a2);
        recipients2.add(a1);
        recipients3.add(a3);

        // Create a message
        Message m1 = new Message(a1, recipients1, "How are you?");
        Message m2 = new Message(a2, recipients2, "I am fine.", m1);
        Message m3 = new Message(a1, recipients3, "Hi, you good?");
        Message m4 = new Message(a2, recipients2, "No, how are YOU?");
        ArrayList<Account> messengers = new ArrayList<>();
        messengers.add(a1);
        messengers.add(a2);
        Conversation c1 = new Conversation(messengers, m1);

        // Create a ConversationManager and send a Message
        ConversationManager v1 = new ConversationManager();
        v1.sendMessage(c1, m2);
        System.out.println(c1.getMessages());
        v1.sendMessage(c1, m3);
        System.out.println(c1.getMessages());
        v1.sendMessage(c1, m4);
        System.out.println(c1.getMessages());
    }
}
