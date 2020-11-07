package controller;

import entities.Account;
import entities.Attendee;
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
        if(in == "N"){
            return; //I need to return a controller
        }
        else if(in == "Y"){
            System.out.println("Please enter your user name:");
            String username = input.nextLine();
            Account account = null;
            boolean user = false;
            if(accountManager.containsAttendee(username)){user=true;account=accountManager.fetchAttendee(username);}
            if(accountManager.containsOrganizer(username)){user=true;account=accountManager.fetchOrganizer(username);}
            if(accountManager.containsSpeaker(username)){user=true;account=accountManager.fetchOrganizer(username);}
            while (user){
                System.out.println("This username does not exist, please enter again.");
                username = input.nextLine();
                if(accountManager.containsAttendee(username)) {
                    user=true;account=accountManager.fetchAttendee(username);
                    password(account);
                    AttendeeController ac= new AttendeeController(username, eventManager, accountManager);
                    ac.runAttendeeInteraction();

                }
                if(accountManager.containsOrganizer(username)) {
                    user=true;account=accountManager.fetchOrganizer(username);
                    password(account);
                    OrganizerController oc = new OrganizerController(username, eventManager, accountManager);
                    oc.runOrganizerInteraction();
                }
                if(accountManager.containsSpeaker(username)) {
                    user=true;account=accountManager.fetchSpeaker(username);
                    password(account);
                    SpeakerController sc = new SpeakerController(username, eventManager, accountManager);
                    sc.runSpeakerInteraction();
                }
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