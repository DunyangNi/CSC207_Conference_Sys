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
        this.messageController = new MessageController(username, accountManager, conversationManager, eventManager, signupManager);
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void seeFriendList() {
        this.presenter.displayFriendList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessagesRequested) {
        try {
            if (numMessagesRequested < 0) {
                System.out.println("You have requested an invalid number");
            } else {
                String msgToPrint;
                ArrayList<Integer> convo = conversationManager.getConversationMessages(this.username, recipient);
                System.out.println("Your recent " + numMessagesRequested + " messages with " + recipient + ":");
                System.out.println();
                int numMessagesRetrieved = Math.min(numMessagesRequested, convo.size());
                for (int i = numMessagesRetrieved; i > 0; i--) {
                    msgToPrint = conversationManager.messageToString(convo.get(convo.size() - i)); // implemented fix
                    System.out.println(msgToPrint);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString() + "\nSomething went wrong in collectTimeInfo. Please enter valid input.\n");
        }
    }

    protected Calendar collectTimeInfo() throws InstantiationException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Day of the month (1-31)");
            int dayOfMonth = sc.nextInt();
            sc.nextLine();
            System.out.println("Month (1-12)");
            int month = sc.nextInt()-1;
            sc.nextLine();
            System.out.println("Year (YYYY)");
            int year = sc.nextInt();
            sc.nextLine();
            System.out.println("Hour of the day (9-16)");
            int hourOfDay = sc.nextInt();
            sc.nextLine();

            Calendar time = Calendar.getInstance();
            time.set(year, month, dayOfMonth, hourOfDay, 0, 0);
            time.clear(Calendar.MILLISECOND);

            return time;
        } catch (Exception e) {
            System.out.println(e.toString() + "\nSomething went wrong in collectTimeInfo. Please enter valid input.\n");
            throw new InstantiationException();
        }
    }

    public abstract boolean runInteraction() throws InstantiationException;
}
