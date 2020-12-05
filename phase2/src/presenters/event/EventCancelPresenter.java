package presenters.event;

import presenters.ConsolePresenter;

public class EventCancelPresenter implements ConsolePresenter {

    @Override
    public void startPrompt() {
        System.out.println("Please enter the ID of a talk you wish to cancel: ");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Cancel the event successfully.");
    }
}
