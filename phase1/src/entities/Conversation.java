package entities;

import java.util.ArrayList;

/**
 * Conversation represents a linked system of Messages exchanged between two Accounts.
 *
 * <pre>
 * Entity Conversation
 * Responsibilities:
 *      Can get and set messengers of the Conversation
 *      Can get and set messages within the Conversation
 *
 * Collaborators:
 *      Message, Account
 * </pre>
 */
public class Conversation {
    private ArrayList<Account> messengers;
    private ArrayList<Message> messages = new ArrayList<>();

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Creates an event with topic and time.
     * @param users given Accounts that can send messages to this Conversation
     * @param initialMessage the first initial Message of the Conversation
     */
    public Conversation(ArrayList<Account> users, Message initialMessage) {
        this.messengers = users;
        this.messages.add(initialMessage);
    }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    /**
     * Compares for equality.
     * @param obj other Conversation to compare
     * @return True if the given Conversation matches this Conversation.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Conversation) &&
                ((Conversation) obj).getMessengers().equals(this.getMessengers()) &&
                ((Conversation) obj).getMessages().equals(this.getMessages());
    }

    /**
     * Returns Conversation info
     * @return Conversation info
     */
    @Override
    public String toString() {
        StringBuilder str_write = new StringBuilder("[Conversation] ( ");
        for (Account c : this.messengers) {
            str_write.append(c.getUsername());
            str_write.append(" ");
        }
        str_write.append(") : ");
        for (Message m : messages) {
            str_write.append(m.toString());
            str_write.append(" ; ");
        }
        return str_write.toString();
    }

    //------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------

    /**
     * @return participants of this Conversation
     */
    public ArrayList<Account> getMessengers() { return messengers; }

    /**
     * @return messages of this Conversation
     */
    public ArrayList<Message> getMessages() { return messages; }

    /**
     * sets participants of this Conversation
     * @param messengers new array of given Accounts
     */
    public void setMessengers(ArrayList<Account> messengers) { this.messengers = messengers; }

    /**
     * sets messages of this Conversation
     * @param messages new array of given Messages
     */
    public void setMessages(ArrayList<Message> messages) { this.messages = messages; }
}
