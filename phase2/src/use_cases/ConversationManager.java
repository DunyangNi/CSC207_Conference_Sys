package use_cases;

import java.io.Serializable;
import java.util.*;

import entities.message.Conversation;
import entities.message.Message;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;

/**
 * Represents the entire system of Conversations and Messages.
 */
public class ConversationManager implements Serializable {
    private final HashMap<String, HashMap<String, Conversation>> conversations = new HashMap<>();
    private final HashMap<Integer, Message> messages = new HashMap<>();
    private int assignMessageID;

    public String messageToString(Integer id) throws MessageNotFoundException {
        if (!this.messages.containsKey(id)) {
            throw new MessageNotFoundException();
        }
        Message selectedMsg = messages.get(id);
        String sender = selectedMsg.getSender();
        String content = selectedMsg.getContent();
        return "(" + sender + ") : " + content;
    }

    public ArrayList<Integer> getConversationMessages(String sender, String recipient) throws RecipientNotFoundException {
        if (!conversations.containsKey(recipient)) {
            throw new RecipientNotFoundException();
        }
        return conversations.get(sender).get(recipient).getMessages();
    }

    public Set<String> getAllConversationRecipients(String user) {
        Set<String> recipients = this.conversations.get(user).keySet();
        return recipients.isEmpty() ? Collections.emptySet() : recipients;
    }

    /**
     * Adds a new key a username of an associated <code>Account</code>.
     *
     * @param username given username of associated <code>Account</code>
     */
    public void addAccountKey(String username) { conversations.put(username, new HashMap<>()); }

    // TODO this method is incorrectly assigning recipient and message (being swapped)
    public void sendMessage(String sender, String recipient, String message) throws UserNotFoundException, RecipientNotFoundException {
        if (!conversations.containsKey(sender)) {
            throw new UserNotFoundException();
        }
        if (!conversations.containsKey(recipient)) {
            throw new RecipientNotFoundException();
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