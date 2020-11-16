package controller;

import Throwables.ObjectNotFoundException;
import gateway.DataManager;
import use_cases.*;

import java.util.Scanner;

public class MainSystem {
    Scanner input = new Scanner(System.in);
    private DataManager dataManager;
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    private LoginController loginController;
    private AccountCreationController accountCreationController;

    public static void main(String[] args) throws InstantiationException, ObjectNotFoundException {
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
//        else accountCreationController.createAccount();
//    }

    public void loginPrompt() {
        //Prompt login or signup
        System.out.println("Enter '0' to login to your account or '1' to signup for a new account.");
        String input = this.input.nextLine();

        //Invalid input prompt
        while (!(input.equals("0") || (input.equals("1")))) {
            System.out.println("Invalid input, please try again.");
            input = this.input.nextLine();
        }

        //Run login or signup
        if (input.equals("0")) {
            loginController.login();
        } else {
            accountCreationController.createAccount();
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
        dataManager = new DataManager(am, fm, cm, em, sm);
        accountManager = dataManager.readAccountManager();
        friendManager = dataManager.readFriendManager();
        conversationManager = dataManager.readConversationManager();
        eventManager = dataManager.readEventManager();
        signupManager = dataManager.readSignupManager();

        // Initiation
        loginController = new LoginController(accountManager, eventManager, conversationManager, friendManager, signupManager, input);
        accountCreationController = new AccountCreationController(accountManager, eventManager, conversationManager, friendManager, signupManager, input);
        loginPrompt();

        // Saving changes
        dataManager.saveManager("EventManager", em, eventManager);
        dataManager.saveManager("AccountManager", am, accountManager);
        dataManager.saveManager("ConversationManager", cm, conversationManager);
        dataManager.saveManager("FriendManager", fm, friendManager);
        dataManager.saveManager("SignupManager", sm, signupManager);
    }


}