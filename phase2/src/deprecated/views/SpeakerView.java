package views.account;

import controllers.account.AccountController;
import enums.SpeakerMenuEnum;
import enums.SpeakerMenuEnum;
import enums.ViewEnum;
import presenters.account.AccountPresenter;
import views.View;

import java.util.Scanner;

public class SpeakerView extends AccountView {
    private final AccountController controller;
    private final AccountPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(AccountController controller, AccountPresenter presenter) {
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
            SpeakerMenuEnum speakerMenuEnum = SpeakerMenuEnum.fromString(userInput.nextLine());
            viewEnum = getView(speakerMenuEnum);

            controller.saveData(); // TODO Consider moving this to ConferenceSystem
            presenter.savedDataPrompt();
            presenter.requestCommandPrompt();
        }
        return ViewEnum.LOGOUT;
    }
}