package presenters.request;

public class ResolvedRequestsPresenter {
    //takes the string representation of the resolved request list that the controller gets from requestmanager
    public void displayResolvedRequestList(String resolvedRequestList) {
        System.out.println();
        System.out.println("[RESOLVED REQUESTS]");
        System.out.println(resolvedRequestList);
    }
}
