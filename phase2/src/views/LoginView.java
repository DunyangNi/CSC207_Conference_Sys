package views;

import enums.AccountType;
import gateway.AccountDataManager;
import gateway.ConversationDataManager;
import gateway.EventDataManager;
import gateway.FriendDataManager;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;
import controller.LoginController;
import presenter.*;

import java.util.Scanner;

public class LoginView {
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final LoginController controller;
    private final LoginPresenter presenter = new LoginPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.controller = new LoginController(am, fm, cm, em);
    }

    public void loginMenu() {
        presenter.startPrompt();
        String username = userInput.nextLine();

        while (!am.containsAccount(username)) {
            presenter.dneUsernamePrompt();
            username = userInput.nextLine();
            if (username.equals("*")) {
                return;
            }
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        while (!am.isCorrectPassword(username, password)) {
            presenter.incorrectPasswordPrompt();
            password = userInput.nextLine();
            if (password.equals("*")) {
                return;
            }
        }

        presenter.exitPrompt();
        AccountType accountType = controller.login(username);

        switch (accountType) {
            case ORGANIZER:
                OrganizerView organizerView = new OrganizerView(username, am, fm, cm, em);
                organizerView.viewOrganizerMenu();
                break;
            case SPEAKER:
                SpeakerView speakerView = new SpeakerView(username, am, fm, cm, em);
                speakerView.viewSpeakerMenu();
                break;
            case ATTENDEE:
                AttendeeView attendeeView = new AttendeeView(username, am, fm, cm, em);
                attendeeView.viewAttendeeMenu();
                break;
        }

        AccountDataManager accountDataManager = new AccountDataManager();
        FriendDataManager friendDataManager = new FriendDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        EventDataManager eventDataManager = new EventDataManager();

        accountDataManager.saveManager("AccountManager", "AccountManager", am);
        friendDataManager.saveManager("FriendManager", "FriendManager", fm);
        conversationDataManager.saveManager("ConversationManager", "ConversationManager", cm);
        eventDataManager.saveManager("EventManager", "EventManager", em);
    }
}