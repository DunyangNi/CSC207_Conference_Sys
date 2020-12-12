package views.account;

import controllers.account.LoginController;
import enums.ViewEnum;
import exceptions.conflict.IncorrectPasswordException;
import exceptions.not_found.UserNotFoundException;
import presenters.account.LoginPresenter;
import views.start.View;

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
        presenter.loginHeader();
        presenter.usernamePrompt();
        String username = userInput.nextLine();
        presenter.passwordPrompt();
        String password = userInput.nextLine();

        try{
            ViewEnum viewEnum = controller.login(username, password);
            presenter.loginFailureNotification();
            return viewEnum;
        } catch (UserNotFoundException e) {
            presenter.usernameNotFoundNotification();
        } catch (IncorrectPasswordException e) {
            presenter.incorrectPasswordNotification();
        }
        presenter.failedLoginPrompt();
        String input = userInput.nextLine();
        if (input.equals("Y")) {
            return ViewEnum.LOGIN;
        } else {
            return ViewEnum.START;
        }
    }
}
