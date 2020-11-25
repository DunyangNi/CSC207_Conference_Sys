package controller;

import presenter.ConsolePresenter;
import presenter.StartPresenter;
import use_cases.*;

import java.util.Scanner;

public class StartController {
    private final Scanner input = new Scanner(System.in);
    private final AccountManager accountManager;
    private final FriendManager friendManager;
    private final ConversationManager conversationManager;
    private final EventManager eventManager;
    private final ConsolePresenter presenter = new StartPresenter();

    /**
     * Manages the home screen
     * @param accountManager manages account data
     * @param friendManager manages friendList functionality
     * @param conversationManager manages messaging functionality
     * @param eventManager manages event data
     */
    StartController(AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventManager) {
        this.accountManager = accountManager;
        this.friendManager = friendManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
    }

    /**
     * Displays the home/start menu to the user when they start the program or return to
     * home screen.
     * The user has the option to exit the program, login to an account, or register a new account.
     * Should the user access their account, they are directed to a menu of options depending on their
     * account type (organizer, speaker, or attendee)
     * @return True if the user wishes to terminate the program
     */
    public boolean runStartMenu() {
        presenter.preUserInput();
        String command = input.nextLine();
        boolean programEnd;
        while (!(command.equals("0") || command.equals("1") || (command.equals("2")))) {
            presenter.postUserInput();
            command = input.nextLine();
        }
        if (command.equals("0")) {
            programEnd = true;
        } else if (command.equals("1")) {
            LoginController loginController = new LoginController(accountManager, friendManager, conversationManager, eventManager);
            programEnd = loginController.attemptLogin();
        } else {
            RegistrationController registrationController = new RegistrationController(accountManager, friendManager, conversationManager, eventManager);
            programEnd = registrationController.attemptRegister();
        }
        return programEnd;
    }
}