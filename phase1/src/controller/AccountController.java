package controller;

import presenter.Presenter;
import use_cases.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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

    public AccountController(String username, AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager, Presenter presenter) {
        this.username = username;
        this.accountManager = accountManager;
        this.friendManager = friendManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
        this.presenter = presenter;
        this.friendController = new FriendController(username, friendManager, presenter);
        this.messageController = new MessageController(username, accountManager, conversationManager, eventManager, signupManager);
    }

    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    public void viewContactList() {
        this.presenter.displayContactList(this.username);
    }

    public void viewMessagesFrom(String recipient, int numMessagesRequested) {
        try {
            if (numMessagesRequested < 0) {
                this.presenter.displayPrompt("You have requested an invalid number");
            } else {
                String msgToPrint;
                ArrayList<Integer> convo = conversationManager.getConversationMessages(this.username, recipient);
                this.presenter.displayPrompt("Your recent " + numMessagesRequested + " messages with " + recipient + ":");
                this.presenter.displayPrompt("");
                int numMessagesRetrieved = Math.min(numMessagesRequested, convo.size());
                for (int i = numMessagesRetrieved; i > 0; i--) {
                    msgToPrint = conversationManager.messageToString(convo.get(convo.size() - i)); // implemented fix
                    this.presenter.displayPrompt(msgToPrint);
                    this.presenter.displayPrompt("");
                }
            }
        } catch (Exception e) {
            this.presenter.displayPrompt(e.toString() + "\nSomething went wrong in collectTimeInfo. Please enter valid input.\n");
        }
    }

    protected Calendar collectTimeInfo() throws InstantiationException {
        try {
            Scanner sc = new Scanner(System.in);
            this.presenter.displayPrompt("Day of the month (1-31)");
            int dayOfMonth = sc.nextInt();
            sc.nextLine();
            this.presenter.displayPrompt("Month (1-12)");
            int month = sc.nextInt()-1;
            sc.nextLine();
            this.presenter.displayPrompt("Year (YYYY)");
            int year = sc.nextInt();
            sc.nextLine();
            this.presenter.displayPrompt("Hour of the day (9-16)");
            int hourOfDay = sc.nextInt();
            sc.nextLine();

            Calendar time = Calendar.getInstance();
            time.set(year, month, dayOfMonth, hourOfDay, 0, 0);
            time.clear(Calendar.MILLISECOND);

            return time;
        } catch (Exception e) {
            this.presenter.displayPrompt(e.toString() + "\nSomething went wrong in collectTimeInfo. Please enter valid input.\n");
            throw new InstantiationException();
        }
    }

    public abstract boolean runInteraction() throws InstantiationException;

    public boolean isNumeric(String s){
        for (char c: s.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}

