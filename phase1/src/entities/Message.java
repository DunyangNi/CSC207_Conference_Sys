package entities;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Message represents a message which can be exchanged between Accounts.
 *
 * <pre>
 * Entity Message
 * Responsibilities:
 *     Stores the contents of the message
 *     Stores the message that it is replying to, if it is
 *     Stores the sender(s)
 *     Stores the receiver(s)
 *     Can return requested information
 *
 * Collaborators:
 *     Account, Message
 *
 * Examples:
 *         Message m1 = new Message(new Account(), "I am fine");
 *         m1.addReceiver(new Account());
 *         m1.addReceiver(new Account());
 *         m1.addReceiver(new Account());
 *         m1.addReceiver(new Account());
 *
 *         System.out.println(m1.getNumReceiver()); // 4
 *         System.out.println(m1);
 *
 *         m1.setMsgToReply(new Message(new Account(),"How are you?"));
 *         System.out.println(m1);
 * </pre>
 */
public class Message {
    private Account sender;
    private Account receiver; // changed by Lucas
    private ArrayList<Account> receivers;
    private String content;
    private Message msgToReply;
    // TODO: 11/07/20 Do we need both a static and non-static id?
    private static int sid = 0;
    private int id;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------
    /**
     * Creates a msg object with a sender and a message content.
     *
     * @param sender a message sender
     * @param content a message content
     */
    public Message(Account sender, Account receiver, String content) {
        // call other constructor but with default value
        // TODO: 11/07/20 Is overload design better?
        new Message(sender, receiver, content, null);
    }

    // Overloaded constructor, accepts ArrayList<Account> for "receivers" parameter, enables group message functionality 
    public Message(Account sender, ArrayList<Account> receivers, String content) {
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        sid++;
    }

    /**
     * Creates a object with a sender and a message content in reply to
     * other messages.
     *
     * @param sender a message sender
     * @param content a message content
     * @param msgToReply a message being replied to
     */
    public Message(Account sender, Account receiver, String content, Message msgToReply) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.msgToReply = msgToReply;
        sid++;
    }

    /**
     * Gets the Message info.
     *
     * @return message info.
     */
    @Override
    public String toString(){
        String inReplyTo;
        // get recipient's username
        String rec = this.receiver.getUsername(); // changed by Lucas

        if (msgToReply == null){
            inReplyTo = "reply=None";
        }
        else{
            inReplyTo = "replyContent=";
            inReplyTo += msgToReply.getContent();
            inReplyTo += ", userToReply=" + msgToReply.getSender().getUsername();
        }
        return  id + " name=" + sender.getUsername() +
                " content=" + content + ", "+ "rec=" + rec + ", (" + inReplyTo +")";
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode() {
        return getSender().getUsername().hashCode() / 10 + getContent().hashCode() % 101;
    }

    /**
     * Compares messages for equality.
     *
     * @param other other message to compare
     * @return True if the msg IDs are same. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Message){
            boolean isSameCont = content.equals(((Message) other).content);
            if (msgToReply == null){
                return isSameCont && ((Message)other).msgToReply == null;
            }
            else{
                return isSameCont &&
                        msgToReply.equals(((Message)other).msgToReply);
            }
        }
        return false;
    }

    /**
     * Reset the ID counter for testing purpose.
     */
    public static void resetSid(){
        sid = 0;
    }

    //------------------------------------------------------------
    // Getters and Setters
    //------------------------------------------------------------
    /**
     * @return gets sender.
     */
    public Account getSender() {
        return sender;
    }

    /**
     * @return gets receiver.
     */
    public Account getReceiver() {
        return receiver;
    } // changed by Lucas

    /**
     * @return gets content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @return gets msg to reply. It can be a null if there is no msg
     * being replied to.
     */
    public Message getMsgToReply() {
        return msgToReply;
    }

    /**
     * @return gets id.
     */
    public int getId() {
        return id;
    }

    /**
     * @param content content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param msgToReply msg being replied to.
     */
    public void setMsgToReply(Message msgToReply) {
        this.msgToReply = msgToReply;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    } // changed by Lucas
}

