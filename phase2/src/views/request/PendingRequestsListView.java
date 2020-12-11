package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import presenters.request.PendingRequestsPresenter;
import views.View;
import enums.ViewEnum;

public class PendingRequestsListView implements View{

    private final RequestController controller;
    private final PendingRequestsPresenter presenter;

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
