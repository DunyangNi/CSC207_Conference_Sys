package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import exceptions.not_found.ObjectNotFoundException;
import presenters.request.RequestResolvePresenter;
import enums.ViewEnum;
import views.factory.View;

/**
 * View responsible for resolving requests
 */

public class RequestResolveView implements View {

    private final RequestController controller;
    private final RequestResolvePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructor of the view
     * @param controller RequestController
     * @param presenter RequestRequestsPresenter
     */
    public RequestResolveView(RequestController controller, RequestResolvePresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     *The view that user can use to mark request as resolved.
     * @return VOID to get back to AccountView
     */
    public ViewEnum runView(){
        presenter.startPrompt();
        int idToResolve = Integer.parseInt(userInput.nextLine());

        try {
            controller.resolveRequest(idToResolve);
            presenter.exitPrompt();
        }
        catch (ObjectNotFoundException e){
            presenter.requestNotFoundMessage();
        }
        return ViewEnum.VOID;
    }
}
