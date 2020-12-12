package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import presenters.message.MessagePresenter;
import views.start.View;

import java.util.Scanner;

public class MessageAllAttendeesView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageAllAttendeesView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

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
