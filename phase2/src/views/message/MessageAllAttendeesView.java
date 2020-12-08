package views.message;

import controllers.message.MessageController;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.EventNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;

import java.util.ArrayList;
import java.util.Scanner;

public class MessageAllAttendeesView {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageAllAttendeesView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public void runView() {
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAllAttendees(message);
        } catch (AccountNotFoundException | NoRecipientsException e) {
            e.printStackTrace();
        }
    }
}
