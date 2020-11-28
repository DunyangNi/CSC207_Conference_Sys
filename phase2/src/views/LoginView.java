package views;

import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;
import controller.LoginController;
import presenter.*;

import java.util.Scanner;

public class LoginView {
    private final AccountManager accountManager;
    private final LoginController controller;
    private final LoginPresenter presenter = new LoginPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.controller = new LoginController(am, fm, cm, em);
    }

    public boolean viewLoginMenu() {
        presenter.preUserInputPrompt();
        String username = userInput.nextLine();

        while (!accountManager.containsAccount(username)) {
            presenter.dneUsernamePrompt();
            username = userInput.nextLine();
            if (username.equals("*")) {
                return false;
            }
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        while (!accountManager.isCorrectPassword(username, password)) {
            presenter.incorrectPasswordPrompt();
            password = userInput.nextLine();
            if (password.equals("*")) {
                return false;
            }
        }

        presenter.postUserInputPrompt();
        return controller.login(username);
    }
}