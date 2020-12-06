package views.account;

import enums.OrganizerEnum;
import gateway.DataManager;
import presenters.account.OrganizerPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import views.ContactView;
import views.message.ConversationView;
import views.event.LocationView;
import views.event.EventView;
import views.message.MessageView;
import views.start.RegistrationView;

import java.util.*;

public class OrganizerView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final OrganizerPresenter presenter = new OrganizerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public OrganizerView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
    }

    public void viewOrganizerMenu() {
        boolean loggedIn = true;
        presenter.startPrompt();
        presenter.displayOrganizerMenu();

        while (loggedIn) {
            String userCommand = userInput.nextLine();
            OrganizerEnum enumCommand = OrganizerEnum.fromString(userCommand);

            switch (enumCommand) {
                // TODO: 12/04/20 Enable exit
//                case EXIT:
//                    loggedIn = false;
//                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case NEW_SPEAKER:
                    RegistrationView registrationView = new RegistrationView(dm);
                    registrationView.accountInfoMenu("2");
                    break;
                case VIEW_ALL_ACCOUNTS:
                    Set<String> accounts = am.getAccountHashMap().keySet();
                    presenter.accountList(accounts);
                    break;
                case ADD_CONTACT:
                    ContactView contactView = new ContactView(username, fm);
                    contactView.viewAddFriendMenu();
                    break;
                case REMOVE_CONTACT:
                    contactView = new ContactView(username, fm);
                    contactView.viewRemoveFriendMenu();
                    break;
                case VIEW_CONTACTS:
                    contactView = new ContactView(username, fm);
                    contactView.viewFriendList();
                    break;
                case MESSAGE_SPEAKER:
                case MESSAGE_ATTENDEE:
                case MESSAGE_ALL_SPEAKERS:
                case MESSAGE_ALL_ATTENDEES:
                    MessageView messageView = new MessageView(dm);
                    messageView.message(enumCommand);
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(dm);
                    conversationView.conversations();
                    break;
                case ADD_ROOM:
                    LocationView locationView = new LocationView(dm);
                    locationView.addRoom();
                    break;
                case VIEW_ROOMS:
                    locationView = new LocationView(dm);
                    locationView.rooms();
                    break;
                case ADD_EVENT:
                    EventView eventView = new EventView(dm);
                    eventView.eventCreation();
                    break;
                case CANCEL_EVENT:
                    eventView = new EventView(dm);
                    eventView.eventCancellation();
                    break;
                case RESCHEDULE_EVENT:
                    eventView = new EventView(dm);
                    eventView.eventReschedule();
                    break;
                case VIEW_SCHEDULE:
                    HashMap<String[], Calendar> allTalks = em.fetchSortedTalks();
                    presenter.displayTalkSchedule(allTalks);
                    break;
                case VIEW_MENU:
                    presenter.displayOrganizerMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
            } if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
    }
}
