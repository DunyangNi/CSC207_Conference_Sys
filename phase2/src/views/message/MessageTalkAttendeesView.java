package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.NonPositiveIntegerException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.EventNotFoundException;
import presenters.message.MessagePresenter;
import views.View;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MessageTalkAttendeesView implements View { // TODO Needs update to work with Panel Events. See TODO below.
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageTalkAttendeesView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        ArrayList<Integer> selectedTalks = new ArrayList<>();
        boolean another;

        try {
            do {
                presenter.eventIdPrompt();
                selectedTalks.add(userInput.nextInt());
                presenter.nextEventIdPrompt();
                another = userInput.nextInt() != 0;
            } while (another);

            presenter.messagePrompt();
            String message = userInput.nextLine();

            controller.messageTalkAttendees(selectedTalks, message); // TODO Related methods need updates to work with Panel Events.
        }
        catch (AccountNotFoundException e) {
            presenter.accountNotFoundNotification();
        }
        catch (NoRecipientsException e) {
            presenter.noRecipientsNotification();
        }
        catch (EventNotFoundException | InputMismatchException e) {
            presenter.eventNotFoundNotification();
        }
        return ViewEnum.VOID;
    }
}
