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
        boolean programEnd;
        this.presenter.displayPrompt("Enter '1' to register an Attendee account.\nEnter '2' to register an Organizer account.");
        String accountType = input.nextLine();

        while (!(accountType.equals("1") || (accountType.equals("2")))) {
            this.presenter.displayPrompt("Invalid input, please try again.");
            accountType = input.nextLine();
        }

        String[] accountInfo = getNewAccountInfo(accountType);
        if (accountType.equals("1")) {
            accountManager.AddNewAttendee(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        } else {
            accountManager.AddNewOrganizer(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        }
        addNewAccountKeys(accountInfo[0]);
        return false;
    }

    private void requireOrganizerPassword() {
        this.presenter.displayPrompt("Enter the Organizer account registration code:");
        String code = input.nextLine();
        while (!code.equals(ORGANIZER_PASSWORD)) {
            this.presenter.displayPrompt("Invalid input, please try again.");
            code = input.nextLine();
        }
    }

    private String[] getNewAccountInfo(String type) {
        if (type.equals("2")) {
            requireOrganizerPassword();
        }
        this.presenter.displayPrompt("Enter a username:");
        String username = input.nextLine();

        while ((accountManager.containsAccount(username))) {
            this.presenter.displayPrompt("This username is already taken, please try again:");
            username = input.nextLine();
        }

        // Obtain rest of information and bundle into Tuple of 4
        this.presenter.displayPrompt("Enter a password:");
        String password = input.nextLine();

        return new String[]{username, password, "", ""};
    }

    private void addNewAccountKeys(String username) {
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
    }
}
