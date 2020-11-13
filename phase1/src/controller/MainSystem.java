package controller;

import gateway.*;
import use_cases.*;
import java.util.Scanner;

public class MainSystem {
    private AccountManager accountManager;
    private EventManager eventManager;
    private DataManager dataManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    Scanner input = new Scanner(System.in);
    private LoginController loginController;
    private AccountCreationController accountCreationController;

    public void startSystem(){
        System.out.println("Do you already have an account?");
        System.out.println("Enter 'Y' to log in.");
        System.out.println("Enter 'N' to sign up.");
        String in = input.nextLine();
        while(!(in.equals("Y")||in.equals("N"))){
            System.out.println("Invalid input");
            in = input.nextLine();
        }
        if(in.equals("N")){
            accountCreationController.signup();
        }
        else{
            loginController.login();
        }
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
        // Deserialization
        dataManager = new DataManager(am, em, cm, fm);
        eventManager = dataManager.readEventManager();
        accountManager = dataManager.readAccountManager();
        conversationManager = dataManager.readConversationManager();
        friendManager = dataManager.readFriendManager();
        // Initiation
        loginController = new LoginController(accountManager, eventManager, conversationManager, friendManager, input);
        accountCreationController = new AccountCreationController(
                accountManager, eventManager, conversationManager, friendManager, input);
        startSystem();
        // Saving changes
        dataManager.saveEventManager(eventManager);
        dataManager.saveAccountManager(accountManager);
        dataManager.saveConversationManager(conversationManager);
        dataManager.saveFriendManager(friendManager);
    }

    public static void main(String[] args){
        MainSystem ms = new MainSystem();
        ms.run();
    }


}