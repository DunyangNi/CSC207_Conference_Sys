package presenters.request;
import presenters.*;

public class RequestResolutionPresenter implements Presenter{
    public void startPrompt() {
        System.out.println("Enter id of resolved request:");
    }

    public void requestNotFoundMessage(){
        System.out.println("Request does not exist in pending pending request list. Please enter a valid id.");
    }
    public void exitPrompt() {
        System.out.println("Request successfully removed from pending request list");
    }
}
