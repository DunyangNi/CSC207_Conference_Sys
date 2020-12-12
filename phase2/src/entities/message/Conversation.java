package entities.message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a linked system of Messages exchanged between two Accounts.
 */
public class Conversation implements Serializable {
    private ArrayList<String> participants;
    private ArrayList<Integer> messages = new ArrayList<>();

    /**
     * Creates an instance of <code>Conversation</code> based on <code>ArrayList</code>
     * of participant usernames and initial <code>Message</code> ID.
     *
     * @param participants given Account usernames that can send messages to this <code>Conversation</code>
     * @param initialMessage the first <code>Message</code> ID of the <code>Conversation</code>
     */
    public Conversation(ArrayList<String> participants, int initialMessage) {
        this.participants = participants;
        this.messages.add(initialMessage);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Conversation</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Conversation</code>.
     *
     * @param obj other <code>Object</code> presumed <code>Conversation</code> to compare
     * @return the given <code>Object</code> matches this <code>Conversation</code>
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Conversation) &&
                ((Conversation) obj).getParticipants().equals(this.getParticipants()) &&
                ((Conversation) obj).getMessages().equals(this.getMessages());
    }

    /**
     * @return participant usernames of this <code>Conversation</code>
     */
    public ArrayList<String> getParticipants() { return participants; }

    /**
     * @return a <code>ArrayList</code> of <code>Message</code> IDs of this <code>Conversation</code>
     */
    public ArrayList<Integer> getMessages() { return messages; }

    /**
     * Sets the participant usernames of this <code>Conversation</code>.
     *
     * @param participants new <code>ArrayList</code> of given <code>Account</code> usernames
     */
    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    /**
     * Sets <code>ArrayList</code> messages of this <code>Conversation</code>
     *
     * @param messages new <code>ArrayList</code> of given <code>Message</code> IDs
     */
    public void setMessages(ArrayList<Integer> messages) { this.messages = messages; }
}
