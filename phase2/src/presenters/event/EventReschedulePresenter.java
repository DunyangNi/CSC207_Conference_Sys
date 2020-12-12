package presenters.event;

import presenters.EventErrorPresenter;
import presenters.InputErrorPresenter;
import presenters.Presenter;
import presenters.TimePresenter;

/**
 * Responsible for displaying event rescheduling functionality prompts and notifications
 */
public class EventReschedulePresenter implements Presenter, InputErrorPresenter, EventErrorPresenter, TimePresenter {

    /**
     * Header of event rescheduling prompt
     */
    @Override
    public void startPrompt() {
        System.out.println();
        System.out.println("[RESCHEDULE EVENT]");
    }

    /**
     * Asks for ID of event
     */

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event to reschedule: ");
    }

    public void eventRescheduleFailureNotification() {
        System.out.println("{Event rescheduling cancelled.}");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Event Rescheduling successful!}");
    }

}
