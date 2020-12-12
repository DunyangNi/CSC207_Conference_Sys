package views.event;

import controllers.event.EventController;

import enums.ViewEnum;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.SignupCancelPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for signup cancellation functionality.
 */
public class SignupCancelView implements View {
    private final SignupCancelPresenter presenter;
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>SignupCancelView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given EventPresenter
     */
    public SignupCancelView(EventController controller, SignupCancelPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.signupCancelHeader();
        presenter.eventIDPrompt();
        try {
            int id = Integer.parseInt(userInput.nextLine());
            controller.cancelSignupForEvent(id);
            presenter.signupCancelSuccessNotification();
            return ViewEnum.VOID;
        } catch (EventNotFoundException e) {
            presenter.eventNotFoundNotification();
        } catch (AttendeeNotFoundException e) {
            presenter.attendeeNotFoundNotification();
        }
        presenter.signupCancelFailureNotification();
        return ViewEnum.VOID;
    }
}
