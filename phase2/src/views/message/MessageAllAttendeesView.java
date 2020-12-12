package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import presenters.message.MessagePresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for messaging all attendees
 * Fields:
 * controller: MessageController responsible for messaging functionality
 * presenter: MessagePresenter responsible for displaying relevant prompts
 */

public class MessageAllAttendeesView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>MessageAllAttendeesView</code> based on the following parameters
     * @param controller The given MessageController
     * @param presenter The given MessagePresenter
     */

    public MessageAllAttendeesView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        presenter.messageAllAttendeesHeader();
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAllAttendees(message);
            presenter.messageAllAttendeesSuccessNotification();
            return ViewEnum.VOID;
        }
        catch (AccountNotFoundException e){ presenter.recipientNotFoundNotification(); }
        catch (NoRecipientsException e){ presenter.noRecipientsNotification(); }
        presenter.messageAllAttendeesFailureNotification();
        return ViewEnum.VOID;
    }
}
