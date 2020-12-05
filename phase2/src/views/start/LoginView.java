package views.start;

import enums.AccountType;
import gateway.AccountDataManager;
import gateway.ConversationDataManager;
import gateway.EventDataManager;
import gateway.ContactDataManager;
import presenters.start.LoginPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import controllers.start.LoginController;
import views.account.AttendeeView;
import views.account.OrganizerView;
import views.account.SpeakerView;

import java.util.Scanner;

public class LoginView {
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final LoginController controller;
    private final LoginPresenter presenter = new LoginPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
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
        ContactDataManager contactDataManager = new ContactDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        EventDataManager eventDataManager = new EventDataManager();

        accountDataManager.saveManager("AccountManager", "AccountManager", am);
        contactDataManager.saveManager("ContactManager", "ContactManager", fm);
        conversationDataManager.saveManager("ConversationManager", "ConversationManager", cm);
        eventDataManager.saveManager("EventManager", "EventManager", em);
    }
}