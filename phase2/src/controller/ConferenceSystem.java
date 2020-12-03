package controller;
import gateway.*;
import use_cases.*;
import views.StartView;

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

        AccountManager accountManager = accountDataManager.readManager();
        FriendManager friendManager = friendDataManager.readManager();
        ConversationManager conversationManager = conversationDataManager.readManager();
        EventManager eventManager = eventDataManager.readManager();

        boolean programEnd = false;
        while (!programEnd) {
            StartView view = new StartView(accountManager, friendManager, conversationManager, eventManager);
            programEnd = view.viewStartMenu();
        }
    }
}