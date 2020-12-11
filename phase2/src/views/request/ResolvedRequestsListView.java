package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import views.View;
import presenters.request.ResolvedRequestsPresenter;
import enums.ViewEnum;

public class ResolvedRequestsListView implements View{

    private final RequestController controller;
    private final ResolvedRequestsPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public ResolvedRequestsListView(RequestController controller, ResolvedRequestsPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView(){
        presenter.startPrompt();

        String resolvedList = controller.retrieveResolvedRequestListStringRep();
        presenter.resolvedRequestListDisplay(resolvedList);

        presenter.exitPrompt();
        return ViewEnum.VOID;
    }
}
