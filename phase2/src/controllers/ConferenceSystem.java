package controllers;
import gateway.*;
import use_cases.*;
import views.start.StartView;

public class ConferenceSystem {

    /**
     * Runs the entire conference program by calling the run method in this class
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ConferenceSystem conferenceSystem = new ConferenceSystem();
        conferenceSystem.run();
    }

    /**
     * Runs the entire conference program starting with the login screen
     */
    public void run() {
        AccountDataManager accountDataManager = new AccountDataManager();
        EventDataManager eventDataManager = new EventDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        FriendDataManager friendDataManager = new FriendDataManager();

        AccountManager am = accountDataManager.readManager();
        FriendManager fm = friendDataManager.readManager();
        ConversationManager cm = conversationDataManager.readManager();
        EventManager em = eventDataManager.readManager();

        boolean programEnd = false;
        while (!programEnd) {
            StartView view = new StartView(am, fm, cm, em);
            programEnd = view.startMenu();
        }
    }
}