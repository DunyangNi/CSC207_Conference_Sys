package presenters.request;

import presenters.Presenter;

public class ResolvedRequestsPresenter implements Presenter {
    public void startPrompt() {
    }

    //takes the string representation of the resolved request list that the controller gets from requestmanager
    public void displayResolvedRequestList(String resolvedRequestList) {
        System.out.println();
        System.out.println("[RESOLVED REQUESTS]");
        System.out.println(resolvedRequestList);
    }

    public void exitPrompt() {

    }
}
