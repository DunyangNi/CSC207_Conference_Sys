package presenters.request;
import presenters.*;

public class PendingRequestsPresenter implements Presenter{
    public void startPrompt(){

    }

    //takes the string representation of the pending request list that the controller gets from requestmanager
    public void pendingRequestListDisplay(String unresolvedRequestListStringRep){
        System.out.println("[LIST OF PENDING REQUESTS (MOST RECENT LAST)]");
        System.out.println(unresolvedRequestListStringRep);
    }

    public void exitPrompt(){

    }
}
