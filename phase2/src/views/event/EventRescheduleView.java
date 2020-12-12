package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.EventReschedulePresenter;
import views.factory.View;

import java.util.Calendar;
import java.util.Scanner;

/**
 * View responsible for event rescheduling.
 */
public class EventRescheduleView implements View {
    private final EventController controller;
    private final EventReschedulePresenter presenter;
    private final GetTimeView getTimeView;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>EventCreationView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given EventReschedulePresenter
     */
    public EventRescheduleView(EventController controller, EventReschedulePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
        getTimeView = new GetTimeView(presenter);
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.eventRescheduleHeader();
        boolean chosenID = false;
        int id = 0;
        while (!chosenID) {
            try {
                presenter.eventIDPrompt();
                id = Integer.parseInt(userInput.nextLine());
                chosenID = true;
            } catch (NumberFormatException e) {
                presenter.nonNegativeNumberNotification();
            }
        }
        Calendar newTime = getTimeView.runTimeView();
        try {
            controller.rescheduleEvent(id, newTime);
            presenter.eventRescheduleSuccessNotification();
            return ViewEnum.VOID;
        } catch (LocationInUseException e) {
            presenter.inUseLocationNotification();
        } catch (SpeakerIsBusyException e) {
            presenter.speakerIsBusyNotification();
        } catch (EventNotFoundException e) {
            presenter.eventNotFoundNotification();
        } catch (OutOfScheduleException e) {
            presenter.outOfScheduleNotification();
        }
        presenter.eventRescheduleFailureNotification();
        return ViewEnum.VOID;
    }
}
