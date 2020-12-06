package views.account;

import enums.SpeakerMenuEnum;
import gateways.DataManager;
import presenters.account.SpeakerPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import views.message.ConversationView;
import views.message.MessageView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class SpeakerView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final SpeakerPresenter presenter = new SpeakerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
    }

    public void viewSpeakerMenu() {
        boolean loggedIn = true;

        presenter.startPrompt();
        presenter.displaySpeakerMenu();

        while (loggedIn) {
            SpeakerMenuEnum enumCommand = SpeakerMenuEnum.fromString(userInput.nextLine());

            switch (enumCommand) {
                // TODO: 12/04/20 Enable exit
//                case EXIT:
//                    loggedIn = false;
//                    break;
                case LOGOUT:
                    loggedIn = false;
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
                case MESSAGE_ATTENDEE:
                case MESSAGE_ALL_AT_TALKS:
                    MessageView messageView = new MessageView(dm);
                    messageView.message(enumCommand);
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(dm);
                    conversationView.conversations();
                    break;
                case VIEW_SCHEDULE:
                    HashMap<String[], Calendar> allTalks = em.fetchSortedTalks();
                    presenter.displayTalkSchedule(allTalks);
                    break;
                case VIEW_MENU:
                    presenter.displaySpeakerMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
            }
            if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
    }
}
