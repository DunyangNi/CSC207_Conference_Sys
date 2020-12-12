package views.message;

import controllers.message.ContactController;
import enums.ViewEnum;
import exceptions.conflict.AlreadyContactException;
import exceptions.not_found.AccountNotFoundException;
import presenters.message.ContactPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for contact adding functionality
 * Fields:
 * controller: ContactController responsible for contact functionality
 * presenter: ContactPresenter responsible for displaying relevant prompts
 */

public class ContactAddView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>ContactAddView</code> based on the following parameters
     * @param controller The given ContactController
     * @param presenter The given ContactPresenter
     */

    public ContactAddView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

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
