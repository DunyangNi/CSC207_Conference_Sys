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
        catch(ObjectNotFoundException e){
            System.out.println("");
            System.out.println("This recipient does not exist. Try again.");
            System.out.println("");
        }
        catch(Exception e){
            System.out.println("");
            System.out.println("Something went wrong. Try again.");
            System.out.println("");
        }
    }

    public void messageSpeaker(String message, String speaker) {
        messageAccount(message, speaker);
    }

    public void messageAttendee(String message, String attendeeUsername) {
        messageAccount(message, attendeeUsername);
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
