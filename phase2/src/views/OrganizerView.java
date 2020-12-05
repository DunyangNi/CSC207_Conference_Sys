package views;

import enums.OrganizerCommand;
import presenter.OrganizerPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.*;

public class OrganizerView {
    private final String username;
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final OrganizerPresenter presenter = new OrganizerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public OrganizerView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
    }

    public void viewOrganizerMenu() {
        presenter.startPrompt();
        presenter.displayOrganizerMenu();


        OrganizerCommand[] enumCommandList = OrganizerCommand.values();
        OrganizerCommand nextView = null;
        boolean validInput = false;
        boolean loggedIn = true;

        while (loggedIn) {
            String userCommand = userInput.nextLine();

            // TODO: 12/04/20 Find more efficient way to use Enums
            while (!validInput) {
                for (OrganizerCommand enumCommand : enumCommandList) {
                    if (userCommand.equals(enumCommand.stringValue)) {
                        validInput = true;
                        nextView = enumCommand;
                        break;
                    }
                }
                if (!validInput) {
                    presenter.invalidInputPrompt();
                    presenter.requestCommandPrompt();
                    userCommand = userInput.nextLine();
                }
            }

            switch (nextView) {
                // TODO: 12/04/20 Enable exit
//                case EXIT:
//                    loggedIn = false;
//                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case NEW_SPEAKER:
                    RegistrationView registrationView = new RegistrationView(am, fm, cm, em);
                    registrationView.accountInfoMenu("2");
                    break;
                case VIEW_ALL_ACCOUNTS:
                    Set<String> accounts = am.getAccountHashMap().keySet();
                    presenter.accountList(accounts);
                    break;
                case ADD_CONTACT:
                    FriendView friendView = new FriendView(username, fm);
                    friendView.viewAddFriendMenu();
                    break;
                case REMOVE_CONTACT:
                    friendView = new FriendView(username, fm);
                    friendView.viewRemoveFriendMenu();
                    break;
                case VIEW_CONTACTS:
                    friendView = new FriendView(username, fm);
                    friendView.viewFriendList();
                    break;
                case MESSAGE_SPEAKER:
                case MESSAGE_ATTENDEE:
                case MESSAGE_ALL_SPEAKERS:
                case MESSAGE_ALL_ATTENDEES:
                    MessageView messageView = new MessageView(username, am, fm, cm, em);
                    messageView.message(nextView);
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(username, am, fm, cm, em);
                    conversationView.conversations();
                    break;
                case ADD_ROOM:
                    LocationView locationView = new LocationView(username, am, fm, cm, em);
                    locationView.addRoom();
                    break;
                case VIEW_ROOMS:
                    locationView = new LocationView(username, am, fm, cm, em);
                    locationView.rooms();
                    break;
                case ADD_EVENT:
                    EventView eventView = new EventView(username, am, fm, cm, em);
                    eventView.eventCreation();
                    break;
                case CANCEL_EVENT:
                    eventView = new EventView(username, am, fm, cm, em);
                    eventView.eventCancellation();
                    break;
                case RESCHEDULE_EVENT:
                    eventView = new EventView(username, am, fm, cm, em);
                    eventView.eventReschedule();
                    break;
                case VIEW_SCHEDULE:
                    HashMap<String[], Calendar> allTalks = em.fetchSortedTalks();
                    presenter.displayTalkSchedule(allTalks);
                    break;
                case VIEW_MENU:
                    presenter.displayOrganizerMenu();
                    break;
            } if (loggedIn) {
                presenter.requestCommandPrompt();
                validInput = false;
            }
        }
    }
}
