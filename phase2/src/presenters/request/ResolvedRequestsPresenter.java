package presenters.request;
import presenters.*;

public class ResolvedRequestsPresenter implements Presenter{
    public void startPrompt(){

    }

    //takes the string representation of the resolved request list that the controller gets from requestmanager
    public void resolvedRequestListDisplay(String resolvedRequestListStringRep){
        System.out.println("[LIST OF RESOLVED REQUESTS (MOST RECENT LAST)]");
        System.out.println(resolvedRequestListStringRep);
    }

    public void exitPrompt(){

    }
}
