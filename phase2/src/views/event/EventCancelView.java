package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventCancelPresenter;
import exceptions.not_found.EventNotFoundException;
import views.factory.View;

import java.util.Scanner;

/**
 * Represents a view for Event cancellation schedules.
 */
public class EventCancelView implements View {
    private final EventController controller;
    private final EventCancelPresenter presenter;
    Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>EventCancelView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given EventCancelPresenter
     */
    public EventCancelView(EventController controller, EventCancelPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.cancelEventHeader();
        try {
            presenter.eventIDPrompt();
            int id = Integer.parseInt(userInput.nextLine());
            controller.cancelEvent(id);
            presenter.cancelEventSuccessNotification();
        } catch (NumberFormatException e) {
            presenter.invalidIDNotification();
            presenter.cancelEventFailureNotification();
        } catch (EventNotFoundException e) {
            presenter.eventNotFoundNotification();
            presenter.cancelEventFailureNotification();
        }
        return ViewEnum.VOID;
    }
}
