package views.account;

import controllers.account.ContactController;
import enums.ViewEnum;
import presenters.account.ContactPresenter;
import views.View;

import java.util.Scanner;

public class ContactListView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public ContactListView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    public ViewEnum runView() {
        presenter.contactListPrompt(controller.getContactList());
        return ViewEnum.VOID;
    }
}
