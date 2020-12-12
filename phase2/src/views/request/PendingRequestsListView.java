package views.request;
import controllers.request.RequestController;
import presenters.request.PendingRequestsPresenter;
import views.factory.View;
import enums.ViewEnum;

/**
 * View responsible for viewing all pending requests
 */

public class PendingRequestsListView implements View{

    private final RequestController controller;
    private final PendingRequestsPresenter presenter;

    /**
     * Constructor of the view
     * @param controller RequestController
     * @param presenter PendingRequestsPresenter
     */
    public PendingRequestsListView(RequestController controller, PendingRequestsPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Display all pending requests.
     * @return VOID to get back to AccountView
     */
    public ViewEnum runView(){
        String pendingRequestList = controller.getPendingRequestListString();

        presenter.displayPendingRequestList(pendingRequestList);

        return ViewEnum.VOID;
    }
}
