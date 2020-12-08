package presenters.event;

import presenters.Presenter;

public class EventCancelPresenter extends EventPresenter implements Presenter {

    @Override
    public void startPrompt() {
        System.out.println("[CANCEL EVENT]");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Event successfully cancelled!");
    }

    public void IDPrompt() { System.out.println("Please enter the ID of an event you wish to cancel: "); }

    public void invalidIDPrompt() { System.out.println("Could not process ID."); }

    public void IDNotFoundPrompt() { System.out.println("This ID does not exist."); }
}
