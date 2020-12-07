package views.message;

import controllers.message.MessageController;
import exceptions.NoRecipientsException;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.EventNotFoundException;
import gateways.DataManager;
import presenters.message.MessagePresenter;

import java.util.ArrayList;
import java.util.Scanner;

public class MessageTalkAttendeesView {
    private final MessageController controller;
    private final MessagePresenter presenter = new MessagePresenter();
    private final Scanner userInput = new Scanner(System.in);

    public MessageTalkAttendeesView(DataManager dm) {
        this.controller = new MessageController(dm);
    }

    public void runView() {
        ArrayList<Integer> selectedTalks = new ArrayList<>();
        boolean another;

        do {
            presenter.eventIdPrompt();
            selectedTalks.add(userInput.nextInt());
            presenter.nextEventIdPrompt();
            another = userInput.nextInt() != 0;
        } while (another);

        presenter.messagePrompt();
        String message = userInput.nextLine();

        try {
            controller.messageTalkAttendees(selectedTalks, message);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        } catch (NoRecipientsException e) {
            e.printStackTrace();
        } catch (EventNotFoundException e) {
            e.printStackTrace();
        }

    }
}
