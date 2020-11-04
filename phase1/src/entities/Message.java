package entities;

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
    private ArrayList<Account> receiver;
    private String content;
    private Message msgToReply;
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
    public Message(Account sender, ArrayList<Account> receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        sid++;
        id = sid;
    }

    /**
     * Creates a object with a sender and a message content in reply to
     * other messages.
     *
     * @param sender a message sender
     * @param content a message content
     * @param msgToReply a message being replied to
     */
    public Message(Account sender, ArrayList<Account> receiver, String content, Message msgToReply) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.msgToReply = msgToReply;
        id++;
    }

    /**
     * Adds a new receiver to the receiver list.
     *
     * @param newReceiver a new message receiver
     */
    public void addReceiver(Account newReceiver) {
        receiver.add(newReceiver);
    }

    /**
     * Gets the total number of the receivers in the list.
     *
     * @return total number of receivers
     */
    public int getNumReceiver(){
        return receiver.size();
    }

    /**
     * Gets the Message info.
     *
     * @return message info.
     */
    @Override
    public String toString(){

        String inReplyTo;

        String rec = "";
        if (getReceiver().size() != 0){
            for (Account atd1: getReceiver()){
                rec += atd1.getUsername() + " ";
            }
            rec = rec.substring(0, rec.length()-1);
        }


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
    public ArrayList<Account> getReceiver() {
        return receiver;
    }

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

    public void setReceiver(ArrayList<Account> receiver) {
        this.receiver = receiver;
    }
}

