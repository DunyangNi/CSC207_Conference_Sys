package views.message;

import controllers.message.ContactController;
import enums.ViewEnum;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;
import presenters.message.ContactPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for contact removal functionality
 * Fields:
 * controller: ContactController responsible for contact functionality
 * presenter: ContactPresenter responsible for displaying relevant prompts
 */

public class ContactRemoveView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>ContactRemoveView</code> based on the following parameters
     * @param controller The given ContactController
     * @param presenter The given ContactPresenter
     */

    public ContactRemoveView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        presenter.removeContactHeader();
        presenter.removeContactPrompt();
        String contactToRemove = userInput.nextLine();
        try {
            controller.removeContact(contactToRemove);
            presenter.removeContactSuccessNotification(contactToRemove);
        } catch (ContactNotFoundException e) {
            presenter.contactNotFoundNotification();
            presenter.removeContactFailureNotification();
        } catch (AccountNotFoundException e) {
            presenter.accountNotFoundNotification();
            presenter.removeContactFailureNotification();
        }
        return ViewEnum.VOID;
    }
}
