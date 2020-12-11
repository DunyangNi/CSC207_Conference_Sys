package presenters.request;

import presenters.Presenter;

public class PendingRequestsPresenter implements Presenter {
    public void startPrompt() {
    }

    //takes the string representation of the pending request list that the controller gets from requestmanager
    public void displayPendingRequestList(String pendingRequestList) {
        System.out.println();
        System.out.println("[PENDING REQUESTS]");
        System.out.println(pendingRequestList);
    }

    public void exitPrompt() {
    }
}
