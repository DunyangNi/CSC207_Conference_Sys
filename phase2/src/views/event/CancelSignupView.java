package views.event;

import controllers.event.EventController;

import enums.ViewEnum;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.CancelSignupPresenter;
import views.View;

import java.util.Scanner;

public class CancelSignupView implements View {
    private final CancelSignupPresenter presenter;
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    public CancelSignupView(EventController controller, CancelSignupPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.eventIdPrompt();
        try {
            int id = Integer.parseInt(userInput.nextLine());
            controller.cancelSignupForEvent(id);
            presenter.exitPrompt();
            return ViewEnum.VOID;
        }
        catch (EventNotFoundException e){ presenter.eventNotFoundPrompt(); }
        catch (AttendeeNotFoundException e) { presenter.attendeeNotFoundPrompt(); }
        presenter.cancelExitPrompt();
        return ViewEnum.VOID;
    }
}
