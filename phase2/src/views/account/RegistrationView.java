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
    private final DataManager dm;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final RegistrationController controller;
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public RegistrationView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.controller = new RegistrationController(dm);
    }

    public void registrationMenu() {
        presenter.startPrompt();
        // TODO: 11/29/20 Replace string cases with enum
        // String accountType = AccountTypeEnum.valueOf(userInput.nextLine()).toString();
        String accountType = userInput.nextLine();

        while (!accountType.matches("[1-3]")) {
            presenter.invalidCommandPrompt();
            accountType = userInput.nextLine();
        }

        registrationCodeMenu(accountType);

        accountInfoMenu(accountType);
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
