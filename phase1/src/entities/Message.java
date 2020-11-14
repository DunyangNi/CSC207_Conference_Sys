package entities;

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
 * </pre>
 */
public class Message {
    private String sender;
    private String receiver;
    private String content;
    private Integer msgToReply; // changed by Lucas~
    private static int sid = 0;
    private final int id;

    /**
     * Creates a object with a sender and a message content in reply to
     * other messages.
     *
     * @param sender a message sender
     * @param content a message content
     * @param msgToReply a message being replied to
     */
    public Message(String sender, String receiver, String content, Integer msgToReply) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.msgToReply = msgToReply;
        id = sid;
        sid++;
    }

    /**
     * Creates a msg object with a sender and a message content.
     *
     * @param sender a message sender
     * @param content a message content
     */
    public Message(String sender, String receiver, String content) {
        // call other constructor but with default value
        this(sender, receiver, content, null);
    }

    // toString() has now been moved to ConversationManager. Just letting you know~

    /**
     * @return hash code
     */
    @Override
    public int hashCode() {
        return getSender().hashCode() / 10 + getContent().hashCode() % 101;
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

    /**
     * @return gets sender.
     */
    public String getSender() {
        return sender;
    }

    /**
     * @return gets receiver.
     */
    public String getReceiver() {
        return receiver;
    } // changed by Lucas

    /**
     * @return gets content.
     */
    public String getContent() {
        return content;
    }

    /**
     * (UPDATED!)
     * @return gets msg to reply. It can be a null if there is no msg
     * being replied to.
     */
    public Integer getMsgToReply() {
        return msgToReply;
    }

    /**
     * @return gets id.
     */
    public int getId() {
        return id;
    }

    public static int getSid() {
        return sid;
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
    public void setMsgToReply(Integer msgToReply) {
        this.msgToReply = msgToReply;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    } // changed by Lucas

    public static void setSid(int sid) {
        Message.sid = sid;
    }
}