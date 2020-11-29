package controller;

import Throwables.*;
import presenter.Presenter;
import use_cases.*;

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
    protected AddFriendController addFriendController;
    protected RemoveFriendController removeFriendController;
    protected MessageAttendeeController messageAttendeeController;
    protected MessageSpeakerController messageSpeakerController;
    protected ViewConversationController viewConversationController;
    protected EventSignUpController eventSignUpController;
    protected AccountCreationController accountCreationController;
    protected LocationController locationController;
    protected EventCreationController eventCreationController;
    protected EventModifyController eventModifyController;

    /**
     * Facilitates interaction with the user (organizer/speaker/attendee) upon login
     *
     * @param username username of account user
     * @param accountManager manages data of all accounts in program
     * @param friendManager manages data of contacts lists
     * @param conversationManager manages conversation data
     * @param eventManager manages event data
     * @param signupManager manages event signup and related information
     * @param presenter defines the UI
     */
    public AccountController(String username, AccountManager accountManager, FriendManager friendManager,
                             ConversationManager conversationManager, EventManager eventManager,
                             SignupManager signupManager, Presenter presenter) {
        this.username = username;
        this.accountManager = accountManager;
        this.friendManager = friendManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
        this.presenter = presenter;
        this.addFriendController = new AddFriendController(username, friendManager);
        this.removeFriendController = new RemoveFriendController(username, friendManager);
        this.messageSpeakerController = new MessageSpeakerController(username, accountManager, conversationManager, eventManager, signupManager);
        this.messageAttendeeController = new MessageAttendeeController(username, accountManager, conversationManager, eventManager, signupManager);
        this.viewConversationController = new ViewConversationController(conversationManager, username);
        this.eventSignUpController = new EventSignUpController(signupManager, username);
        this.accountCreationController = new AccountCreationController(accountManager ,conversationManager, friendManager, eventManager);
        this.locationController = new LocationController(eventManager);
        this.eventCreationController = new EventCreationController(username, eventManager, signupManager);
        this.eventModifyController = new EventModifyController(eventManager);
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
    public abstract boolean runInteraction() throws UserNotFoundException, UserNameNotFoundException, AlreadyExistException, EmptyListException, EventNotFoundException, InvalidIntegerException, MessageNotFound, EventFullException, AlreadyExistException;

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

