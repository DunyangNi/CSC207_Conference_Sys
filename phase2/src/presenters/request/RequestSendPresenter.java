package presenters.request;

import presenters.Presenter;

public class RequestSendPresenter implements Presenter {
    public void startPrompt() {
        System.out.println();
        System.out.println("[CREATE A REQUEST]");
    }

    public void subjectLinePrompt() {
        System.out.println("Please enter a subject line:");
    }

    public void requestContentPrompt() {
        System.out.println("Enter your suggestion/request below:");
    }

    public void exitPrompt() {
        System.out.println("{Request sent.}");
    }
}
