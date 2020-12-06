package controllers;
import gateway.*;
import use_cases.*;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;
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
        ContactDataManager contactDataManager = new ContactDataManager();

        AccountManager am = accountDataManager.readManager();
        ContactManager fm = contactDataManager.readManager();
        ConversationManager cm = conversationDataManager.readManager();
        EventManager em = eventDataManager.readManager();

        DataManager dm = new DataManager(am, fm, cm, em);

        boolean programEnd = false;
        while (!programEnd) {
            StartView view = new StartView(dm);
            programEnd = view.startMenu();
        }
    }
}