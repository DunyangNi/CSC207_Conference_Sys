package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;

public class EventReschedulePresenter implements Presenter, InputErrorPresenter, EventErrorPresenter {
    @Override
    public void startPrompt() {
        System.out.println("[EVENT RESCHEDULING]\nWelcome to Event Rescheduling! Follow the prompts to reschedule your event.");
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
