package controller;

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

    public void messageAllSpeakers(String message) {
        Iterator<String> speakerusernameiterator = this.accountmanager.speakerUsernameIterator();
        while(speakerusernameiterator.hasNext()){
            conversationManager.sendMessage(this.username, speakerusernameiterator.next(), message);
        }
    }

    public void messageSpeaker(String message, String speaker) {
        conversationManager.sendMessage(this.username, speaker, message);
    }

    public void messageAllAttendees(String message) {
        Iterator<String> attendeeusernameiterator = this.accountmanager.attendeeUsernameIterator();
        while(attendeeusernameiterator.hasNext()){
            conversationManager.sendMessage(this.username, attendeeusernameiterator.next(), message);
        }
    }

    public void messageAttendee(String message, String attendeeUsername) {
        conversationManager.sendMessage(this.username, attendeeUsername, message);
    }



}
