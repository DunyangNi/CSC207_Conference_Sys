package presenters.request;

public class PendingRequestsPresenter {
    //takes the string representation of the pending request list that the controller gets from requestmanager
    public void displayPendingRequestList(String pendingRequestList) {
        System.out.println();
        System.out.println("[PENDING REQUESTS]");
        System.out.println(pendingRequestList);
    }
}
