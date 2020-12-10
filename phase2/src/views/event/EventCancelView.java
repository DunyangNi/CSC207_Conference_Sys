package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventCancelPresenter;
import exceptions.not_found.EventNotFoundException;
import views.View;

import java.util.Scanner;

public class EventCancelView implements View {
    private final EventController controller;
    private final EventCancelPresenter presenter;
    Scanner userInput = new Scanner(System.in);

    public EventCancelView(EventController controller, EventCancelPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView(){
        presenter.cancelEventHeader();
        try {
            presenter.eventIDPrompt();
            int id = Integer.parseInt(userInput.nextLine());
            controller.cancelEvent(id);
            presenter.cancelEventSuccessNotification();
            presenter.exitPrompt();
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
