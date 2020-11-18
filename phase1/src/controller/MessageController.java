package controller;

import use_cases.ConversationManager;
import use_cases.AccountManager;
import use_cases.EventManager;
import use_cases.SignupManager;

import java.util.*;

public class MessageController {
    protected String username;
    protected AccountManager accountManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;
    protected SignupManager signupManager;

    public MessageController(String username, AccountManager accountManager, ConversationManager conversationManager,
                             EventManager eventManager, SignupManager signupManager){
        this.username = username;
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
        this.signupManager = signupManager;
    }

    public void messageAccount(String message, String account) {
        try{
            conversationManager.sendMessage(this.username, account, message);
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("Something went wrong with messageAccount while messaging. Try again.");
        }
    }

    public void messageSpeaker(String message, String speaker) {
        if (accountManager.containsSpeaker(speaker)){
            messageAccount(message, speaker);
        }
        else {
            System.out.println("This speaker does not exist.");
        }

    }

    public void messageAttendee(String message, String attendeeUsername) {
        if (accountManager.containsAttendee(attendeeUsername)){
            messageAccount(message, attendeeUsername);
        }
        else {
            System.out.println("This attendee does not exist.");
        }
    }


    public void messageAllSpeakers(String message) {
        try {
            Iterator<String> speakerUsernameIterator = this.accountManager.speakerUsernameIterator();
            if (!speakerUsernameIterator.hasNext())
                System.out.println("There are no speakers to message."); // f
            while(speakerUsernameIterator.hasNext()) {
                messageSpeaker(message, speakerUsernameIterator.next());
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Something went wrong. Please try again.");
        }
    }

    public void messageAllAttendees(String message) {
        try {
            Iterator<String> attendeeUsernameIterator = this.accountManager.attendeeUsernameIterator();
            if (!attendeeUsernameIterator.hasNext())
                System.out.println("There are no attendees to message."); // f
            while (attendeeUsernameIterator.hasNext()) {
                messageAttendee(message, attendeeUsernameIterator.next());
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Something went wrong. Please try again.");
        }
    }

    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) {
        try {
            Set<String> selectedAttendeeUsernames = new HashSet<>();
            for (Integer id : selectedSpeakerTalks) {
                if (eventManager.isTalk(id))
                    selectedAttendeeUsernames.addAll(signupManager.fetchTalkAttendeeList(id));
            }
            if (selectedAttendeeUsernames.isEmpty())
                System.out.println("There are no attendees to message."); // f
            for (String attendeeUsername : selectedAttendeeUsernames) {
                messageAttendee(message, attendeeUsername);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString() + "\nSomething went wrong. Please enter valid input.\n");
        }
    }
}
