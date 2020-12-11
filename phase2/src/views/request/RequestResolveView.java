package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import exceptions.not_found.ObjectNotFoundException;
import presenters.request.RequestResolvePresenter;
import enums.ViewEnum;
import views.View;

public class RequestResolveView implements View {

    private final RequestController controller;
    private final RequestResolvePresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public RequestResolveView(RequestController controller, RequestResolvePresenter presenter){
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
