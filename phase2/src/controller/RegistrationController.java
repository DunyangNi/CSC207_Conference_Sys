package controller;

import gateway.DataManager;
import presenter.Presenter;
import presenter.TextPresenter;
import use_cases.*;

import java.util.Scanner;

public class RegistrationController {
    private final Scanner input = new Scanner(System.in);
    private final AccountManager accountManager;
    private final FriendManager friendManager;
    private final ConversationManager conversationManager;
    private final EventManager eventManager;
    private final Presenter presenter = new TextPresenter();

    /**
     * handles the creation of new organizer and attendee accounts for registration
     * @param am manages data of all accounts in the program
     * @param fm manages friendlist functionality
     * @param cm manages messaging functionality
     * @param em manages data of all events in the program
     */
    public RegistrationController(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.eventManager = em;
    }

    /**
     *  prompts user for which account they wish to create (attendee or organizer) and then attempts
     *  to create a new account for them according to username/password specifications by the user
     * @return false
     */
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
        DataManager dataManager = new DataManager();
        dataManager.saveAllManagers(eventManager, accountManager, conversationManager, friendManager);
        return false;
    }



    /**
     * Prompts the user for the organizer account registration code
     */
    private void requireOrganizerPassword() {
        String ORGANIZER_REGISTRATION_CODE = "123456";

        this.presenter.displayPrompt("Enter the Organizer account registration code:");
        String code = input.nextLine();
        while (!code.equals(ORGANIZER_REGISTRATION_CODE)) {
            this.presenter.displayPrompt("Invalid input, please try again.");
            code = input.nextLine();
        }
    }

    /**
     * prompts user for username and password
     * @param type 1 if account type is Attendee, 2 if account type is Organizer
     * @return username and password info
     */
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

    /**
     * helper method that adds the given username as a key to various
     * hashmaps in the use cases
     * @param username given username of associated <code>Account</code>
     */
    private void addNewAccountKeys(String username) {
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
    }
}
