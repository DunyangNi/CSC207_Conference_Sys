package views.account;

import controllers.account.RegistrationController;
import enums.AccountTypeEnum;
import enums.ViewEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import presenters.account.RegistrationPresenter;
import views.start.View;

import java.util.Scanner;

/**
 * View responsible for register functionality (The one available at the login menu)
 */

public class RegistrationView implements View {
    protected final RegistrationController controller;
    protected final RegistrationPresenter presenter;
    protected final Scanner userInput = new Scanner(System.in);

    public RegistrationView(RegistrationController controller, RegistrationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        presenter.registrationHeader();
        presenter.registrationMenu();
        AccountTypeEnum accountTypeEnum = getAccountTypeEnum();
        String registrationCode = controller.getRegistrationCode(accountTypeEnum);

        presenter.registrationCodePrompt(accountTypeEnum);
        if (!accountTypeEnum.equals(AccountTypeEnum.ATTENDEE)) {
            validateCode(userInput.nextLine(), registrationCode);
        }

        getAccountInfo(accountTypeEnum);

        return ViewEnum.START;
    }

    protected AccountTypeEnum getAccountTypeEnum() {
        AccountTypeEnum accountTypeEnum = AccountTypeEnum.fromString(userInput.nextLine());

        while (accountTypeEnum.equals(AccountTypeEnum.INVALID)) {
            presenter.invalidCommandNotification();
            accountTypeEnum = AccountTypeEnum.fromString(userInput.nextLine());
        }

        return accountTypeEnum;
    }

    public void validateCode(String codeInput, String code) {
        while (!codeInput.equals(code)) {
            presenter.invalidCodeNotification();
            codeInput = userInput.nextLine();
        }
    }

    public void getAccountInfo(AccountTypeEnum accountType) {
        presenter.usernamePrompt();
        String username = userInput.nextLine();

        while (controller.usernameExists(username)) {
            presenter.takenUsernameNotification();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        try {
            controller.register(accountType, username, password);
            presenter.registrationSuccessNotification();
        } catch (AccountAlreadyExistsException e) {
            presenter.accountAlreadyExistsNotification();
        }
    }
}