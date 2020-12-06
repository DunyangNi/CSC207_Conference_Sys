package views.start;

import controllers.start.StartController;
import enums.StartCommand;
import gateway.DataManager;
import presenters.start.StartPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class StartView {
    private final DataManager dm;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final StartController controller;
    private final StartPresenter presenter = new StartPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public StartView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.controller = new StartController(dm);
    }

    public boolean startMenu() {
        presenter.startPrompt();
        String command = userInput.nextLine();

        while (!(command.equals("0") || command.equals("1") || (command.equals("2")))) {
            presenter.invalidCommandPrompt();
            command = userInput.nextLine();
        }

        StartCommand nextView = controller.start(command);

        if (nextView.equals(StartCommand.REGISTER)) {
            RegistrationView view = new RegistrationView(dm);
            view.registrationMenu();
        }
        if (nextView.equals(StartCommand.LOGIN)) {
            LoginView view = new LoginView(dm);
            view.loginMenu();
        }

        return false;
    }
}


