package views.request;
import enums.ViewEnum;
import presenters.request.RequestSendPresenter;
import views.*;
import controllers.request.*;
import java.util.Scanner;

public class RequestCreationView implements View{
    public final RequestController rc;
    public final RequestSendPresenter rsp;
    private final Scanner userInput = new Scanner(System.in);

    public RequestCreationView(RequestController rc, RequestSendPresenter rsp) {
        this.rc = rc;
        this.rsp = rsp;
    }

    public ViewEnum runView(){
        rsp.startPrompt();
        rsp.subjectLinePrompt();

        String subjectLine = userInput.nextLine();

        rsp.requestContentPrompt();

        String content = userInput.nextLine();

        rsp.exitPrompt();
        return ViewEnum.VOID;
    }
}
