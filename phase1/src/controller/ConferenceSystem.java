package controller;

import gateway.DataManager;
//import presenter.Presenter;
import use_cases.*;

import java.util.Scanner;

public class ConferenceSystem {
//    private final Scanner input = new Scanner(System.in);
//    private Presenter presenter = new Presenter();
//    private LoginController loginController;
//    private RegistrationController registrationController;
    private StartController startController;

    public static void main(String[] args) {
        ConferenceSystem ms = new ConferenceSystem();
        ms.run();
    }

//    public void runStartMenu() {
//        //Prompt attemptLogin or signup
//        presenter.displayPrompt("[START MENU]");
//        presenter.displayPrompt("1 = login to your account:\n2 = to register a new account:");
//        String command = input.nextLine();
//
//        //Invalid input prompt
//        while (!(command.equals("1") || (command.equals("2")))) {
//            presenter.displayPrompt("Invalid input, please try again.");
//            command = input.nextLine();
//        }
//
//        //Run attemptLogin or signup
//        if (command.equals("1")) {
//            loginController.attemptLogin();
//        } else {
//            registrationController.attemptRegister();
//        }
//    }

    public void run() {
        // Deserialization
        DataManager dataManager = new DataManager("AccountManager", "FriendManager", "ConversationManager", "EventManager", "SignupManager");
        AccountManager accountManager = dataManager.readAccountManager();
        FriendManager friendManager = dataManager.readFriendManager();
        ConversationManager conversationManager = dataManager.readConversationManager();
        EventManager eventManager = dataManager.readEventManager();
        SignupManager signupManager = dataManager.readSignupManager();

        // Initiation
        startController = new StartController(accountManager, friendManager, conversationManager, eventManager, signupManager);
        startController.runStartMenu();

        // Saving changes
        dataManager.saveManager("EventManager", "EventManager", eventManager);
        dataManager.saveManager("AccountManager", "AccountManager", accountManager);
        dataManager.saveManager("ConversationManager", "ConversationManager", conversationManager);
        dataManager.saveManager("FriendManager", "FriendManager", friendManager);
        dataManager.saveManager("SignupManager", "SignupManager", signupManager);
    }
}