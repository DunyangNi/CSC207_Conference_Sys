package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.TimePresenter;

public class EventReschedulePresenter implements InputErrorPresenter, EventErrorPresenter, TimePresenter {
    public void eventRescheduleHeader() { System.out.println("\n[RESCHEDULE EVENT]"); }

    public void eventRescheduleSuccessNotification() {
        System.out.println("{Event Rescheduling successful!}");
    }

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event to reschedule: ");
    }

    public void eventRescheduleFailureNotification() {
        System.out.println("{Event rescheduling cancelled.}");
    }
}
