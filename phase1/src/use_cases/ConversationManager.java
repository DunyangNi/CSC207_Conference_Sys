package use_cases;

import java.io.Serializable;
import java.util.*;
import entities.*;
import Throwables.*;

/**
 * ConversationManager manages a given Conversation between a sender Account and a recipient Account.
 *
 * <pre>
 * Use Case ConversationManager
 * Responsibilities:
 *      Stores a HashMap of every Account's Hashmap of Conversations with a recipient
 *      Stores a Hashmap of every Message sent by its ID
 *      Returns an ArrayList of all Messages in a Conversation by its ID
 *      Returns a Set of all existing recipients of a given Account
 *      Can send a message from a sender Account to a recipient Account
 *      Can add a given Message to a given Conversation
 *
 * Collaborators:
 *      Conversation, Message
 * </pre>
 */
public class ConversationManager implements Serializable {
    private HashMap<String, HashMap<String, Conversation>> conversations = new HashMap<>();
    private HashMap<Integer, Message> messages = new HashMap<>();

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    public String messageToString(Integer id) throws ObjectNotFoundException{
        if (!this.messages.containsKey(id)) { throw new ObjectNotFoundException(); }
        // Obtain Message information
        Message selectedMsg = messages.get(id);
        Message msgToReply = messages.get(selectedMsg.getMsgToReply());
        String sender = selectedMsg.getSender();
        String content = selectedMsg.getContent();
        // Construct the String representation
        String firstSegment = "[Message " + id + "] (" + sender + ") : " + content + " [ReplyTo] (";
        StringBuilder str_write = new StringBuilder(firstSegment);
        if (msgToReply == null) { str_write.append("None)"); }
        else {
            str_write.append(msgToReply.getSender());
            str_write.append(") : ");
            String replyContent = msgToReply.getContent();
            str_write.append(replyContent, 0, Math.min(replyContent.length(), 10));
            str_write.append(replyContent.length() <= 10 ? "" : "...");
        }
        return str_write.toString();
    }

    // (NEW!)
    public void addAccountKey(String username) { conversations.put(username, new HashMap<>()); }

    public ArrayList<Integer> getConversationMessages(String user, String recipient) throws ObjectNotFoundException{
        // Checking username and recipient are valid Accounts
        if (!conversations.containsKey(user) || !conversations.containsKey(recipient)) { throw new ObjectNotFoundException(); }
        return conversations.get(user).get(recipient).getMessages();
    }

    /**
     * (UPDATED!) Returns all Accounts who have had Conversations with the given Account.
     *  If given Account has no Conversations, returns the empty set.
     * @param user username of given Account
     * @return Set of usernames associated with recipient Accounts
     */
    public Set<String> getAllUserConversationRecipients(String user) throws ObjectNotFoundException{
        if(!conversations.containsKey(user)) {
            throw new ObjectNotFoundException();
        }
        Set<String> recipients = this.conversations.get(user).keySet();
        return recipients.isEmpty() ? Collections.emptySet() : recipients;
    }

    /**
     * (UPDATED!) Sends a message from a sender Account to a recipient Account
     * @param sender given sender Account
     * @param recipient given recipient Account
     * @param message given String content for message
     */
    public void sendMessage(String sender, String recipient, String message) throws ObjectNotFoundException {
        if (!conversations.containsKey(sender) || !conversations.containsKey(recipient)) { throw new ObjectNotFoundException(); }
        HashMap<String, Conversation> senderConversations = conversations.get(sender);
        HashMap<String, Conversation> recipientConversations = conversations.get(recipient);
        Conversation givenConversation = senderConversations.get(recipient);
        Message newMessage = new Message(sender, recipient, message);
        // Create a new Conversation if an existing one isn't found
        if (givenConversation == null) {
            ArrayList<String> participants = new ArrayList<>(Arrays.asList(sender, recipient));
            givenConversation = new Conversation(participants, newMessage.getId());
        }
        else { addMessageToConversation(givenConversation, newMessage); }
        senderConversations.put(recipient, givenConversation);
        recipientConversations.put(sender, givenConversation);
    }

    /**
     * (UPDATED!) (Helper) Adds given Message to given Conversation, assigning msgToReply if needed.
     * @param conversation given Conversation
     * @param message given Message
     */
    private void addMessageToConversation(Conversation conversation, Message message) {
        ArrayList<Integer> existingMessages = conversation.getMessages();
        if (existingMessages.size() != 0) { message.setMsgToReply(existingMessages.get(existingMessages.size()-1)); }
        conversation.getMessages().add(message.getId());
    }
}