package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import entities.*;

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
    private HashMap<String, HashMap<String, Conversation>> conversations = new HashMap<>(); // (NEW!) key: username ; value: hash of convos
    private HashMap<Integer, Message> messages = new HashMap<>(); // (NEW!) key: message id ; value: Message object

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    public String messageToString(Integer id) {
        // Obtain Message information
        Message selectedMsg = messages.get(id);
        Message msgToReply = messages.get(selectedMsg.getMsgToReply());
        String sender = selectedMsg.getSender();
        String content = selectedMsg.getContent();
        // Construct the String representation
        StringBuilder str_write = new StringBuilder("[Message ");
        str_write.append(id);
        str_write.append("] (");
        str_write.append(sender);
        str_write.append(") : ");
        str_write.append(content);
        str_write.append(" [ReplyTo] (");
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

    // formerly getConversationArrayList
    public ArrayList<Integer> getConversationMessages(String user, String recipient) {
        Conversation selectedConvo = conversations.get(user).get(recipient);
        if (selectedConvo == null) { return new ArrayList<>(); }
        return selectedConvo.getMessages();
    }

    /**
     * (UPDATED!) Returns all Accounts who have had Conversations with the given Account.
     *  If given Account has no Conversations, returns the empty set.
     * @param current username of given Account
     * @return Set of usernames associated with recipient Accounts
     */
    public Set<String> getAllUserConversationRecipients(String current) {
        Set<String> recipients = this.conversations.get(current).keySet();
        return recipients.isEmpty() ? Collections.emptySet() : recipients;
    }

    /**
     * (UPDATED!) Sends a message from a sender Account to a recipient Account
     * @param sender given sender Account
     * @param recipient given recipient Account
     * @param message given String content for message
     */
    public void sendMessage(String sender, String recipient, String message) {
        //if (recipient != null && validRecipient(sender, recipient)) {
        HashMap<String, Conversation> senderConversations = this.conversations.get(sender);
        HashMap<String, Conversation> recipientConversations = this.conversations.get(recipient);
        Conversation givenConversation = senderConversations.get(recipient);
        Message newMessage = new Message(sender, recipient, message);
        // Create a new Conversation if an existing one isn't found.
        if (givenConversation == null) {
            ArrayList<String> participants = new ArrayList<>(Arrays.asList(sender, recipient));
            givenConversation = new Conversation(participants, newMessage.getId());
            senderConversations.put(recipient, givenConversation);
            recipientConversations.put(sender, givenConversation);
        }
        else {
            addMessageToConversation(givenConversation, newMessage);
            senderConversations.replace(recipient, givenConversation);
            recipientConversations.replace(sender, givenConversation);
        }
        this.conversations.replace(sender, senderConversations);
        this.conversations.replace(recipient, recipientConversations);
        //}
        //else{
        //    System.out.println("ERROR: invalid recipient username: '"
        //            + recipient.getUsername() + "'");
        //}
    }

    /**
     * (UPDATED!) (Helper) Adds given Message to given Conversation, assigning msgToReply if needed.
     * @param conversation given Conversation
     * @param message given Message
     */
    public void addMessageToConversation(Conversation conversation, Message message) {
//        Old Version:
//        Get list of Messages from Conversation
//        ArrayList<Integer> existingMessages = conversation.getMessages();
//        // Assign message to reply to. By default, it is the Message last added to the Conversation.
//        if (existingMessages.size() != 0) {
//            Message msgToReply = this.messages.get(existingMessages.get(existingMessages.size()-1));
//            message.setMsgToReply(msgToReply);
//        }
//        // Add new message to Conversation
//        existingMessages.add(message.getId());
//        // Set new list of Messages to Conversation
//        conversation.setMessages(existingMessages);
        ArrayList<Integer> existingMessages = conversation.getMessages();
        if (existingMessages.size() != 0) {
            message.setMsgToReply(existingMessages.get(existingMessages.size()-1));
        }
        conversation.getMessages().add(message.getId());
    }
}