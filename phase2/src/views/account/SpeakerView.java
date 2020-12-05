package views.account;

import enums.SpeakerEnum;
import presenters.account.SpeakerPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import views.message.ConversationView;
import views.FriendView;
import views.message.MessageView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class SpeakerView {
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final SpeakerPresenter presenter = new SpeakerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(String username, AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
    }

    public void viewSpeakerMenu() {
        presenter.startPrompt();
        presenter.displaySpeakerMenu();

        SpeakerEnum[] enumCommandList = SpeakerEnum.values();
        SpeakerEnum nextView = null;
        boolean validInput = false;
        boolean loggedIn = true;

        while (loggedIn) {
            String userCommand = userInput.nextLine();

            // TODO: 12/04/20 Find more efficient way to use Enums
            while (!validInput) {
                for (SpeakerEnum enumCommand : enumCommandList) {
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
                case MESSAGE_ATTENDEE:
                case MESSAGE_ALL_AT_TALKS:
                    MessageView messageView = new MessageView(username, am, fm, cm, em);
                    messageView.message(nextView);
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(username, am, fm, cm, em);
                    conversationView.conversations();
                    break;
                case VIEW_SCHEDULE:
                    HashMap<String[], Calendar> allTalks = em.fetchSortedTalks();
                    presenter.displayTalkSchedule(allTalks);
                    break;
                case VIEW_MENU:
                    presenter.displaySpeakerMenu();
                    break;
            }
            if (loggedIn) {
                presenter.requestCommandPrompt();
                validInput = false;
            }
        }
    }
}
