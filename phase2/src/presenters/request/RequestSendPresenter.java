package presenters.request;
import presenters.*;

public class RequestSendPresenter implements Presenter{
    public void startPrompt(){
        System.out.println("\n[SEND US A SUGGESTION/REQUEST] \n");
    }

    public void subjectLinePrompt() {
        System.out.println("Please enter subject line here:");
    }

    public void requestContentPrompt() {
        System.out.println("What can we help you with?\nEnter your suggestion/request below:");
    }

    public void exitPrompt(){
        System.out.println("\nThank you. Your request will be considered shortly. \n");
    }
}
