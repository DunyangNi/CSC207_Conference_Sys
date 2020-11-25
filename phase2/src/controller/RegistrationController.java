package controller;

import gateway.DataManager;
import presenter.ConsolePresenter;
import presenter.RegistrationPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class RegistrationController {
    private final Scanner input = new Scanner(System.in);
    private final AccountManager accountManager;
    private final FriendManager friendManager;
    private final ConversationManager conversationManager;
    private final EventManager eventManager;
    private final ConsolePresenter presenter = new RegistrationPresenter();

    /**
     * handles the creation of new organizer and attendee accounts for registration
     *
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
     * prompts user for which account they wish to create (attendee or organizer) and then attempts
     * to create a new account for them according to username/password specifications by the user
     *
     * @return false
     */
    public boolean attemptRegister() {
        presenter.preUserInput("accountType");
        String accountType = input.nextLine();

        while (!(accountType.equals("1") || (accountType.equals("2")))) {
            presenter.postUserInput("accountType");
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

        presenter.preUserInput("code");
        String code = input.nextLine();
        while (!code.equals(ORGANIZER_REGISTRATION_CODE)) {
            presenter.postUserInput("code");
            code = input.nextLine();
        }
    }

    /**
     * prompts user for username and password
     *
     * @param type 1 if account type is Attendee, 2 if account type is Organizer
     * @return username and password info
     */
    private String[] getNewAccountInfo(String type) {
        if (type.equals("2")) {
            requireOrganizerPassword();
        }
        presenter.preUserInput("username");
        String username = input.nextLine();

        while ((accountManager.containsAccount(username))) {
            presenter.postUserInput("username");
            username = input.nextLine();
        }

        // Obtain rest of information and bundle into Tuple of 4
        presenter.preUserInput("password");
        String password = input.nextLine();

        return new String[]{username, password, "", ""};
    }

    /**
     * helper method that adds the given username as a key to various
     * hashmaps in the use cases
     *
     * @param username given username of associated <code>Account</code>
     */
    private void addNewAccountKeys(String username) {
        conversationManager.addAccountKey(username);
        friendManager.addAccountKey(username);
    }
}
