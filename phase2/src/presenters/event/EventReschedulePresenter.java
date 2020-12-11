package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;
import presenters.TimePresenter;

public class EventReschedulePresenter implements Presenter, InputErrorPresenter, EventErrorPresenter, TimePresenter {
    @Override
    public void startPrompt() {
        System.out.println();
        System.out.println("[RESCHEDULE EVENT]");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Event Rescheduling successful!}");
    }

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event to reschedule: ");
    }

    public void eventRescheduleFailureNotification() {
        System.out.println("{Event rescheduling cancelled.}");
    }
}
