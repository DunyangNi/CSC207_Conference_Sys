package presenters.event;

import presenters.EventErrorPresenter;
import presenters.Presenter;

public class EventCancelPresenter implements Presenter, EventErrorPresenter {

    @Override
    public void startPrompt() {
    }

    @Override
    public void exitPrompt() {
    }

    public void cancelEventHeader() {
        System.out.println("[CANCEL EVENT]");
    }

    public void cancelEventFailureNotification() {
        System.out.println("{No events were cancelled}");
    }

    public void cancelEventSuccessNotification() {
        System.out.println("{Successfully cancelled event!}");
    }

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event to cancel: ");
    }

    public void invalidIDNotification() {
        System.out.println("{Invalid event ID}");
    }
}
