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
    private ArrayList<String> messengers;
    private ArrayList<Integer> messages = new ArrayList<>();

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

    /**
     * Creates an event with topic and time.
     * @param users given Accounts that can send messages to this Conversation
     * @param initialMessage the first initial Message of the Conversation
     */
    public Conversation(ArrayList<String> users, int initialMessage) {
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

    // toString() is now in EventManager under conversationToString()

    public ArrayList<String> toArrayList() {
        ArrayList<String> conversation = new ArrayList<>();
        for(Message m: messages) {
            conversation.add(m.toString());
        }
        return conversation;
    }

    //------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------

    /**
     * @return participants of this Conversation
     */
    public ArrayList<String> getMessengers() { return messengers; }

    /**
     * @return messages of this Conversation
     */
    public ArrayList<Integer> getMessages() { return messages; }

    /**
     * sets participants of this Conversation
     * @param messengers new array of given Accounts
     */
    public void setMessengers(ArrayList<String> messengers) { this.messengers = messengers; }

    /**
     * sets messages of this Conversation
     * @param messages new array of given Messages
     */
    public void setMessages(ArrayList<Integer> messages) { this.messages = messages; }
}
