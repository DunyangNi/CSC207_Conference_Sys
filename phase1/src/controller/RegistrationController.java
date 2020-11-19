package controller;

import gateway.DataManager;
import presenter.*;
import use_cases.*;

import java.util.Scanner;

public class RegistrationController {
    private final String ORGANIZER_PASSWORD = "123456";
    private final Scanner input = new Scanner(System.in);
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    private Presenter presenter = new TextPresenter();

    public RegistrationController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em, SignupManager sm) {
        this.accountManager = am;
        this.eventManager = em;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.signupManager = sm;
    }

    public boolean attemptRegister() {
        this.presenter.displayPrompt("Enter '1' to register an Attendee account.\nEnter '2' to register an Organizer account.");
        String command = input.nextLine();
        boolean programEnd;
        while (!(command.equals("1") || (command.equals("2")))) {
            this.presenter.displayPrompt("Invalid input, please try again.");
            command = input.nextLine();
        }
        if (command.equals("1")) {
            programEnd = registerNewAttendee();
        } else {
            programEnd = registerNewOrganizer();
        }
        DataManager dataManager = new DataManager();
        dataManager.saveManager("EventManager", "EventManager", eventManager);
        dataManager.saveManager("AccountManager", "AccountManager", accountManager);
        dataManager.saveManager("ConversationManager", "ConversationManager", conversationManager);
        dataManager.saveManager("FriendManager", "FriendManager", friendManager);
        dataManager.saveManager("SignupManager", "SignupManager", signupManager);
        return programEnd;
    }

    private boolean registerNewAttendee() {
        boolean programEnd;
        String[] accountInfo = getNewAccountInfo();
        accountManager.AddNewAttendee(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        addNewAccountKeys(accountInfo[0]);
        TextPresenter textpresenter = new TextPresenter(eventManager, friendManager, signupManager);
        AttendeeController ac = new AttendeeController(accountInfo[0], eventManager, conversationManager, friendManager, signupManager, accountManager, textpresenter);
        programEnd = ac.runInteraction();
        return programEnd;
    }

    private boolean registerNewOrganizer() {
        this.presenter.displayPrompt("Enter the Organizer account registration code:");
        String code = input.nextLine();
        while (!code.equals(ORGANIZER_PASSWORD)) {
            this.presenter.displayPrompt("Invalid input, please try again.");
            code = input.nextLine();
        }
        boolean programEnd;
        String[] accountInfo = getNewAccountInfo();
        accountManager.AddNewOrganizer(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        addNewAccountKeys(accountInfo[0]);
        TextPresenter textpresenter = new TextPresenter(eventManager, friendManager, signupManager);
        OrganizerController oc = new OrganizerController(accountInfo[0], accountManager, friendManager, conversationManager, eventManager, signupManager, textpresenter);
        programEnd = oc.runInteraction();
        return programEnd;
    }

    private String[] getNewAccountInfo() {
        this.presenter.displayPrompt("Enter a username:");
        String username = input.nextLine();

        while ((accountManager.containsAccount(username))) {
            this.presenter.displayPrompt("This username is already taken, please try again:");
            username = input.nextLine();
        }

        // Obtain rest of information and bundle into Tuple of 4
        this.presenter.displayPrompt("Enter a password:");
        String password = input.nextLine();
        this.presenter.displayPrompt("Enter your first name:");
        String firstname = input.nextLine();
        this.presenter.displayPrompt("Enter your last name:");
        String lastname = input.nextLine();

        return new String[]{username, password, firstname, lastname};
    }

    // (NEW!) (Helper)
    private void addNewAccountKeys(String username) {
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
    }
}
