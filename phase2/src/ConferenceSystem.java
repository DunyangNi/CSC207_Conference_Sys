import enums.ViewEnum;
import gateways.*;
import use_cases.account.AccountManager;
import use_cases.event.EventManager;
import use_cases.event.LocationManager;
import use_cases.message.ContactManager;
import use_cases.message.ConversationManager;
import use_cases.request.RequestManager;
import views.factory.ViewFactory;

public class ConferenceSystem {
    /**
     * Runs the entire conference program by calling the run method in this class
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ConferenceSystem conferenceSystem = new ConferenceSystem();
        conferenceSystem.run();
    }

    /**
     * Runs the entire conference program starting with the Start Menu.
     */
    public void run() {
        DataManager dm = readData();
        ViewFactory viewFactory = new ViewFactory(dm);

        ViewEnum viewEnum = ViewEnum.START;
        while (viewEnum != ViewEnum.EXIT) {
            viewEnum = viewFactory.getView(viewEnum).runView();
        }
    }

    /**
     * Read data from the DataManagers to instantiate Use Case classes which will be used in the program.
     * @return a new Datamanager storing all use case managers which will be used in the program.
     */
    private DataManager readData() {
        AccountDataManager accountDataManager = new AccountDataManager();
        EventDataManager eventDataManager = new EventDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        ContactDataManager contactDataManager = new ContactDataManager();
        LocationDataManager locationDataManager = new LocationDataManager();
        RequestDataManager requestDataManager = new RequestDataManager();

        AccountManager am = accountDataManager.readManager();
        ContactManager fm = contactDataManager.readManager();
        ConversationManager cm = conversationDataManager.readManager();
        EventManager em = eventDataManager.readManager();
        LocationManager lm = locationDataManager.readManager();
        RequestManager rm = requestDataManager.readManager();

        return new DataManager(am, fm, cm, em, lm, rm);
    }
}