package controller;

import presenter.Presenter;
import use_cases.*;

import java.util.Scanner;

public class StartController {
    private final Scanner input = new Scanner(System.in);
    private final AccountManager accountManager;
    private final FriendManager friendManager;
    private final ConversationManager conversationManager;
    private final EventManager eventManager;
    private final SignupManager signupManager;
    private final Presenter presenter = new Presenter();

    StartController(AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager) {
        this.accountManager = accountManager;
        this.friendManager = friendManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
    }

    public void runStartMenu() {
        //Prompt attemptLogin or signup
        presenter.displayPrompt("[START MENU]");
        presenter.displayPrompt("1 = login to your account:\n2 = to register a new account:");
        String command = input.nextLine();

        //Invalid input prompt
        while (!(command.equals("1") || (command.equals("2")))) {
            presenter.displayPrompt("Invalid input, please try again.");
            command = input.nextLine();
        }

        //Run attemptLogin or signup
        if (command.equals("1")) {
            LoginController loginController = new LoginController(accountManager, friendManager, conversationManager, eventManager, signupManager);
            loginController.attemptLogin();
        } else {
            RegistrationController registrationController = new RegistrationController(accountManager, friendManager, conversationManager, eventManager, signupManager);
            registrationController.attemptRegister();
        }
    }
}
