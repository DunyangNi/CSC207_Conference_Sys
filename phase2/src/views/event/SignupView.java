package views.event;

import controllers.event.SignupController;
import enums.AttendeeEnum;
import presenters.event.SignupPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.ContactManager;
import use_cases.EventManager;

import java.util.Scanner;

public class SignupView {
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final SignupPresenter presenter = new SignupPresenter();
    private final SignupController controller;
    private final Scanner userInput = new Scanner(System.in);

    public SignupView(String username, AccountManager am, ContactManager fm, ConversationManager cm, EventManager em) {
        this.username = username;
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.controller = new SignupController(username, am, em);
    }

    public void signupMenu(AttendeeEnum command) {
        presenter.eventIdPrompt();
        int id = userInput.nextInt();
        if (command.equals(AttendeeEnum.SIGNUP_EVENT)) {
            controller.signupForEvent(id);
        }
        if (command.equals(AttendeeEnum.SIGNUP_EVENT)) {
            controller.cancelSignupForEvent(id);
        }
    }

    public void signupSchedule() {
    }
}
