package presenters.event;

import presenters.interfaces.EventErrorPresenter;
import presenters.interfaces.InputErrorPresenter;
import presenters.interfaces.TimePresenter;

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
