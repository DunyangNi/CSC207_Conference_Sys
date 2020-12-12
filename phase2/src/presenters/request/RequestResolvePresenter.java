package presenters.request;

public class RequestResolvePresenter {
    public void startPrompt() {
        System.out.println("Enter id of resolved request:");
    }

    public void requestNotFoundMessage() {
        System.out.println("{Invalid request ID.}");
    }

    public void exitPrompt() {
        System.out.println("{Request resolved.}");
    }
}
