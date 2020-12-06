package views.account;

import controllers.account.LoginController;
import enums.AccountTypeEnum;
import gateways.*;
import presenters.account.LoginPresenter;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class LoginView {
    private final DataManager dm;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final LoginController controller;
    private final LoginPresenter presenter = new LoginPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.controller = new LoginController(dm);
    }

    public void runView() {
        presenter.startPrompt();

        presenter.usernamePrompt();
        String username = userInput.nextLine();

        while (!controller.usernameExists(username)) {
            presenter.dneUsernamePrompt();
            username = userInput.nextLine();
            if (username.equals("*")) {
                return;
            }
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        while (!controller.isCorrectPassword(username, password)) {
            presenter.incorrectPasswordPrompt();
            password = userInput.nextLine();
            if (password.equals("*")) {
                return;
            }
        }

        presenter.exitPrompt();
        AccountTypeEnum accountTypeEnum = controller.login(username);

        switch (accountTypeEnum) {
            case ORGANIZER:
                OrganizerView organizerView = new OrganizerView(dm);
                organizerView.viewOrganizerMenu();
                break;
            case SPEAKER:
                SpeakerView speakerView = new SpeakerView(dm);
                speakerView.viewSpeakerMenu();
                break;
            case ATTENDEE:
                AttendeeView attendeeView = new AttendeeView(dm);
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