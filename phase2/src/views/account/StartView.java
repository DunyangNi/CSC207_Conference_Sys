package views.account;

import gateway.DataManager;
import presenters.StartPresenter;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class StartView {
    private final DataManager dm;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final StartPresenter presenter = new StartPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public StartView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
    }

    public boolean startMenu() {
        presenter.startPrompt();
        String command = userInput.nextLine();

        while (!command.matches("[0-2]")) {
            presenter.invalidCommandPrompt();
            command = userInput.nextLine();
        }

        if (command.equals("1")) {
            LoginView view = new LoginView(dm);
            view.runView();
        }
        if (command.equals("2")) {
            RegistrationView view = new RegistrationView(dm);
            view.runView();
        }

        return false;
    }
}