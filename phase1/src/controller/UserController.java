package controller;

import Throwables.ObjectNotFoundException;
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
    protected FriendController friendController;
    protected MessageController messageController;
    protected AccountManager accountManager;
    protected Presenter presenter;

    public UserController(String username, EventManager eventmanager, ConversationManager conversationManager,
                          FriendManager friendManager, SignupManager signupManager, AccountManager accountManager) {
        this.username = username;
        this.eventmanager = eventmanager;
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.friendManager = friendManager;
        this.signupManager = signupManager;
        this.presenter = new Presenter(eventmanager, friendManager, signupManager);
        this.friendController = new FriendController(username, friendManager, presenter);
        this.messageController = new MessageController(username, accountManager, conversationManager, eventmanager);
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void addFriend(String friendToAdd) {
        try {
            friendManager.AddFriend(this.username, friendToAdd);
        }
        catch(Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void removeFriend(String friendToRemove) {
        try{
            friendManager.RemoveFriend(this.username, friendToRemove);
        }
        catch(Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessages) {
        try {
            if (numMessages < 0) { System.out.println("You have requested an invalid number"); }
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
        catch(Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    protected Calendar _timeoftalkrequesthelper(String talkdescriptor) throws InstantiationException{
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Specify the year of the talk " + talkdescriptor);
            int year = sc.nextInt();
            sc.nextLine();
            System.out.println("Specify the numerical month (1-12) of the talk " + talkdescriptor);
            int month = sc.nextInt();
            sc.nextLine();
            System.out.println("Specify the day of the month (1-31) of the talk " + talkdescriptor);
            int dayofmonth = sc.nextInt();
            sc.nextLine();
            System.out.println("Specify the hour of the day (0-23) of the talk " + talkdescriptor);
            int hourofday = sc.nextInt();
            sc.nextLine();

            Calendar time = Calendar.getInstance();
            time.set(Calendar.YEAR, year);
            TimeZone tz = TimeZone.getTimeZone("EST");
            time.setTimeZone(tz);
            time.set(Calendar.DAY_OF_MONTH, dayofmonth);
            time.set(Calendar.MONTH, month - 1);
            time.set(Calendar.HOUR_OF_DAY, hourofday);
            time.set(Calendar.MINUTE, 0);
            time.set(Calendar.SECOND, 0);
            time.set(Calendar.MILLISECOND, 0);

            return time;
        }
        catch(Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
            throw new InstantiationException();
        }
    }

    public abstract void runInteraction() throws InstantiationException;
}
