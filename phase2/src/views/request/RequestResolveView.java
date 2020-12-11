package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import exceptions.not_found.ObjectNotFoundException;
import presenters.request.RequestResolutionPresenter;
import enums.ViewEnum;

public class RequestResolveView {

    private final RequestController controller;
    private final RequestResolutionPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public RequestResolveView(RequestController controller, RequestResolutionPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

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
