package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import exceptions.conflict.AlreadySignedUpException;
import exceptions.conflict.EventIsFullException;
import exceptions.conflict.VipRestrictedException;
import exceptions.not_found.EventNotFoundException;
import presenters.event.SignupPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for event signup functionality
 */
public class SignupView implements View {
    private final SignupPresenter presenter;
    private final EventController controller;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>SignupView</code> based on the following parameters
     *
     * @param controller The given EventController
     * @param presenter  The given SignupPresenter
     */
    public SignupView(EventController controller, SignupPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return ViewEnum.VOID
     */
    public ViewEnum runView() {
        presenter.signupHeader();
        presenter.eventIDPrompt();
        try {
            int id = Integer.parseInt(userInput.nextLine());
            controller.signupForEvent(id);
            presenter.signupSuccessNotification();
            return ViewEnum.VOID;
        } catch (VipRestrictedException e) {
            presenter.vipRestrictionNotification();
        } catch (EventIsFullException e) {
            presenter.eventIsFullNotification();
        } catch (EventNotFoundException e) {
            presenter.eventNotFoundNotification();
        } catch (AlreadySignedUpException e) {
            presenter.alreadySignedUpNotification();
        } catch (NumberFormatException e) {
            presenter.invalidNumberNotification();
        }
        presenter.signupFailureNotification();
        return ViewEnum.VOID;
    }
}
