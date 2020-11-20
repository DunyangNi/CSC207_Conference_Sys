package presenter;

import java.util.Set;

public interface Presenter {

    //Account methods
    void displayPrompt(String output);

    void displayUserPassPrompt();

    void displayContactsPrompt(String action);

    void displayAccountList(Set<String> accounts);

    void displayContactList(String user);

    void displayConversations(String empty, Set<String> recipients);

    void displayConversationsErrors(String condition);

    void displayMessagingPrompt(String action);


    //Organizer methods
    void displayOrganizerMenu();

    void displayRoomRegistration();

    void displayEventPrompt(String action);


    //Speaker methods
    void displaySpeakerMenu();

    void displayTalkSchedule();

    void displaySpeakerTalksSchedule(String speaker);


    //Attendee methods
    void displayAttendeeMenu();

    void displayAttendeeTalkSchedule(String attendee);

    void displayTalkPrompt(String action);
}
