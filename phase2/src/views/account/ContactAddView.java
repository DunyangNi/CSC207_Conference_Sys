package views.account;

import controllers.account.ContactController;
import enums.ViewEnum;
import exceptions.conflict.AlreadyFriendException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.UserNotFoundException;
import presenters.account.ContactPresenter;
import views.View;

import java.util.Scanner;

public class ContactAddView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public ContactAddView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    public ViewEnum runView() {
        presenter.addContactPrompt();
        String username = userInput.nextLine();
        try {
            controller.addFriend(username);
        }
        catch (UserNotFoundException e) {
            presenter.UserNotFoundPrompt();
        }
        catch (AlreadyFriendException e) {
            presenter.AlreadyFriendPrompt();
        }
        catch (FriendNotFoundException e) {
            presenter.FriendNotFoundPrompt();
        }
        return ViewEnum.VOID;
    }
}
