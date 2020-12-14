package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.not_found.ContactNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import presenters.message.MessagePresenter;
import views.factory.View;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * View responsible for messaging all attendees at an event(s)
 * Fields:
 * controller: MessageController responsible for messaging functionality
 * presenter: MessagePresenter responsble for displaying relevant prompts
 */

public class MessageEventAttendeesView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>MessageEventAttendeesView</code> based on the following parameters
     * @param controller The given MessageController
     * @param presenter The given MessagePresenter
     */

    public MessageEventAttendeesView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     * @return ViewEnum.VOID
     */

    public ViewEnum runView() {
        presenter.messageEventAttendeesHeader();
        ArrayList<Integer> selectedEvents = new ArrayList<>();
        boolean inputEvents = true;
        presenter.eventIDPrompt();
        try {
            while (inputEvents) {
                String input = userInput.nextLine();
                if (input.equals("")) inputEvents = false;
                else selectedEvents.add(Integer.parseInt(input));
            }
            presenter.messagePrompt();
            String message = userInput.nextLine();
            controller.messageEventAttendees(selectedEvents, message);
            presenter.messageEventAttendeesSuccessNotification();
            return ViewEnum.VOID;
        }
        catch (ContactNotFoundException e) { presenter.contactNotFoundNotification(); }
        catch (RecipientNotFoundException e) { presenter.recipientNotFoundNotification(); }
        catch (NoRecipientsException e) { presenter.noRecipientsNotification(); }
        catch (EventNotFoundException e) { presenter.eventNotFoundNotification(); }
        catch (NumberFormatException e) { presenter.nonNegativeNumberNotification(); }
        catch (InputMismatchException e) { presenter.invalidNumberNotification(); }
        presenter.messageEventAttendeesFailureNotification();
        return ViewEnum.VOID;
    }
}
