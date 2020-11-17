package controller;

import gateway.DataManager;
import presenter.Presenter;
import use_cases.*;

import java.util.Scanner;

public class ConferenceSystem {
    private final Scanner input = new Scanner(System.in);
    private Presenter presenter = new Presenter();
    private LoginController loginController;
    private RegisterController registerController;

    public static void main(String[] args) {
        ConferenceSystem ms = new ConferenceSystem();
        ms.run();
    }

    public void loginPrompt() {
        //Prompt attemptLogin or signup
        presenter.displayPrompt("Enter '1' to login to your account:\nEnter '2' to register a new account:");
        String command = input.nextLine();

        //Invalid input prompt
        while (!(command.equals("1") || (command.equals("2")))) {
            presenter.displayPrompt("Invalid input, please try again.");
            command = input.nextLine();
        }

        //Run attemptLogin or signup
        if (command.equals("1")) {
            loginController.attemptLogin();
        } else {
            registerController.attemptRegister();
        }
    }

    public void run() {
        // Obtain filepath of all .ser files
//        presenter.displayPrompt("Enter the filepath for AccountManager");
//        String am = input.nextLine();
//        presenter.displayPrompt("Enter the filepath for FriendManager:");
//        String fm = input.nextLine();
//        presenter.displayPrompt("Enter the filepath for ConversationManager:");
//        String cm = input.nextLine();
//        presenter.displayPrompt("Enter the filepath for EventManager:");
//        String em = input.nextLine();
//        presenter.displayPrompt("Enter the filepath for SignupManager:");
//        String sm = input.nextLine();

        // Deserialization
        DataManager dataManager = new DataManager("AccountManager", "FriendManager", "ConversationManager", "EventManager", "SignupManager");
        AccountManager accountManager = dataManager.readAccountManager();
        FriendManager friendManager = dataManager.readFriendManager();
        ConversationManager conversationManager = dataManager.readConversationManager();
        EventManager eventManager = dataManager.readEventManager();
        SignupManager signupManager = dataManager.readSignupManager();

        // Initiation
        loginController = new LoginController(accountManager, friendManager, conversationManager, eventManager, signupManager);
        registerController = new RegisterController(accountManager, friendManager, conversationManager, eventManager, signupManager);
        loginPrompt();

        // Saving changes
        dataManager.saveManager("EventManager", "EventManager", eventManager);
        dataManager.saveManager("AccountManager", "AccountManager", accountManager);
        dataManager.saveManager("ConversationManager", "ConversationManager", conversationManager);
        dataManager.saveManager("FriendManager", "FriendManager", friendManager);
        dataManager.saveManager("SignupManager", "SignupManager", signupManager);
    }
}