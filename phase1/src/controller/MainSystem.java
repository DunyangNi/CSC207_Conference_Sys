package controller;

import entities.Account;
import gateway.DataManager;
import use_cases.AccountManager;
import use_cases.EventManager;

import java.util.Scanner;

public class MainSystem {
    private AccountManager accountManager;
    private EventManager eventManager;
    private DataManager dataManager;
    Scanner input = new Scanner(System.in);
    private void login(){
        boolean user = false;
        System.out.println("Do you already have an account?");
        System.out.println("Enter 'Y' to log in.");
        System.out.println("Enter 'N' to sign up.");
        String in = input.nextLine();
        if(in == "N"){
            return; //I need to return a controller
        }
        else if(in == "Y"){
            System.out.println("Please enter your user name:");
            String username = input.nextLine();
            if(accountManager.contains(username)){user=true;}
            while (user){
                System.out.println("This username does not exist, please enter again.");
                username = input.nextLine();
                if(accountManager.contains(username)){user=true;}
            }
            Account account = password(username);
            if (account.isAttendee()){return;} //I need to return a controller
            else if (account.isOrganizer()){return;} //I need to return a controller

        }

    }
    private Account password(String username){
        Account a = accountManager.getAccountlist().get(username);
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        while(!password.equals(a.getPassword())){
            System.out.println("Wrong! Please enter again:");
            password = input.nextLine();
        }
        return a;
    }
    public void run(){
        System.out.println("Print enter the address for AccountManager");
        String am = input.nextLine();
        System.out.println("Print enter the address for EventManager");
        String em = input.nextLine();
        dataManager = new DataManager(am, em);
        EventManager eventManager = dataManager.readEventManager();
        AccountManager accountManager = dataManager.readAccountManager();
        login(); // it should be controller = login();
    }
}