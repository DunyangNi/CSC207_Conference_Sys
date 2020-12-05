package views.start;

import controllers.start.RegistrationController;
import exceptions.already_exists.AccountAlreadyExistsException;
import presenters.start.RegistrationPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class RegistrationView {
    private final AccountManager am;
    private final FriendManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final RegistrationController controller;
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public RegistrationView(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.am = am;
        this.fm = fm;
        this.cm = cm;
        this.em = em;
        this.controller = new RegistrationController(am, fm, cm, em);
    }

    public void registrationMenu() {
        presenter.startPrompt();
        // TODO: 11/29/20 Replace string cases with enum
        // String accountType = AccountType.valueOf(userInput.nextLine()).toString();
        String accountType = userInput.nextLine();

        // TODO: 11/28/20 Consider replace while loop with exception throws in Controller or Use Case level
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
        // TODO: 11/28/20 Could possibly remove call to AccountManager.containsAccount() and instead pass whatever
        //  username input to RegistrationController which then catches exceptions when applicable
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
