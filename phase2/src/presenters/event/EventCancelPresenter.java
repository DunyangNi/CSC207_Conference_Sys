package presenters.event;

import presenters.EventErrorPresenter;
import presenters.Presenter;

/**
 * Responsible for displaying event cancellation functionality prompts and notifications
 */
public class EventCancelPresenter implements Presenter, EventErrorPresenter {

    @Override
    public void startPrompt() {
    }

    /**
     * Header of event cancellation prompt
     */
    public void cancelEventHeader() {
        System.out.println();
        System.out.println("[CANCEL EVENT]");
    }

    /**
     * Asks for ID of event to cancel
     */

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event to cancel: ");
    }

    public void invalidIDNotification() {
        System.out.println("{Invalid event ID}");
    }

    public void cancelEventFailureNotification() {
        System.out.println("{No events were cancelled}");
    }

    public void cancelEventSuccessNotification() {
        System.out.println("{Successfully cancelled event!}");
    }


    @Override
    public void exitPrompt() {
    }
}
