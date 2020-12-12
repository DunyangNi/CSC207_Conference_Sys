package views.account;

import controllers.account.ContactController;
import enums.ViewEnum;
import exceptions.conflict.AlreadyContactException;
import exceptions.not_found.AccountNotFoundException;
import presenters.account.ContactPresenter;
import views.View;

import java.util.Scanner;

/**
 * View responsible for contact adding functionality
 */

public class ContactAddView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public ContactAddView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    public ViewEnum runView() {
        presenter.addContactHeader();
        presenter.addContactPrompt();
        String contactToAdd = userInput.nextLine();
        try {
            controller.addContact(contactToAdd);
            presenter.addContactSuccessNotification(contactToAdd);
        }
        catch (AccountNotFoundException e) {
            presenter.accountNotFoundNotification();
            presenter.addContactFailureNotification();
        }
        catch (AlreadyContactException e) {
            presenter.alreadyContactNotification();
            presenter.addContactFailureNotification();
        }
        return ViewEnum.VOID;
    }
}
