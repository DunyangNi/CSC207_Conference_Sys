package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import presenters.request.RequestSendPresenter;
import enums.ViewEnum;

public class RequestSendView {

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

        controller.sendRequest("johndoe", subjectToSend, contentToSend);

        return ViewEnum.VOID;
    }
}
