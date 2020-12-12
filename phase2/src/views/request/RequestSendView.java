package views.request;
import java.util.Scanner;
import controllers.request.RequestController;
import presenters.request.RequestSendPresenter;
import enums.ViewEnum;
import views.factory.View;

/**
 * View responsible for sending requests
 */

public class RequestSendView implements View {

    private final RequestController controller;
    private final RequestSendPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructor of the view
     * @param controller Given RequestController
     * @param presenter Given RequestSendPresenter
     */
    public RequestSendView(RequestController controller, RequestSendPresenter presenter){
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * The view user can send new request.
     * @return VOID to get back to AccountView
     */
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
