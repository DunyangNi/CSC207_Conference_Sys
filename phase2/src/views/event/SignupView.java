package views.event;

import controllers.event.SignupController;
import enums.AttendeeMenuEnum;
import gateway.DataManager;
import presenters.event.SignupPresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class SignupView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final SignupPresenter presenter = new SignupPresenter();
    private final SignupController controller;
    private final Scanner userInput = new Scanner(System.in);

    public SignupView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
        this.controller = new SignupController(dm);
    }

    public void signupMenu(AttendeeMenuEnum command) {
        presenter.eventIdPrompt();
        int id = userInput.nextInt();
        if (command.equals(AttendeeMenuEnum.SIGNUP_EVENT)) {
            controller.signupForEvent(id);
        }
        if (command.equals(AttendeeMenuEnum.SIGNUP_EVENT)) {
            controller.cancelSignupForEvent(id);
        }
    }

    public void signupSchedule() {
    }
}
