package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import presenters.request.RequestSendPresenter;
import enums.ViewEnum;
import views.start.View;

public class RequestSendView implements View {

    private final RequestController controller;
    private final RequestSendPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public RequestSendView(RequestController controller, RequestSendPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView(){
        presenter.startPrompt();

        presenter.subjectLinePrompt();
        String subjectToSend = userInput.nextLine();

        presenter.requestContentPrompt();
        String contentToSend = userInput.nextLine();

        controller.sendRequest(subjectToSend, contentToSend);

        presenter.exitPrompt();

        return ViewEnum.VOID;
    }
}
