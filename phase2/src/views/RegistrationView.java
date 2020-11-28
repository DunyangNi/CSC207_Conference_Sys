package views;

import controller.RegistrationController;
import presenter.RegistrationPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class RegistrationView {
    private final AccountManager accountManager;
    private final RegistrationController controller;
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public RegistrationView(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.controller = new RegistrationController(am, fm, cm, em);
    }

    public boolean viewRegistrationMenu() {
        presenter.preUserInputPrompt();
        String accountType = userInput.nextLine();

        while (!(accountType.equals("1") || (accountType.equals("2")))) {
            presenter.invalidCommandPrompt();
            accountType = userInput.nextLine();
        }

        if (accountType.equals("2")) {
            presenter.organizerCodePrompt();
            String code = userInput.nextLine();

            while (!code.equals(controller.ORGANIZER_CODE)) {
                presenter.invalidCodePrompt();
                code = userInput.nextLine();
            }
        }

        presenter.usernamePrompt();
        String username = userInput.nextLine();

        while ((accountManager.containsAccount(username))) {
            presenter.takenUsernamePrompt();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        presenter.postUserInputPrompt();
        return controller.register(accountType, username, password);
    }
}
