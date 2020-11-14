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
    
    public void createAccount(){
        // Obtain type of Account to create.
        boolean accountSelected; Integer accountType; String prompt; int firstMessage = 0;
        System.out.println("What type of account do you want to create?");
        do {
            prompt = firstMessage == 0 ? "(1 = Attendee; 2 = Organizer): " : "Invalid input, please enter again: ";
            System.out.print(prompt);
            accountType = input.nextInt();
            accountSelected = accountType.equals(1) || accountType.equals(2);
            firstMessage = !accountSelected ? 1 : 0;
        }
        while (!accountSelected);
        // Run Account Creation methods.
        if (accountType == 1) { createNewAttendee(); }
        else { createNewOrganizer(); }
    }
    
    private void createNewAttendee(){
        // Obtain Attendee information
        String[] info = accountInformation();
        // Add new Attendee and run its Controller
        accountManager.AddNewAttendee(info[0], info[1], info[2], info[3]);
        AttendeeController ac = new AttendeeController(info[0], eventManager, conversationManager, friendManager, signupManager);
        ac.runInteraction();
    }
    
    private void createNewOrganizer(){
        // Evaluate Organizer creation password
        boolean correctOrganizerPassword; int firstMessage = 0; String inputPassword; String prompt;
        do {
            prompt = firstMessage == 0 ? "Please enter the Organizer password: " : "Incorrect password, please enter again: ";
            System.out.print(prompt);
            inputPassword = input.nextLine();
            correctOrganizerPassword = organizerPassword.equals(inputPassword);
            firstMessage = !correctOrganizerPassword ? 1 : 0;
        }
        while (!correctOrganizerPassword);
        // Obtain Organizer information
        String[] info = accountInformation();
        // Add new Organizer and run its Controller
        accountManager.AddNewOrganizer(info[0], info[1], info[2], info[3]);
        OrganizerController oc = new OrganizerController(info[0], eventManager, accountManager, conversationManager, friendManager);
        oc.runInteraction();
    }

    private String[] accountInformation(){
        // Evaluate username creation
        boolean usernamePicked; int firstMessage = 0; String username; String prompt;
        do {
            prompt = firstMessage == 0 ? "Please enter your username:" : "The username already exists, please enter another one:";
            System.out.print(prompt);
            username = input.nextLine();
            usernamePicked = accountManager.doesNotContainAccount(username);
            firstMessage = !usernamePicked ? 1 : 0;
        }
        while (!usernamePicked);
        // Obtain rest of information and bundle into Tuple of 4
        System.out.print("Please enter your first name: ");
        String firstname = input.nextLine();
        System.out.print("Please enter your last name: ");
        String lastname = input.nextLine();
        System.out.print("Please enter your password: ");
        String password = input.nextLine();
        return new String[]{username, password, firstname, lastname};
    }
}
