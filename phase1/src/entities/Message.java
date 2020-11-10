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
    private Account sender;
    private Account receiver;
    private String content;
    private Message msgToReply;
    private static int sid = 0;
    private int id;

    //------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------

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
        this.id = sid;
        sid++;
    }

    /**
     * Creates a msg object with a sender and a message content.
     *
     * @param sender a message sender
     * @param content a message content
     */
    public Message(Account sender, Account receiver, String content) {
        // call other constructor but with default value
        this(sender, receiver, content, null);
    }

    /**
     * (NEW!) Gets the Message info.
     *
     * @return message info, formatted as
     *  [Message id] (sender1) : content [ReplyTo] (sender2) : content (first 10 char)...
     *  [Message id] (sender1) : content [ReplyTo] (None)
     */
    @Override
    public String toString() {
        StringBuilder str_write = new StringBuilder("[Message ");
        str_write.append(this.id);
        str_write.append("] (");
        str_write.append(sender.getUsername());
        str_write.append(") : ");
        str_write.append(content);
        str_write.append(" [ReplyTo] (");
        if (msgToReply == null) { str_write.append("None)"); }
        else {
            str_write.append(msgToReply.getSender().getUsername());
            str_write.append(") : ");
            String replyContent = msgToReply.getContent();
            str_write.append(replyContent, 0, Math.min(replyContent.length(), 10));
            str_write.append(replyContent.length() <= 10 ? "" : "...");
        }
        return str_write.toString();
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
    static void resetSid(){
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

