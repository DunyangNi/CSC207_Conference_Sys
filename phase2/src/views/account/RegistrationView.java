package views.account;

import controllers.account.RegistrationController;
import enums.AccountTypeEnum;
import enums.ViewEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import presenters.account.RegistrationPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for register functionality (The one available at the login menu)
 */
public class RegistrationView implements View {
    protected final RegistrationController controller;
    protected final RegistrationPresenter presenter;
    protected final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>RegistrationView</code> based on the following parameters
     *
     * @param controller The given RegistrationController
     * @param presenter  The given RegistrationPresenter
     */
    public RegistrationView(RegistrationController controller, RegistrationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Run the view.
     *
     * @return ViewEnum.START so that the program returns to StartView
     */
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

    /**
     * @return the account type enum for the account that we want to register
     */
    protected AccountTypeEnum getAccountTypeEnum() {
        AccountTypeEnum accountTypeEnum = AccountTypeEnum.fromString(userInput.nextLine());

        while (accountTypeEnum.equals(AccountTypeEnum.INVALID)) {
            presenter.invalidCommandNotification();
            accountTypeEnum = AccountTypeEnum.fromString(userInput.nextLine());
        }

        return accountTypeEnum;
    }

    /**
     * Asks for and validates the registration code when applicable.
     *
     * @param codeInput the inputted registration code
     * @param code      the correct registration code
     */
    public void validateCode(String codeInput, String code) {
        while (!codeInput.equals(code)) {
            presenter.invalidCodeNotification();
            codeInput = userInput.nextLine();
        }
    }

    /**
     * Ask for the username, password during registration and check that they are valid,
     * and register the account if they are
     *
     * @param accountType the account type enum
     */
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