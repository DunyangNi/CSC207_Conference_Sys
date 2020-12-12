package views.request;
import controllers.request.RequestController;
import views.start.View;
import presenters.request.ResolvedRequestsPresenter;
import enums.ViewEnum;

/**
 * View responsible for viewing all resolved requests
 */

public class ResolvedRequestsListView implements View{

    private final RequestController controller;
    private final ResolvedRequestsPresenter presenter;

    public ResolvedRequestsListView(RequestController controller, ResolvedRequestsPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView(){
        String resolvedList = controller.getResolvedRequestsString();

        presenter.displayResolvedRequestList(resolvedList);

        return ViewEnum.VOID;
    }
}
