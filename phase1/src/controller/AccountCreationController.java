package controller;

import use_cases.*;

import java.util.Scanner;

public class AccountCreationController {
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    Scanner input;
    private String organizerPassword = "123456"; //we can move this to eventmanager, have an extra constructor for it

    public AccountCreationController(AccountManager am, EventManager em, ConversationManager cm, FriendManager fm, Scanner input){
        this.accountManager = am;
        this.eventManager = em;
        this.conversationManager = cm;
        this.friendManager = fm;
        signupManager = new SignupManager(eventManager);
        this.input = input;
    }
    public void signup(){
        System.out.println("What type of account you want to create?");
        System.out.println("Enter 1 for Attendee; enter 2 for Organizer");
        String in = input.nextLine();
        boolean valid = false;
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
        AttendeeController ac = new AttendeeController(info[0], eventManager, conversationManager, friendManager, signupManager);
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
        OrganizerController oc = new OrganizerController(info[0],
                eventManager, accountManager, conversationManager, friendManager);
        oc.runOrganizerInteraction();
    }

    private String[] accountInformation(){
        boolean usernamePicked;
        int firstMessage = 0;
        String username;
        String prompt;
        do {
            prompt = firstMessage == 0 ? "Please enter your username:" : "The username already exists, please enter another one:";
            System.out.println(prompt);
            username = input.nextLine();
            usernamePicked = accountManager.doesNotContainAccount(username);
            firstMessage = !usernamePicked ? 1 : 0;
        }
        while (!usernamePicked);
        System.out.print("Please enter your first name: ");
        String firstname = input.nextLine();
        System.out.print("Please enter your last name: ");
        String lastname = input.nextLine();
        System.out.print("Please enter your password: ");
        String password = input.nextLine();
        return new String[]{username, password, firstname, lastname};
    }
}
