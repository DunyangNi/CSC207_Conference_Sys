package views.message;

import controllers.message.MessageController;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;

import java.util.Scanner;

public class MessageAllSpeakersView {
    private final MessageController controller;
    private final MessagePresenter presenter = new MessagePresenter();
    private final Scanner userInput = new Scanner(System.in);

    public MessageAllSpeakersView(DataManager dm) {
        this.controller = new MessageController(dm);
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
