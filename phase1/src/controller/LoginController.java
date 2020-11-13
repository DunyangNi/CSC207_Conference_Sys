package controller;

import entities.Organizer;
import entities.Speaker;
import use_cases.*;

import java.util.Scanner;

public class LoginController {
    private AccountManager accountManager;
    private EventManager eventManager;
    private ConversationManager conversationManager;
    private FriendManager friendManager;
    private SignupManager signupManager;
    Scanner input;

    public LoginController(AccountManager am, EventManager em, ConversationManager cm, FriendManager fm, Scanner input){
        this.accountManager = am;
        this.eventManager = em;
        this.conversationManager = cm;
        this.friendManager = fm;
        signupManager = new SignupManager(eventManager);
        this.input = input;
    }

    public void login(){
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
    private void password(String username){
        System.out.println("Please enter the password:");
        String password = input.nextLine();
        while(!accountManager.checkPassword(username,password)){
            System.out.println("Wrong! Please enter again:");
            password = input.nextLine();
        }
    }

    private boolean loginHelper(String username){
        if (accountManager.containsAttendee(username)) {
            password(username);
            AttendeeController ac = new AttendeeController(username, eventManager, conversationManager, friendManager, signupManager);
            ac.runAttendeeInteraction();
            return true;
        }
        if (accountManager.containsOrganizer(username)) {
            Organizer account = accountManager.fetchOrganizer(username);
            password(username);
            OrganizerController oc = new OrganizerController(
                    username, eventManager, accountManager, conversationManager, friendManager);
            oc.runOrganizerInteraction();
            return true;
        }
        if (accountManager.containsSpeaker(username)) {
            Speaker account = accountManager.fetchSpeaker(username);
            password(username);
            SpeakerController sc = new SpeakerController(
                    username, eventManager, accountManager, conversationManager, friendManager);
            sc.runSpeakerInteraction();
            return true;
        }
        return false;
    }
}
