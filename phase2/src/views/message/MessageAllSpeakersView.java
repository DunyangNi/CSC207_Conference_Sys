package views.message;

import controllers.message.MessageController;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;

import java.util.Scanner;

public class MessageAllSpeakersView {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageAllSpeakersView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public void runView() {
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAllSpeakers(message);
        } catch (AccountNotFoundException | NoRecipientsException e) {
            e.printStackTrace();
        }
    }
}
