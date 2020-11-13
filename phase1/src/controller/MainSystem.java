package controller;

import entities.Account;
import entities.Attendee;
import entities.Organizer;
import entities.Speaker;
import gateway.DataManager;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;

import java.util.Scanner;

public class MainSystem {
    private AccountManager accountManager;
    private EventManager eventManager;
    private DataManager dataManager;
    private ConversationManager conversationManager;
    Scanner input = new Scanner(System.in);
    private LoginController loginController;
    private SignupController signupController;

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
            signupController.signup();
        }
        else{
            loginController.login();
        }
    }


    public void run(){
        System.out.println("Print enter the address for AccountManager");
        String am = input.nextLine();
        System.out.println("Print enter the address for EventManager");
        String em = input.nextLine();
        dataManager = new DataManager(am, em);
        eventManager = dataManager.readEventManager();
        accountManager = dataManager.readAccountManager();
        conversationManager = dataManager.readConversationManager();
        loginController = new LoginController(accountManager, eventManager, input);
        signupController = new SignupController(accountManager, eventManager, input);
        startSystem();
        dataManager.saveEventManager(eventManager);
        dataManager.saveAccountManager(accountManager);
    }

    public static void main(String[] args){
        MainSystem ms = new MainSystem();
        ms.run();
    }


}