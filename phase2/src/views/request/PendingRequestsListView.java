package views.request;
import controllers.request.RequestController;
import presenters.request.PendingRequestsPresenter;
import views.start.View;
import enums.ViewEnum;

public class PendingRequestsListView implements View{

    private final RequestController controller;
    private final PendingRequestsPresenter presenter;

    public PendingRequestsListView(RequestController controller, PendingRequestsPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView(){
        String pendingRequestList = controller.getPendingRequestListString();

        presenter.displayPendingRequestList(pendingRequestList);

        return ViewEnum.VOID;
    }
}
