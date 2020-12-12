package views.message;

import controllers.message.ContactController;
import enums.ViewEnum;
import presenters.message.ContactPresenter;
import views.factory.View;

/**
 * View responsible for seeing the contact list
 */

public class ContactListView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;

    public ContactListView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    public ViewEnum runView() {
        presenter.displayContactList(controller.getContactList());
        return ViewEnum.VOID;
    }
}
