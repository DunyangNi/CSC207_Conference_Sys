package views.account;

import controllers.account.AccountController;
import entities.account.Attendee;
import enums.AttendeeMenuEnum;
import enums.SpeakerMenuEnum;
import enums.ViewEnum;
import presenters.account.AccountPresenter;
import views.View;

import java.util.Scanner;

public class AttendeeView extends AccountView {
    private final AccountController controller;
    private final AccountPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public AttendeeView(AccountController controller, AccountPresenter presenter) {
        super(controller, presenter);
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.displayUserMenu();
        presenter.requestCommandPrompt();

        ViewEnum viewEnum = null;
        while (viewEnum != ViewEnum.LOGOUT) {
            AttendeeMenuEnum attendeeMenuEnum = AttendeeMenuEnum.fromString(userInput.nextLine());
            viewEnum = getView(attendeeMenuEnum);

            controller.saveData(); // TODO Consider moving this to ConferenceSystem
            presenter.savedDataPrompt();
            presenter.requestCommandPrompt();
        }
        return ViewEnum.LOGOUT;
    }
}