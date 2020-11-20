package presenter;
import use_cases.*;
import entities.*;
import java.util.*;

import java.util.Calendar;

public abstract class Presenter {

    public Presenter() {
    }

    public Presenter(EventManager eventManager, FriendManager friendManager, SignupManager signupManager) {
    }

    public abstract void displayPrompt(String output);

    public abstract void displayAccountList(Set<String> allAccts);

    public abstract void displayOrganizerMenu();

    public abstract void displayRoomRegistration();

    public abstract void displayEventPrompt(String action);

    public abstract void displayUserPassPrompt();

    public abstract void displayMessagingPrompt(String action);

    public abstract void displayContactsPrompt(String action);

    public abstract void displayConversations(String empty, Set<String> recipients);

    public abstract void displayConversationsErrors(String condition);

    public abstract void displaySpeakerMenu();

    public abstract void displayAttendeeMenu();

    public abstract void displayTalkSchedule();

    public abstract void displayAttendeeTalkSchedule(String attendee);

    public abstract void displaySpeakerTalksSchedule(String speaker);

    public abstract void displayContactList(String user);

    public abstract void displayTalkPrompt(String action);
}
