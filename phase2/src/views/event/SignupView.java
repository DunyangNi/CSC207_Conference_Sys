package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import exceptions.conflict.AlreadySignedUpException;
import exceptions.conflict.EventIsFullException;
import exceptions.conflict.VipRestrictedException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.SignupPresenter;
import views.View;

import java.util.Scanner;

public class SignupView implements View {
    private final SignupPresenter presenter;
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    public SignupView(EventController controller, SignupPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.eventIdPrompt();
        try {
            int id = Integer.parseInt(userInput.nextLine());
            controller.signupForEvent(id);
            presenter.exitPrompt();
            return ViewEnum.VOID;
        }
        catch (VipRestrictedException e) { presenter.vipRestrictionPrompt(); }
        catch (EventIsFullException e){ presenter.eventIsFullPrompt(); }
        catch (EventNotFoundException e){ presenter.eventNotFoundNotification(); }
        catch (AlreadySignedUpException e) { presenter.alreadySignedUpPrompt(); }
        catch (NumberFormatException e) { presenter.invalidNumberPrompt(); }
        presenter.cancelExitPrompt();
        return ViewEnum.VOID;
    }
}
