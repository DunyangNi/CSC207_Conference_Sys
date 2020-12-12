package entities.message;

import java.io.Serializable;

/**
 * Represents a message which can be exchanged between Accounts in a <code>Conversation</code>.
 */
public class Message implements Serializable {
    private final String sender;
    private final String recipient;
    private String content;
    private Integer msgToReply;
    private final int id;

    /**
     * Creates an instance of <code>Message</code> with an assigned ID, Strings of information,
     * and the ID of a message to reply to.
     *
     * @param id assigned ID
     * @param sender given sender username
     * @param recipient given recipient username
     * @param content given content of message
     * @param msgToReply given ID of <code>Message</code> to reply to
     */
    public Message(int id, String sender, String recipient, String content, Integer msgToReply) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.msgToReply = msgToReply;
        this.id = id;
    }

    /**
     * Creates an instance of <code>Message</code> with an assigned ID and Strings of information.
     *
     * @param id assigned ID
     * @param sender given sender username
     * @param recipient given recipient username
     * @param content given content of message
     */
    public Message(int id, String sender, String recipient, String content) {
        this(id, sender, recipient, content, null);
    }

    /**
     * Compares a given <code>Object</code> with this <code>Message</code>. Returns
     * true iff the given <code>Object</code> matches this <code>Message</code>.
     *
     * @param other other <code>Object</code> presumed <code>Message</code> to compare
     * @return the given <code>Object</code> matches this <code>Message</code>
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Message){
            boolean isSameCont = content.equals(((Message) other).content);
            if (msgToReply == null) {
                return isSameCont && ((Message)other).msgToReply == null;
            }
            else {
                return isSameCont && msgToReply.equals(((Message)other).msgToReply);
            }
        }
        return false;
    }

    /**
     * @return hash code of this <code>Message</code>
     */
    @Override
    public int hashCode() {
        return getSender().hashCode() / 10 + getContent().hashCode() % 101;
    }

    /**
     * @return sender username of this <code>Message</code>
     */
    public String getSender() {
        return sender;
    }

    /**
     * @return recipient username of this <code>Message</code>
     */
    public String getRecipient() {
        return recipient;
    } // changed by Lucas

    /**
     * @return content of this <code>Message</code>
     */
    public String getContent() {
        return content;
    }

    /**
     * @return ID of <code>Message</code> that this <code>Message</code> is replying to.
     */
    public Integer getMsgToReply() {
        return msgToReply;
    }

    /**
     * @return ID of this <code>Message</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new content of this <code>Message</code>
     *
     * @param content new content of <code>Message</code>
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets new ID of <code>Message</code> that this <code>Message</code> is replying to.
     *
     * @param msgToReply ID of <code>Message</code> that this <code>Message</code> is replying to
     */
    public void setMsgToReply(Integer msgToReply) {
        this.msgToReply = msgToReply;
    }
}