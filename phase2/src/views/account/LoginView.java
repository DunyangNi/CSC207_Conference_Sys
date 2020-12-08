package views.account;

import controllers.account.LoginController;
import enums.AccountTypeEnum;
import enums.ViewEnum;
import presenters.account.LoginPresenter;
import views.View;

import java.util.Scanner;

public class LoginView implements View {
    private final LoginController controller;
    private final LoginPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(LoginController controller, LoginPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.usernamePrompt();
        String username = userInput.nextLine();

        while (!controller.usernameExists(username)) {
            if (username.equals("*")) {
                return ViewEnum.START;
            }
            presenter.dneUsernamePrompt();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        while (!controller.isCorrectPassword(username, password)) {
            if (password.equals("*")) {
                return ViewEnum.START;
            }
            presenter.incorrectPasswordPrompt();
            password = userInput.nextLine();
        }

        presenter.exitPrompt();

        return controller.login(username);
    }
}
