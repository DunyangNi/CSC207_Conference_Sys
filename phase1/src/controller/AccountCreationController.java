package controller;

import use_cases.*;

import java.util.Scanner;

public class AccountCreationController {
    private final String ORGANIZER_PASSWORD = "123456";
    private final Scanner input = new Scanner(System.in);
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;

    public AccountCreationController(AccountManager am, ConversationManager cm, FriendManager fm, EventManager em, SignupManager sm) {
        this.accountManager = am;
        this.eventManager = em;
        this.conversationManager = cm;
        this.friendManager = fm;
        this.signupManager = sm;
    }

    public void createNewAccount() {
        System.out.println("Enter '1' to create an Attendee account.\nEnter '2' to create an Organizer account.");
        String command = input.nextLine();

        while (!(command.equals("1") || (command.equals("2")))) {
            System.out.println("Invalid input, please try again.");
            command = input.nextLine();
        }

        if (command.equals("1")) {
            createNewAttendee();
        } else {
            createNewOrganizer();
        }
    }

    private void createNewAttendee() {
        String[] accountInfo = getNewAccountInfo();
        accountManager.AddNewAttendee(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        conversationManager.addAccountKey(accountInfo[0]);
        friendManager.addAccountKey(accountInfo[0]);

        AttendeeController ac = new AttendeeController(accountInfo[0], eventManager, conversationManager, friendManager, signupManager, accountManager);
        ac.runInteraction();
    }

    private void createNewOrganizer() {
        System.out.println("Enter the Organizer account creation code:");
        String code = input.nextLine();

        while (!code.equals(ORGANIZER_PASSWORD)) {
            System.out.println("Invalid input, please try again.");
            code = input.nextLine();
        }

        String[] accountInfo = getNewAccountInfo();
        accountManager.AddNewOrganizer(accountInfo[0], accountInfo[1], accountInfo[2], accountInfo[3]);
        conversationManager.addAccountKey(accountInfo[0]);
        friendManager.addAccountKey(accountInfo[0]);

        OrganizerController oc = new OrganizerController(accountInfo[0], eventManager, accountManager, signupManager, conversationManager, friendManager);
        oc.runInteraction();
    }

    private String[] getNewAccountInfo() {
        System.out.println("Enter a username:");
        String username = input.nextLine();

        while ((accountManager.containsAccount(username))) {
            System.out.println("This username is already taken, please try again:");
            username = input.nextLine();
        }

        // Obtain rest of information and bundle into Tuple of 4
        System.out.println("Enter a password:");
        String password = input.nextLine();
        System.out.println("Enter your first name:");
        String firstname = input.nextLine();
        System.out.println("Enter your last name:");
        String lastname = input.nextLine();

        return new String[]{username, password, firstname, lastname};
    }
}
