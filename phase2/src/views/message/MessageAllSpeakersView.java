package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import presenters.message.MessagePresenter;
import views.View;

import java.util.Scanner;

public class MessageAllSpeakersView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageAllSpeakersView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.messagePrompt();
        String message = userInput.nextLine();
        try {
            controller.messageAllSpeakers(message);
        }
        catch ( AccountNotFoundException e ){
            presenter.accountNotFoundNotification();
        }
        catch ( NoRecipientsException e){
            presenter.noRecipientsNotification();
        }
        return ViewEnum.VOID;
    }
}
