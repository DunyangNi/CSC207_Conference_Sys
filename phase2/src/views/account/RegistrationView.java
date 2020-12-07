package views.account;

import controllers.account.AccountRegistrationController;
import enums.AccountTypeEnum;
import exceptions.already_exists.AccountAlreadyExistsException;
import gateways.*;
import presenters.account.RegistrationPresenter;

import java.util.Scanner;

public class RegistrationView {
    private final DataManager dm;
    private final AccountRegistrationController controller;
    private final RegistrationPresenter presenter = new RegistrationPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public RegistrationView(DataManager dm) {
        this.dm = dm;
        this.controller = new AccountRegistrationController(dm);
    }

    public void runView() {
        presenter.startPrompt();
        AccountTypeEnum enumCommand = AccountTypeEnum.fromString(userInput.nextLine());

        while (enumCommand.equals(AccountTypeEnum.INVALID)) {
            presenter.invalidCommandPrompt();
            enumCommand = AccountTypeEnum.fromString(userInput.nextLine());
        }

        registrationCodeView(enumCommand);
        accountInfoView(enumCommand);

        AccountDataManager accountDataManager = new AccountDataManager();
        ContactDataManager contactDataManager = new ContactDataManager();
        ConversationDataManager conversationDataManager = new ConversationDataManager();
        EventDataManager eventDataManager = new EventDataManager();

        accountDataManager.saveManager("AccountManager", "AccountManager", dm.getAccountManager());
        contactDataManager.saveManager("ContactManager", "ContactManager", dm.getContactManager());
        conversationDataManager.saveManager("ConversationManager", "ConversationManager", dm.getConversationManager());
        eventDataManager.saveManager("EventManager", "EventManager", dm.getEventManager());
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
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }

        presenter.exitPrompt();
    }
}