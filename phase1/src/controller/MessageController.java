package controller;

import Throwables.ObjectNotFoundException;
import presenter.Presenter;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;
import use_cases.SignupManager;
import use_cases.AccountManager;

import java.util.Iterator;

public class MessageController {
    protected String username;
    protected AccountManager accountmanager;
    protected ConversationManager conversationManager;
    protected Presenter presenter;

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
        if (accountmanager.containsSpeaker(speaker)){
            messageAccount(message, speaker);
        }
        else {
            System.out.println("");
            System.out.println("This recipient is not a speaker. Try again.");
            System.out.println("");
        }

    }

    public void messageAttendee(String message, String attendeeUsername) {
        if (accountmanager.containsAttendee(attendeeUsername)){
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
            Iterator<String> speakerusernameiterator = this.accountmanager.speakerUsernameIterator();
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
            Iterator<String> attendeeusernameiterator = this.accountmanager.attendeeUsernameIterator();
            while(attendeeusernameiterator.hasNext()){
                conversationManager.sendMessage(this.username, attendeeusernameiterator.next(), message);}
        }
        catch(Exception e) {
            System.out.println("");
            System.out.println("Something went wrong. Please try again.");
            System.out.println("");
        }
    }
}
