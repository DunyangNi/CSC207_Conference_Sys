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
        AccountTypeEnum enumCommand = getAccountType();

        registrationCodeView(enumCommand);
        accountInfoView(enumCommand);

        controller.saveData();

        return ViewEnum.START;
    }

    protected AccountTypeEnum getAccountType(){
        AccountTypeEnum enumCommand = AccountTypeEnum.fromString(userInput.nextLine());

        while (enumCommand.equals(AccountTypeEnum.INVALID)) {
            presenter.invalidCommandPrompt();
            enumCommand = AccountTypeEnum.fromString(userInput.nextLine());
        }

        return enumCommand;
    }

    public void registrationCodeView(AccountTypeEnum accountType) {
        // TODO: 11/28/20 Add new cases for new account types
        String code;
        switch (accountType) {
            case SPEAKER:
                presenter.speakerCodePrompt();
                code = controller.SPEAKER_CODE;
                break;
            case ORGANIZER:
                presenter.organizerCodePrompt();
                code = controller.ORGANIZER_CODE;
                break;
            default:
                return;
        }

        String codeInput = userInput.nextLine();

        while (!codeInput.equals(code)) {
            presenter.invalidCodePrompt();
            codeInput = userInput.nextLine();
        }
    }

    public void accountInfoView(AccountTypeEnum accountType) {
        presenter.usernamePrompt();
        String username = userInput.nextLine();

        while (controller.usernameExists(username)) {
            presenter.takenUsernamePrompt();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        try {
            controller.register(accountType, username, password);
            presenter.exitPrompt();
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}