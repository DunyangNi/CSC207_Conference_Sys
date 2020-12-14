package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.not_found.ContactNotFoundException;
import exceptions.not_found.AccountNotFoundException;
import presenters.message.MessagePresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for messaging functionality
 * Fields:
 * MessageController responsible for messaging functionality
 * MessagePresener responsible for displaying relevant prompts
 */

public class MessageView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>MessageView</code> based on the following parameters
     * @param controller The given MessageController
     * @param presenter The given MessagePresenter
     */

    public MessageView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        presenter.messageHeader();
        presenter.recipientPrompt();
        String username = userInput.nextLine();
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAccount(username, message);
            presenter.messageSuccessNotification();
        } catch (ContactNotFoundException e){
            presenter.contactNotFoundNotification();
        } catch (AccountNotFoundException e) {
            presenter.recipientNotFoundNotification();
        }
        return ViewEnum.VOID;
    }
}
