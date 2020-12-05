package views;

import enums.AttendeeEnum;
import presenter.AttendeePresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class AttendeeView {
    private final String username;
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final AttendeePresenter presenter = new AttendeePresenter();
    private final Scanner userInput = new Scanner(System.in);

    public AttendeeView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
    }

    public void viewAttendeeMenu() {
        presenter.startPrompt();
        presenter.displayAttendeeMenu();

        AttendeeEnum[] enumCommandList = AttendeeEnum.values();
        AttendeeEnum nextView = null;
        boolean validInput = false;
        boolean loggedIn = true;

        while (loggedIn) {
            String userCommand = userInput.nextLine();

            // TODO: 12/04/20 Find more efficient way to use Enums
            while (!validInput) {
                for (AttendeeEnum enumCommand : enumCommandList) {
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
                case MESSAGE_SPEAKER:
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
                case SIGNUP_EVENT:
                    SignupView signupView = new SignupView(username, am, fm, cm, em);

                    break;
                case LEAVE_EVENT:
                    break;
                case VIEW_MY_SCHEDULE:
                    break;
                case DOWNLOAD_SCHEDULE:
                    break;
                case VIEW_MENU:
                    presenter.displayAttendeeMenu();
                    break;
            }
            if (loggedIn) {
                presenter.requestCommandPrompt();
                validInput = false;
            }
        }
    }
}
