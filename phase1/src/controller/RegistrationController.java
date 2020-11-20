package controller;

import presenter.Presenter;
import presenter.TextPresenter;
import use_cases.*;

import java.util.Scanner;

public class RegistrationController {
    private final Scanner input = new Scanner(System.in);
    private final AccountManager accountManager;
    private final ConversationManager conversationManager;
    private final FriendManager friendManager;
    private final Presenter presenter = new TextPresenter();

    public RegistrationController(AccountManager am, FriendManager fm, ConversationManager cm) {
        this.accountManager = am;
        this.conversationManager = cm;
        this.friendManager = fm;
    }

    public boolean attemptRegister() {
        this.presenter.displayPrompt("Enter '1' to register an Attendee account.\nEnter '2' to register an Organizer account.");
        String accountType = input.nextLine();

        while (!(accountType.equals("1") || (accountType.equals("2")))) {
            this.presenter.displayPrompt("Invalid input, please try again.");
            accountType = input.nextLine();
        }

        String[] accountInfo = getNewAccountInfo(accountType);
        if (accountType.equals("1")) {
            accountManager.addNewAttendee(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        } else {
            accountManager.addNewOrganizer(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        }
        addNewAccountKeys(accountInfo[0]);
        return false;
    }

    private void requireOrganizerPassword() {
        String ORGANIZER_REGISTRATION_CODE = "123456";

        this.presenter.displayPrompt("Enter the Organizer account registration code:");
        String code = input.nextLine();
        while (!code.equals(ORGANIZER_REGISTRATION_CODE)) {
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
