package views.account;

import controllers.account.ContactController;
import enums.ViewEnum;
import exceptions.not_found.ObjectNotFoundException;
import presenters.account.ContactPresenter;
import views.View;

import java.util.Scanner;

public class ContactRemoveView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public ContactRemoveView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    public ViewEnum runView() {
        presenter.removeContactPrompt();
        String username = userInput.nextLine();
        try {
            controller.removeFriend(username);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return ViewEnum.VOID;
    }
}
