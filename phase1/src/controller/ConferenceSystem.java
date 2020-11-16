package controller;

import gateway.DataManager;
import presenter.Presenter;
import use_cases.*;

import java.util.Scanner;

public class ConferenceSystem {
    private final Scanner input = new Scanner(System.in);
    private Presenter presenter = new Presenter();
    private LoginController loginController;
    private AccountCreationController accountCreationController;

    public static void main(String[] args) {
        ConferenceSystem ms = new ConferenceSystem();
        ms.run();
    }

    public void loginPrompt() {
        //Prompt attemptLogin or signup
        presenter.displayPrompt("Enter '1' to login to your account:\nEnter '2' to signup for a new account:");
        String command = input.nextLine();

        //Invalid input prompt
        while (!(command.equals("1") || (command.equals("2")))) {
            presenter.displayInvalidInputPrompt("Invalid input, please try again.");
            command = input.nextLine();
        }

        //Run attemptLogin or signup
        if (command.equals("1")) {
            loginController.attemptLogin();
        } else {
            accountCreationController.attemptSignup();
        }
    }

    public void run() {
        // Obtain filepath of all .ser files
        presenter.displayPrompt("Enter the filepath for AccountManager");
        String am = input.nextLine();
        presenter.displayPrompt("Enter the filepath for FriendManager:");
        String fm = input.nextLine();
        presenter.displayPrompt("Enter the filepath for ConversationManager:");
        String cm = input.nextLine();
        presenter.displayPrompt("Enter the filepath for EventManager:");
        String em = input.nextLine();
        presenter.displayPrompt("Enter the filepath for SignupManager:");
        String sm = input.nextLine();

        // Deserialization
        DataManager dataManager = new DataManager(am, fm, cm, em, sm);
        AccountManager accountManager = dataManager.readAccountManager();
        FriendManager friendManager = dataManager.readFriendManager();
        ConversationManager conversationManager = dataManager.readConversationManager();
        EventManager eventManager = dataManager.readEventManager();
        SignupManager signupManager = dataManager.readSignupManager();

        // Initiation
        loginController = new LoginController(accountManager, friendManager, conversationManager, eventManager, signupManager);
        accountCreationController = new AccountCreationController(accountManager, friendManager, conversationManager, eventManager, signupManager);
        loginPrompt();

        // Saving changes
        dataManager.saveManager("EventManager", em, eventManager);
        dataManager.saveManager("AccountManager", am, accountManager);
        dataManager.saveManager("ConversationManager", cm, conversationManager);
        dataManager.saveManager("FriendManager", fm, friendManager);
        dataManager.saveManager("SignupManager", sm, signupManager);
    }
}