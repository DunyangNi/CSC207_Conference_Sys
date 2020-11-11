package controller;

import use_cases.AccountManager;
import use_cases.EventManager;

import java.util.Scanner;

public class SignupController {
    private AccountManager accountManager;
    private EventManager eventManager;
    Scanner input;
    private String organizerPassword = "123456"; //we can move this to eventmanager, have an extra constructor for it

    public SignupController(AccountManager am, EventManager em, Scanner input){
        this.accountManager = am;
        this.eventManager = em;
        this.input = input;
    }
    public void signup(){
        System.out.println("What type of account you want to create?");
        System.out.println("Enter 1 for Attendee; enter 2 for Organizer");
        String in = input.nextLine();
        Boolean valid = false;
        while (!valid){
            if(in.equals("1")){
                signupAttendee();
                valid = true;
            }
            else if(in.equals("2")){
                signupOrganizer();
                valid = true;
            }
            else{
                System.out.println("Invalid input, please enter again.");
            }
        }
    }
    private void signupAttendee(){
        String[] info = accountInformation();
        accountManager.AddNewAttendee(info[0], info[1], info[2], info[3]);
        AttendeeController ac = new AttendeeController(info[0], eventManager, accountManager);
        ac.runAttendeeInteraction();
    }
    private void signupOrganizer(){
        Boolean valid = false;
        System.out.println("Please enter the Organizer Password:");
        String password = input.nextLine();
        if (password.equals(organizerPassword)) valid = true;
        while (!valid){
            System.out.println("Incorrect password, please enter again:");
            password = input.nextLine();
            if (password.equals(organizerPassword)) valid = true;
        }
        String[] info = accountInformation();
        accountManager.AddNewOrganizer(info[0], info[1], info[2], info[3]);
        OrganizerController oc = new OrganizerController(info[0], eventManager, accountManager);
        oc.runOrganizerInteraction();
    }

    private String[] accountInformation(){
        Boolean valid = false;
        System.out.println("Please enter your username:");
        String username = input.nextLine();
        if(!accountManager.containsAccount(username)) valid = true;
        while (!valid){
            System.out.println("The username already exists, please enter another one:");
            username = input.nextLine();
            if(!accountManager.containsAccount(username)) valid = true;
        }
        System.out.println("Please enter your first name:");
        String firstname = input.nextLine();
        System.out.println("Please enter your last name:");
        String lastname = input.nextLine();
        System.out.println("Please enter your password:");
        String password = input.nextLine();
        String[] info = {username, password, firstname, lastname};
        return info;
    }
}
