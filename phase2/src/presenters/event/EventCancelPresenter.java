package presenters.event;

import presenters.interfaces.EventErrorPresenter;

public class EventCancelPresenter implements EventErrorPresenter {
    public void cancelEventHeader() { System.out.println("\n[CANCEL EVENT]"); }

    public void cancelEventFailureNotification() {
        System.out.println("{No events were cancelled.}");
    }

    public void cancelEventSuccessNotification() {
        System.out.println("{Successfully cancelled event!}");
    }

    public void eventIDPrompt() {
        System.out.println("Please enter the ID of an event to cancel: ");
    }

    public void invalidIDNotification() {
        System.out.println("{Sorry, the ID entered is invalid.}");
    }
}
