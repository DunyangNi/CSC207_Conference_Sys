package controller;

import Throwables.ObjectNotFoundException;
import gateway.*;
import use_cases.*;
import java.util.Scanner;

public class MainSystem {
    private DataManager dataManager;
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    private LoginController loginController;
    private AccountCreationController accountCreationController;
    Scanner input = new Scanner(System.in);

    public void startSystem(){
        // Evaluate choice
        boolean validChoice; String choice; String prompt; int firstMessage = 0;
        System.out.println("Do you already have an account?");
        System.out.println("Enter 'Y' to log into an existing account.");
        System.out.println("Enter 'N' to create a new account.");
        do {
            prompt = firstMessage == 0 ? "" : "Invalid choice, please enter again.";
            System.out.println(prompt); choice = input.nextLine();
            validChoice = choice.equals("Y") || choice.equals("N");
            firstMessage = !validChoice ? 1 : 0;
        }
        while (!validChoice);
        // Execute controller based on choice
        if (choice.equals("Y")) loginController.login();
        else accountCreationController.createAccount();
    }

    public void run(){
        // Obtain filepath of all .ser files
        System.out.println("Print enter the address for AccountManager");
        String am = input.nextLine();
        System.out.println("Print enter the address for EventManager");
        String em = input.nextLine();
        System.out.print("Enter the filepath for ConversationManager: ");
        String cm = input.nextLine();
        System.out.print("Enter the filepath for FriendManager: ");
        String fm = input.nextLine();
        System.out.print("Enter the filepath for SignupManager: ");
        String sm = input.nextLine();
        // Deserialization
        dataManager = new DataManager(am, em, cm, fm, sm);
        eventManager = dataManager.readEventManager();
        accountManager = dataManager.readAccountManager();
        conversationManager = dataManager.readConversationManager();
        friendManager = dataManager.readFriendManager();
        signupManager = dataManager.readSignupManager();
        // Initiation
        loginController = new LoginController(
                accountManager, eventManager, conversationManager, friendManager, signupManager, input);
        accountCreationController = new AccountCreationController(
                accountManager, eventManager, conversationManager, friendManager, signupManager, input);
        startSystem();
        // Saving changes
        dataManager.saveManager("EventManager", em, eventManager);
        dataManager.saveManager("AccountManager", am, accountManager);
        dataManager.saveManager("ConversationManager", cm, conversationManager);
        dataManager.saveManager("FriendManager", fm, friendManager);
        dataManager.saveManager("SignupManager", sm, signupManager);
    }

    public static void main(String[] args) throws InstantiationException, ObjectNotFoundException {
        MainSystem ms = new MainSystem();
        ms.run();
    }


}