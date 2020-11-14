package controller;
import use_cases.*;
import presenter.*;
import java.util.*;
import java.lang.*;

public abstract class UserController {
    protected String username;
    protected EventManager eventmanager;
    protected ConversationManager conversationManager;
    protected FriendManager friendManager;
    protected SignupManager signupManager;
    protected Presenter presenter;

    public UserController(String username, EventManager eventmanager,
                             ConversationManager conversationManager, FriendManager friendManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.conversationManager = conversationManager;
        this.friendManager = friendManager;
        this.signupManager = new SignupManager(eventmanager);
        this.presenter = new Presenter(eventmanager, friendManager, signupManager);
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void addFriend(String friendToAdd) {
        friendManager.AddFriend(this.username, friendToAdd);
    }

    public void removeFriend(String friendToRemove) {
        friendManager.RemoveFriend(this.username, friendToRemove);
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessages) {
        if (numMessages < 0) { System.out.println("This is an invalid number"); }
        else {
            String msgToPrint;
            ArrayList<Integer> convo = conversationManager.getConversationMessages(this.username, recipient);
            System.out.println("Your recent " + numMessages + " messages with " + recipient + ":");
            System.out.println();
            int recent_num = Math.min(numMessages, convo.size());
            for (int i = 0; i < recent_num; i++) {
                msgToPrint = conversationManager.messageToString(convo.get(numMessages - recent_num - 1 + i));
                System.out.println(msgToPrint);
                System.out.println();
            }
        }
    }

    public abstract void runInteraction();
}
