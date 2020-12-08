package presenters.event;

import presenters.Presenter;

public class EventCancelPresenter extends EventPresenter implements Presenter {

    @Override
    public void startPrompt() {
        System.out.println("[CANCEL EVENT]");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Cancelling... Cancelled}");
    }
}
