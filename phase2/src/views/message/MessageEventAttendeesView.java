package views.message;

import controllers.message.MessageController;
import enums.ViewEnum;
import exceptions.NoRecipientsException;
import exceptions.not_found.ContactNotFoundException;
import exceptions.not_found.EventNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import presenters.message.MessagePresenter;
import views.start.View;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MessageEventAttendeesView implements View {
    private final MessageController controller;
    private final MessagePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public MessageEventAttendeesView(MessageController controller, MessagePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

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
