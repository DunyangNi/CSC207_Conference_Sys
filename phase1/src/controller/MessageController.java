package controller;

import use_cases.ConversationManager;
import use_cases.AccountManager;
import use_cases.EventManager;

import java.util.*;

public class MessageController {
    protected String username;
    protected AccountManager accountManager;
    protected ConversationManager conversationManager;
    protected EventManager eventManager;

    public MessageController(String username, AccountManager accountManager, ConversationManager conversationManager,
                             EventManager eventManager){
        this.username = username;
        this.accountManager = accountManager;
        this.conversationManager = conversationManager;
        this.eventManager = eventManager;
    }

    public void messageAccount(String message, String account) {
        try{
            conversationManager.sendMessage(this.username, account, message);
        }
        catch(Exception e){
            System.out.println("");
            System.out.println("Something went wrong with messageAccount while messaging. Try again.");
            System.out.println("");
        }
    }

    public void messageSpeaker(String message, String speaker) {
        if (accountManager.containsSpeaker(speaker)){
            messageAccount(message, speaker);
        }
        else {
            System.out.println("");
            System.out.println("This recipient is not a speaker. Try again.");
            System.out.println("");
        }

    }

    public void messageAttendee(String message, String attendeeUsername) {
        if (accountManager.containsAttendee(attendeeUsername)){
            messageAccount(message, attendeeUsername);
        }
        else {
            System.out.println("");
            System.out.println("This recipient is not an attendee. Try again.");
            System.out.println("");
        }
    }


    public void messageAllSpeakers(String message) {
        try{
            Iterator<String> speakerusernameiterator = this.accountManager.speakerUsernameIterator();
            while(speakerusernameiterator.hasNext()){
                conversationManager.sendMessage(this.username, speakerusernameiterator.next(), message);}
        }
        catch(Exception e) {
            System.out.println("");
            System.out.println("Something went wrong. Please try again.");
            System.out.println("");
        }
    }

    public void messageAllAttendees(String message) {
        try{
            Iterator<String> attendeeusernameiterator = this.accountManager.attendeeUsernameIterator();
            while(attendeeusernameiterator.hasNext()){
                conversationManager.sendMessage(this.username, attendeeusernameiterator.next(), message);}
        }
        catch(Exception e) {
            System.out.println("");
            System.out.println("Something went wrong. Please try again.");
            System.out.println("");
        }
    }

    public void messageAttendeesAtTalks(ArrayList<Integer> selectedSpeakerTalks, String message) {
        try {
            Set<String> selectedAttendeeUsernames = new HashSet<>();
            for (Integer id : selectedSpeakerTalks) {
                if (eventManager.isTalk(id)) {
                    selectedAttendeeUsernames.addAll(eventManager.fetchTalkAttendeeList(id));
                }
            }
            for (String attendeeUsername : selectedAttendeeUsernames) {
                conversationManager.sendMessage(this.username, attendeeUsername, message);
            }
        }
        catch(Exception e) {
            System.out.println("\nSomething went wrong. Please enter valid input.\n");
        }
    }
}
