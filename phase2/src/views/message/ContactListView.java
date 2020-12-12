package views.message;

import controllers.message.ContactController;
import enums.ViewEnum;
import presenters.message.ContactPresenter;
import views.factory.View;

/**
 * View responsible for seeing the contact list
 *
 * Fields:
 * controller: ContactController responsible for contact funtionality
 * presenter: ContactPresenter responsible for displaying relevant prompts
 */

public class ContactListView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;

    /**
     * Constructs an instance of <code>ContactListView</code> based on the following parameters
     * @param controller The given ContactController
     * @param presenter The given ContactPresenter
     */

    public ContactListView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        presenter.displayContactList(controller.getContactList());
        return ViewEnum.VOID;
    }
}
