package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import exceptions.not_found.ObjectNotFoundException;
import presenters.request.PendingRequestsPresenter;
import presenters.request.RequestResolutionPresenter;
import enums.ViewEnum;

public class PendingRequestsListView {

    private final RequestController controller;
    private final PendingRequestsPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public PendingRequestsListView(RequestController controller, PendingRequestsPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView(){
        presenter.startPrompt();

        String unresolvedList = controller.retrieveUnresolvedRequestListStringRep();
        presenter.pendingRequestListDisplay(unresolvedList);

        presenter.exitPrompt();
        return ViewEnum.VOID;
    }
}
