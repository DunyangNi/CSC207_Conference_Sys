package views.account;

import controllers.account.AccountController;
import enums.*;
import presenters.account.AccountPresenter;
import views.View;

import java.util.Scanner;

public class AccountView implements View {
    private final AccountController controller;
    private final AccountPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public AccountView(AccountController controller, AccountPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        presenter.startPrompt();
        presenter.displayUserMenu();
        presenter.requestCommandPrompt();

        ViewEnum viewEnum = null;
        AccountTypeEnum accountType = controller.getAccountType();

        while (viewEnum != ViewEnum.LOGOUT && viewEnum != ViewEnum.EXIT) {
            // TODO Consider surrounding switch statement with try/catch for "View not found" exception
            switch (accountType) {
                case ORGANIZER:
                    viewEnum = this.getView(OrganizerMenuEnum.fromString(userInput.nextLine()));
                    break;
                case SPEAKER:
                    viewEnum = this.getView(SpeakerMenuEnum.fromString(userInput.nextLine()));
                    break;
                case ATTENDEE:
                    viewEnum = this.getView(AttendeeMenuEnum.fromString(userInput.nextLine()));
                    break;
            }
        }
        return viewEnum;
    }

    private <T> ViewEnum getView(T accountMenuEnum) {
        switch (accountMenuEnum.toString()) {
            case "EXIT":
                return ViewEnum.EXIT;
            case "LOGOUT":
                return ViewEnum.LOGOUT;
            case "VIEW_ALL_ACCOUNTS":
                presenter.displayAccountList(controller.getAccountList());
                break;
            case "VIEW_MENU":
                presenter.displayUserMenu();
                break;
            case "INVALID":
                presenter.invalidInputPrompt();
                break;
            default:
                return controller.getView(ViewEnum.valueOf(accountMenuEnum.toString())).runView();
        }
        controller.saveData();
        presenter.requestCommandPrompt();
        return ViewEnum.VOID;
    }
}
