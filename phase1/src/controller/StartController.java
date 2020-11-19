package controller;

import presenter.Presenter;
import presenter.TextPresenter;
import use_cases.*;

import java.util.Scanner;

public class StartController {
    private final Scanner input = new Scanner(System.in);
    private final AccountManager accountManager;
    private final FriendManager friendManager;
    private final ConversationManager conversationManager;
    private final EventManager eventManager;
    private final SignupManager signupManager;
    private final Presenter presenter = new TextPresenter();

    StartController(AccountManager accountManager, FriendManager friendManager, ConversationManager conversationManager, EventManager eventManager, SignupManager signupManager) {
        this.accountManager = accountManager;
        this.friendManager = friendManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
    }

    public boolean runStartMenu() {
        presenter.displayPrompt("[START MENU]");
        presenter.displayPrompt("0 = Exit program:\n1 = Login to your account:\n2 = Register a new account:");
        String command = input.nextLine();
        boolean programEnd;
        while (!(command.equals("0") || command.equals("1") || (command.equals("2")))) {
            presenter.displayPrompt("Invalid input, please try again.");
            command = input.nextLine();
        }
        if (command.equals("0")) {
            programEnd = true;
        } else if (command.equals("1")) {
            LoginController loginController = new LoginController(accountManager, friendManager, conversationManager, eventManager, signupManager);
            programEnd = loginController.attemptLogin();
        } else {
            RegistrationController registrationController = new RegistrationController(accountManager, friendManager, conversationManager);
            programEnd = registrationController.attemptRegister();
        }
        return programEnd;
    }
}