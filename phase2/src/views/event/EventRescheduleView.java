package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.EventReschedulePresenter;
import views.start.View;

import java.util.Calendar;
import java.util.Scanner;

public class EventRescheduleView implements View {
    private final EventController controller;
    private final EventReschedulePresenter presenter;
    private final GetTimeView getTimeView;
    private final Scanner userInput = new Scanner(System.in);

    public EventRescheduleView(EventController controller, EventReschedulePresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
        getTimeView =  new GetTimeView(presenter);
    }

    public ViewEnum runView() {
        presenter.eventRescheduleHeader();

        boolean chosenID = false;
        int id = 0;
        while (!chosenID) {
            try {
                presenter.eventIDPrompt();
                id = Integer.parseInt(userInput.nextLine());
                chosenID = true;
            } catch (NumberFormatException e) { presenter.nonNegativeNumberNotification(); }
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
