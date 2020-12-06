package presenters.event;

import presenters.Presenter;

public class EventCancelPresenter implements Presenter {

    @Override
    public void startPrompt() {
        System.out.println("Please enter the ID of a talk you wish to cancel: ");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Cancel the event successfully.");
    }
}
