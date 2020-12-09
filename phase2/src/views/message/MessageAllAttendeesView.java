package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.EventNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;
import views.View;

import java.util.ArrayList;
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
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAllAttendees(message);
        }
        catch (AccountNotFoundException e){
            presenter.AccountNotFoundPrompt();
        }
        catch (NoRecipientsException e){
            presenter.NoRecipientsPrompt();
        }
        return ViewEnum.VOID;
    }
}
