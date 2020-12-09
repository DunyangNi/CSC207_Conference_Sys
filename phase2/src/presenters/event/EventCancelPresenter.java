package presenters.event;

import presenters.Presenter;

public class EventCancelPresenter implements Presenter, EventErrorPresenter {

    @Override
    public void startPrompt() {
        System.out.println("[CANCEL EVENT]");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Event successfully cancelled! :)");
    }

    public void eventIDPrompt() { System.out.println("Please enter the ID of an event to cancel: "); }

    public void invalidIDPrompt() {
        System.out.println("The ID entered could not be recognized. Ensure the input is a non-negative number.");
    }

    public void cancelExitPrompt() { System.out.println("No events were cancelled. :(");}
}
