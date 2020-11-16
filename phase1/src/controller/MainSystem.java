package controller;

import Throwables.ObjectNotFoundException;
import gateway.DataManager;
import use_cases.*;

import java.util.Scanner;

public class MainSystem {
    private final Scanner input = new Scanner(System.in);
    private LoginController loginController;
    private AccountCreationController accountCreationController;

    public static void main(String[] args) {
        MainSystem ms = new MainSystem();
        ms.run();
    }

//    // Old implementation
//    public void loginPrompt(){
//        // Evaluate choice
//        boolean validChoice; String choice; String prompt; int firstMessage = 0;
//        System.out.println("Do you already have an account?");
//        System.out.println("Enter 'Y' to log into an existing account.");
//        System.out.println("Enter 'N' to create a new account.");
//        do {
//            prompt = firstMessage == 0 ? "" : "Invalid choice, please enter again.";
//            System.out.println(prompt); choice = input.nextLine();
//            validChoice = choice.equals("Y") || choice.equals("N");
//            firstMessage = !validChoice ? 1 : 0;
//        }
//        while (!validChoice);
//        // Execute controller based on choice
//        if (choice.equals("Y")) loginController.login();
//        else accountCreationController.createNewAccount();
//    }

    public void loginPrompt() {
        //Prompt login or signup
        System.out.println("Enter '1' to login to your account.\nEnter '2' to signup for a new account.");
        String command = input.nextLine();

        //Invalid input prompt
        while (!(command.equals("1") || (command.equals("2")))) {
            System.out.println("Invalid input, please try again.");
            command = input.nextLine();
        }

        //Run login or signup
        if (command.equals("1")) {
            loginController.login();
        } else {
            accountCreationController.createNewAccount();
        }
    }

    public void run() {
        // Obtain filepath of all .ser files
        System.out.println("Enter the filepath for AccountManager:");
        String am = input.nextLine();
        System.out.println("Enter the filepath for FriendManager:");
        String fm = input.nextLine();
        System.out.println("Enter the filepath for ConversationManager:");
        String cm = input.nextLine();
        System.out.println("Enter the filepath for EventManager:");
        String em = input.nextLine();
        System.out.println("Enter the filepath for SignupManager:");
        String sm = input.nextLine();

        // Deserialization
        DataManager dataManager = new DataManager(am, fm, cm, em, sm);
        AccountManager accountManager = dataManager.readAccountManager();
        FriendManager friendManager = dataManager.readFriendManager();
        ConversationManager conversationManager = dataManager.readConversationManager();
        EventManager eventManager = dataManager.readEventManager();
        SignupManager signupManager = dataManager.readSignupManager();

        // Initiation
        loginController = new LoginController(accountManager, conversationManager, friendManager, eventManager, signupManager);
        accountCreationController = new AccountCreationController(accountManager, conversationManager, friendManager, eventManager, signupManager);
        loginPrompt();

        // Saving changes
        dataManager.saveManager("EventManager", em, eventManager);
        dataManager.saveManager("AccountManager", am, accountManager);
        dataManager.saveManager("ConversationManager", cm, conversationManager);
        dataManager.saveManager("FriendManager", fm, friendManager);
        dataManager.saveManager("SignupManager", sm, signupManager);
    }


}