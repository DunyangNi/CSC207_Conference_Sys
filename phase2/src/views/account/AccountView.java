package views.account;

import controllers.account.AccountController;
import enums.*;
import presenters.account.AccountPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for user menu display and interaction
 */
public class AccountView implements View {
    private final AccountController controller;
    private final AccountPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Constructs an instance of <code>AccountView</code> based on the following parameters
     *
     * @param controller The given AccountController
     * @param presenter  The given AccountPresenter
     */
    public AccountView(AccountController controller, AccountPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return viewEnum with a value dependent on account type and menu option selected, which then leads
     * the program into another view
     */
    @Override
    public ViewEnum runView() {
        presenter.displayUserMenu();
        presenter.requestCommandPrompt();

        ViewEnum viewEnum = null;
        AccountTypeEnum accountType = controller.getAccountType();

        while (viewEnum != ViewEnum.START && viewEnum != ViewEnum.EXIT) {
            switch (accountType) {
                case ORGANIZER:
                    viewEnum = getViewEnum(OrganizerMenuEnum.fromString(userInput.nextLine()));
                    break;
                case SPEAKER:
                    viewEnum = getViewEnum(SpeakerMenuEnum.fromString(userInput.nextLine()));
                    break;
                case ATTENDEE:
                    viewEnum = getViewEnum(AttendeeMenuEnum.fromString(userInput.nextLine()));
                    break;
                case VIP_ATTENDEE:
                    viewEnum = getViewEnum(VipAttendeeMenuEnum.fromString(userInput.nextLine()));
                    break;
            }
        }
        return viewEnum;
    }

    private <T> ViewEnum getViewEnum(T accountMenuEnum) {
        String stringMenuEnum = accountMenuEnum.toString();
        switch (stringMenuEnum) {
            case "EXIT":
                return ViewEnum.EXIT;
            case "LOGOUT":
                return ViewEnum.START;
            case "VIEW_ALL_ACCOUNTS":
                presenter.displayAccountList(controller.getAccountList());
                break;
            case "VIEW_MENU":
                presenter.displayUserMenu();
                break;
            case "INVALID":
                presenter.invalidCommandNotification();
                break;
            default:
                controller.getView(ViewEnum.valueOf(stringMenuEnum)).runView();
        }
        controller.saveData();
        presenter.requestCommandPrompt();
        return ViewEnum.VOID;
    }
}
