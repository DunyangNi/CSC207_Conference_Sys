package views.request;
import controllers.request.RequestController;
import views.factory.View;
import presenters.request.ResolvedRequestsPresenter;
import enums.ViewEnum;

/**
 * View responsible for viewing all resolved requests
 */

public class ResolvedRequestsListView implements View{

    private final RequestController controller;
    private final ResolvedRequestsPresenter presenter;

    /**
     * Constructor of the view
     * @param controller Given RequestController
     * @param presenter Given ResolvedRequestsPresenter
     */
    public ResolvedRequestsListView(RequestController controller, ResolvedRequestsPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * The view display all of resolved requests.
     * @return VOID to get back to AccountView
     */
    public ViewEnum runView(){
        String resolvedList = controller.getResolvedRequestsString();

        presenter.displayResolvedRequestList(resolvedList);

        return ViewEnum.VOID;
    }
}
