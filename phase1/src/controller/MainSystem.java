package controller;

import entities.Account;
import entities.Attendee;
import entities.Organizer;
import entities.Speaker;
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

        System.out.println("Do you already have an account?");
        System.out.println("Enter 'Y' to log in.");
        System.out.println("Enter 'N' to sign up.");
        String in = input.nextLine();
        while(!(in.equals("Y")||in.equals("N"))){
            System.out.println("Invalid input");
            in = input.nextLine();
        }
        if(in.equals("N")){
            return; //I need to return a controller
        }
        else{
            System.out.println("Please enter your username:");
            String username = input.nextLine();
            boolean user = false;
            user = loginHelper(username);
            while (!user){
                System.out.println("This username does not exist, please enter again.");
                username = input.nextLine();
                loginHelper(username);
            }

        }

    }
    private void password(Account a){
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        while(!password.equals(a.getPassword())){
            System.out.println("Wrong! Please enter again:");
            password = input.nextLine();
        }
    }

    private boolean loginHelper(String username){
        if(accountManager.containsAttendee(username)) {
            Attendee account=accountManager.fetchAttendee(username);
            password(account);
            AttendeeController ac = new AttendeeController(username, eventManager, accountManager);
            ac.runAttendeeInteraction();
            return true;
        }
        if(accountManager.containsOrganizer(username)) {
            Organizer account=accountManager.fetchOrganizer(username);
            password(account);
            OrganizerController oc = new OrganizerController(username, eventManager, accountManager);
            oc.runOrganizerInteraction();
            return true;
        }
        if(accountManager.containsSpeaker(username)) {
            Speaker account=accountManager.fetchSpeaker(username);
            password(account);
            SpeakerController sc = new SpeakerController(username, eventManager, accountManager);
            sc.runSpeakerInteraction();
            return true;
        }
        return false;
    }

    public void run(){
        System.out.println("Print enter the address for AccountManager");
        String am = input.nextLine();
        System.out.println("Print enter the address for EventManager");
        String em = input.nextLine();
        DataManager dataManager = new DataManager(am, em);
        EventManager eventManager = dataManager.readEventManager();
        AccountManager accountManager = dataManager.readAccountManager();
        login();
        dataManager.saveEventManager(eventManager);
        dataManager.saveAccountManager(accountManager);
    }
}