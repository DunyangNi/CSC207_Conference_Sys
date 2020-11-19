package presenter;
import use_cases.*;
import entities.*;
import java.util.*;

import java.util.Calendar;

public abstract class Presenter {
    // TODO: 11/17/20 Consider whether these fields are necessary or what other fields might be required
    private EventManager eventmanager;
    private SignupManager signupManager;
    private FriendManager friendManager;

    // TODO: 11/16/20 Find a better way to do this?
    public Presenter() {
    }

    public Presenter(EventManager eventManager, FriendManager friendManager, SignupManager signupManager) {
        this.eventmanager = eventManager;
        this.signupManager = signupManager;
        this.friendManager = friendManager;
    }

    public abstract void displayPrompt(String output);

    public abstract void displayOrganizerMenu();

    public abstract void displayRoomRegistration();

    public abstract void displayEventPrompt(String action);

    public abstract void displayUserPassPrompt();

    public abstract void displayMessagingPrompt(String action);

    public abstract void displayContactsPrompt(String action);

    public abstract void displayConversations(Set<String> recipients);

    public abstract void displaySpeakerMenu();

    public abstract void displayAttendeeMenu();

    public abstract void displayTalkSchedule();

    public abstract void displayAttendeeTalkSchedule(String attendee);

    public abstract void displaySpeakerTalksSchedule(String speaker);

    public abstract void displayContactList(String user);

    public abstract void displayTalkPrompt(String action);

}
