package controllers.account;

import controllers.ContactController;
import controllers.message.MessageController;
import exceptions.not_found.ObjectNotFoundException;
import deprecated.Presenter;
import deprecated.TextPresenter;
import use_cases.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AccountController {
    protected String username;
    protected AccountManager am;
    protected ContactManager fm;
    protected ConversationManager cm;
    protected EventManager em;
    protected Presenter presenter = new TextPresenter();
    protected ContactController contactController;
    protected MessageController messageController;

    /**
     * Facilitates interaction with the user (organizer/speaker/attendee) upon login
     *  @param username username of account user
     * @param am manages data of all accounts in program
     * @param fm manages data of contacts lists
     * @param cm manages conversation data
     * @param em manages event data
     */
    public AccountController(String username, AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.contactController = new ContactController(username, fm);
        this.messageController = new MessageController(username, am, cm, em);
    }

    /**
     * Displays the talk schedule for talks that start past the current time
     * in chronological order
     */
    public void SeeTalkSchedule() {
        this.presenter.displayTalkSchedule();
    }

    /**
     * Displays the list of contacts for the current user
     */
    public void viewContactList() {
        this.presenter.displayContactList(this.username);
    }

    /**
     * displays the numMessagesRequested most recent messages with the recipient
     * @param recipient person whose conversation with the user is being requested
     * @param numMessagesRequested an upper bound for the number of past messages requested to be seen
     */
    public void viewMessagesFrom(String recipient, int numMessagesRequested) throws ObjectNotFoundException, InputMismatchException{
        if (numMessagesRequested < 0) {
            this.presenter.displayPrompt("You have requested an invalid number");
        } else {
            String msgToPrint;
            ArrayList<Integer> convo = cm.getConversationMessages(this.username, recipient);
            this.presenter.displayPrompt("Your recent " + numMessagesRequested + " messages with " + recipient + ":");
            this.presenter.displayPrompt("");
            int numMessagesRetrieved = Math.min(numMessagesRequested, convo.size());
            for (int i = numMessagesRetrieved; i > 0; i--) {
                msgToPrint = cm.messageToString(convo.get(convo.size() - i)); // implemented fix
                this.presenter.displayPrompt(msgToPrint);
                this.presenter.displayPrompt("");
            }
        }
    }

    /**
     * A helper method to collect standart time information
     * @return returns the specified time
     * @throws InstantiationException error instantiating the time
     */
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

    /**
     * Runs the menu of options that the user sees and interacts with
     * @return returns a boolean which is True, indicating the user interaction ended
     */
    public abstract boolean runInteraction();

    /**
     * Returns whether or not the string s is numeric
     * @param s string
     * @return True if s is numeric
     */
    public boolean isNumeric(String s){
        for (char c: s.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}

