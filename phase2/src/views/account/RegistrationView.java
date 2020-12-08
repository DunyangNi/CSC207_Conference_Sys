package views.account;

import controllers.account.AccountController;
import controllers.account.AccountRegistrationController;
import enums.AccountTypeEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import gateways.DataManager;
import presenters.account.RegistrationPresenter;

import java.util.Scanner;

public class RegistrationView {
    protected final AccountRegistrationController controller;
    protected final RegistrationPresenter presenter = new RegistrationPresenter();
    protected final Scanner userInput = new Scanner(System.in);

    public RegistrationView(DataManager dm) {
        this.controller = new AccountRegistrationController(dm);
    }

    public void runView() {
        presenter.startPrompt();
        AccountTypeEnum enumCommand = getType();

        registrationCodeView(enumCommand);
        accountInfoView(enumCommand);

        controller.saveData();
    }

    protected AccountTypeEnum getType(){
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