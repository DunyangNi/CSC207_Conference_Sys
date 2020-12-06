package views.account;

import controllers.account.RegistrationController;
import exceptions.already_exists.AccountAlreadyExistsException;
import gateway.DataManager;
import presenters.account.RegistrationPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class RegistrationView {
    private final AccountManager am;
    private final RegistrationController controller;
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public RegistrationView(DataManager dm) {
        this.am = dm.getAccountManager();
        this.controller = new RegistrationController(dm);
    }

    public void runView() {
        presenter.startPrompt();
        // TODO: 11/29/20 Replace string cases with enum
        // String command = AccountTypeEnum.valueOf(userInput.nextLine()).toString();
        String command = userInput.nextLine();

        while (!command.matches("[1-3]")) {
            presenter.invalidCommandPrompt();
            command = userInput.nextLine();
        }

        registrationCodeMenu(command);

        accountInfoMenu(command);
    }

    public void registrationCodeMenu(String accountType) {
        // TODO: 11/28/20 Add functionality for new account types.
        String code;
        switch (accountType) {
            case "2": {
                presenter.speakerCodePrompt();
                code = controller.SPEAKER_CODE;
                break;
            }
            case "3": {
                presenter.organizerCodePrompt();
                code = controller.ORGANIZER_CODE;
                break;
            }
            default:
                return;
        }

        String codeInput = userInput.nextLine();

        while (!codeInput.equals(code)) {
            presenter.invalidCodePrompt();
            codeInput = userInput.nextLine();
        }
    }

    public void accountInfoMenu(String accountType) {
        presenter.usernamePrompt();
        String username = userInput.nextLine();
        while ((am.containsAccount(username))) {
            presenter.takenUsernamePrompt();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        presenter.exitPrompt();

        try {
            controller.register(accountType, username, password);
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

}
