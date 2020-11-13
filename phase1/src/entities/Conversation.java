package entities;

import java.io.Serializable;
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
public class Conversation implements Serializable {
    private ArrayList<String> participants;
    private ArrayList<Integer> messages = new ArrayList<>();

    /**
     * Creates an event with topic and time.
     * @param participants given Accounts that can send messages to this Conversation
     * @param initialMessage the first initial Message of the Conversation
     */
    public Conversation(ArrayList<String> participants, int initialMessage) {
        this.participants = participants;
        this.messages.add(initialMessage);
    }

    /**
     * Compares for equality.
     * @param obj other Conversation to compare
     * @return True if the given Conversation matches this Conversation.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Conversation) &&
                ((Conversation) obj).getParticipants().equals(this.getParticipants()) &&
                ((Conversation) obj).getMessages().equals(this.getMessages());
    }

    // toString() is now in EventManager under conversationToString()

    public ArrayList<String> toArrayList() {
        ArrayList<String> conversation = new ArrayList<>();
        for(Integer m: messages) {
            conversation.add(m.toString());
        }
        return conversation;
    }

    /**
     * @return participants of this Conversation
     */
    public ArrayList<String> getParticipants() { return participants; }

    /**
     * @return messages of this Conversation
     */
    public ArrayList<Integer> getMessages() { return messages; }

    /**
     * sets participants of this Conversation
     * @param participants new array of given Accounts
     */
    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    /**
     * sets messages of this Conversation
     * @param messages new array of given Messages
     */
    public void setMessages(ArrayList<Integer> messages) { this.messages = messages; }
}
