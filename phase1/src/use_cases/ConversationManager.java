package use_cases;

import java.io.Serializable;
import java.util.*;
import entities.*;
import Throwables.*;

/**
 * Represents the entire system of Conversations and Messages.
 */
public class ConversationManager implements Serializable {
    private final HashMap<String, HashMap<String, Conversation>> conversations = new HashMap<>();
    private final HashMap<Integer, Message> messages = new HashMap<>();
    private int assignMessageID;

    /**
     * Attempts to represent a <code>Message</code> of a given id
     * as a String containing the sender and the content.
     *
     * @param id given id
     * @return sender and content
     * @throws ObjectNotFoundException upon <code>Message</code> not being found.
     */
    public String messageToString(Integer id) throws MessageNotFound {
        if (!this.messages.containsKey(id)) {
            throw new MessageNotFound();
        }
        Message selectedMsg = messages.get(id);
        String sender = selectedMsg.getSender();
        String content = selectedMsg.getContent();
        return "(" + sender + ") : " + content;
    }

    /**
     * Attempts to return an <code>ArrayList</code> of <code>Message</code> IDs for a given sender and recipient
     * of an associated <code>Conversation</code>.
     *
     * @param sender given sender username
     * @param recipient given recipient username
     * @return an <code>ArrayList</code> of integers
     * @throws UserNotFoundException throw when the sender username is invalid
     * @throws UserNameNotFoundException throw when the recipient username is invalid
     */
    public ArrayList<Integer> getConversationMessages(String sender, String recipient) throws UserNotFoundException, UserNameNotFoundException {
        if (!conversations.containsKey(sender)) {
            throw new UserNotFoundException();
        }
        if (!conversations.containsKey(recipient)) {
            throw new UserNameNotFoundException();
        }
        return conversations.get(sender).get(recipient).getMessages();
    }

    /**
     * Attempts to return all Accounts who have had Conversations with the given Account.
     *
     * @param user given username
     * @return Set of usernames associated with recipient Accounts
     * @throws UserNotFoundException upon User not being found.
     */
    public Set<String> getAllUserConversationRecipients(String user) throws UserNotFoundException {
        if (!conversations.containsKey(user)) {
            throw new UserNotFoundException();
        }
        Set<String> recipients = this.conversations.get(user).keySet();
        return recipients.isEmpty() ? Collections.emptySet() : recipients;
    }

    /**
     * Adds a new key a username of an associated <code>Account</code>.
     *
     * @param username given username of associated <code>Account</code>
     */
    public void addAccountKey(String username) { conversations.put(username, new HashMap<>()); }


    /**
     * Creates a new message from a given sender Account to a given recipient Account
     * with given content, adding it to their associated <code>Conversation</code>. If
     * no existing <code>Conversation</code> is found, creates a new one.
     *
     * @param sender given sender username
     * @param recipient given recipient username
     * @param message given String content for message
     * @throws ObjectNotFoundException upon sender or recipient Account not being found
     */
    public void sendMessage(String sender, String recipient, String message) throws UserNotFoundException, UserNameNotFoundException {
        if (!conversations.containsKey(sender)) {
            throw new UserNotFoundException();
        }
        if (!conversations.containsKey(recipient)) {
            throw new UserNameNotFoundException();
        }
        HashMap<String, Conversation> senderConversations = conversations.get(sender);
        HashMap<String, Conversation> recipientConversations = conversations.get(recipient);
        Conversation givenConversation = senderConversations.get(recipient);
        Message newMessage = new Message(assignMessageID++, sender, recipient, message);
        if (givenConversation == null) {
            ArrayList<String> participants = new ArrayList<>(Arrays.asList(sender, recipient));
            givenConversation = new Conversation(participants, newMessage.getId());
        }
        else { addMessageToConversation(givenConversation, newMessage); }
        messages.put(newMessage.getId(), newMessage);
        senderConversations.put(recipient, givenConversation);
        recipientConversations.put(sender, givenConversation);
    }

    private void addMessageToConversation(Conversation conversation, Message message) {
        ArrayList<Integer> existingMessages = conversation.getMessages();
        if (existingMessages.size() != 0)
            message.setMsgToReply(existingMessages.get(existingMessages.size()-1));
        conversation.getMessages().add(message.getId());
    }
}