package controller;

import presenter.Presenter;
import use_cases.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

public abstract class AccountController {
    protected String username;
    protected AccountManager accountManager;
    protected FriendManager friendManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;
    protected SignupManager signupManager;
    protected Presenter presenter;
    protected FriendController friendController;
    protected MessageController messageController;

    public AccountController(String username, AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager) {
        this.username = username;
        this.accountManager = accountManager;
        this.friendManager = friendManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
        this.presenter = new Presenter(eventManager, friendManager, signupManager);
        this.friendController = new FriendController(username, friendManager, presenter);
        this.messageController = new MessageController(username, accountManager, conversationManager, eventManager);
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessages) {
        try {
            if (numMessages < 0) {
                System.out.println("You have requested an invalid number");
            } else {
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
        } catch (Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
        }
    }

    protected Calendar registerEventTime() throws InstantiationException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Day of the month (1-31)");
            int dayOfMonth = sc.nextInt();
            sc.nextLine();
            System.out.println("Month (1-12)");
            int month = sc.nextInt();
            sc.nextLine();
            System.out.println("Year (YYYY)");
            int year = sc.nextInt();
            sc.nextLine();
            System.out.println("Hour of the day (9-16)");
            int hourOfDay = sc.nextInt();
            sc.nextLine();

            Calendar time = Calendar.getInstance();
            time.set(Calendar.YEAR, year);
            TimeZone tz = TimeZone.getTimeZone("EST");
            time.setTimeZone(tz);
            time.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            time.set(Calendar.MONTH, month - 1);
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, 0);
            time.set(Calendar.SECOND, 0);
            time.set(Calendar.MILLISECOND, 0);

            return time;
        } catch (Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
            throw new InstantiationException();
        }
    }

    public abstract void runInteraction() throws InstantiationException;
}
