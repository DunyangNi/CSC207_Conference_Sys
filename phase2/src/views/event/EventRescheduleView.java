package views.event;

import controllers.event.EventController;
import exceptions.OutOfScheduleException;
import exceptions.conflict.LocationInUseException;
import exceptions.conflict.SpeakerIsBusyException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.EventReschedulePresenter;

import java.util.Calendar;
import java.util.Scanner;

public class EventRescheduleView {
    private final EventController eventController;
    private final EventReschedulePresenter eventReschedulePresenter;
    private final Scanner userInput = new Scanner(System.in);
    private final TimeView timeView = new TimeView();

    public EventRescheduleView(EventController eventController, EventReschedulePresenter eventReschedulePresenter) {
        this.eventController = eventController;
        this.eventReschedulePresenter = eventReschedulePresenter;
    }

    public void runView() {
        eventReschedulePresenter.startPrompt();

        boolean chosenID = false;
        int id = 0;
        while (!chosenID) {
            try {
                eventReschedulePresenter.eventIDPrompt();
                id = Integer.parseInt(userInput.nextLine());
                chosenID = true;
            } catch (NumberFormatException e) { eventReschedulePresenter.invalidIDPrompt(); }
        }

        Calendar newTime = timeView.runTimeView();

        try {
            eventController.rescheduleTalk(id, newTime);
            eventReschedulePresenter.exitPrompt();
            return;
        } catch (LocationInUseException e) {
            eventReschedulePresenter.inUseLocationPrompt();
        } catch (SpeakerIsBusyException e) {
            eventReschedulePresenter.speakerIsBusyPrompt();
        } catch (EventNotFoundException e) {
            eventReschedulePresenter.eventNotFoundPrompt();
        } catch (OutOfScheduleException e) {
            eventReschedulePresenter.outOfSchedulePrompt();
        }
        eventReschedulePresenter.cancelExitPrompt();
    }
}
