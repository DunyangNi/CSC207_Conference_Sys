package views.event;

import controllers.event.SignupController;
import enums.AttendeeMenuEnum;
import exceptions.conflict.AlreadySignedUpException;
import exceptions.conflict.EventIsFullException;
import exceptions.conflict.VipRestrictionException;
import exceptions.not_found.AttendeeNotFoundException;
import exceptions.not_found.EventNotFoundException;
import gateways.DataManager;
import presenters.event.SignupPresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class SignupView {
    private final SignupPresenter presenter;
    private final SignupController controller;
    private final Scanner userInput = new Scanner(System.in);

    public SignupView(SignupController controller, SignupPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public void runView(AttendeeMenuEnum command) {
        presenter.eventIdPrompt();
        int id = userInput.nextInt();

        if (command.equals(AttendeeMenuEnum.SIGNUP_EVENT)) {
            try {
                controller.signupForEvent(id);
            }
            catch (VipRestrictionException e) {
                presenter.VipRestrictionPrompt();
            }
            catch (EventIsFullException e){
                presenter.EventIsFullPrompt();
            }
            catch (EventNotFoundException e){
                presenter.EventNotFoundPrompt();
            } catch (AlreadySignedUpException e) {
                presenter.AlreadySignedUpPrompt();
            }
        }
        if (command.equals(AttendeeMenuEnum.LEAVE_EVENT)) {
            try {
                controller.cancelSignupForEvent(id);
            }
            catch (EventNotFoundException e){
                presenter.EventNotFoundPrompt();
            }
            catch (AttendeeNotFoundException e){
                presenter.AttendeeNotFoundPrompt();
            }
        }
    }
}
