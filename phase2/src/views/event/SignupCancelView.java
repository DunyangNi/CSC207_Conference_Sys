package views.event;

import controllers.event.EventController;

import enums.ViewEnum;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.SignupCancelPresenter;
import views.View;

import java.util.Scanner;

public class SignupCancelView implements View {
    private final SignupCancelPresenter presenter;
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    public SignupCancelView(EventController controller, SignupCancelPresenter presenter) {
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