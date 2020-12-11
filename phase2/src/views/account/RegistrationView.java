package views.account;

import controllers.account.RegistrationController;
import enums.AccountTypeEnum;
import enums.ViewEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import presenters.account.RegistrationPresenter;
import views.View;

import java.util.Scanner;

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
        presenter.startPrompt();
        AccountTypeEnum accountTypeEnum = getAccountType();

        presenter.displayCodePrompt(accountTypeEnum);
        if (!accountTypeEnum.equals(AccountTypeEnum.ATTENDEE)) {
            String code = controller.getRegistrationCode(accountTypeEnum);
            String codeInput = userInput.nextLine();
            validateCode(codeInput, code);
        }

        getAccountInfo(accountTypeEnum);

        return ViewEnum.START;
    }

    protected AccountTypeEnum getAccountType(){
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
            presenter.usernameIsTakenNotification();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        try {
            controller.register(accountType, username, password);
            presenter.exitPrompt();
        } catch (AccountAlreadyExistsException e) {
            presenter.accountAlreadyExistsNotification();
        }
    }
}