package views.account;

import controllers.account.AccountController;
import enums.ViewEnum;
import presenters.account.AccountPresenter;

import java.util.Scanner;

public class AccountView {
    private final AccountController controller;
    private final AccountPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public AccountView(AccountController controller, AccountPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public <T> ViewEnum getView(T menuEnum) {
            switch (menuEnum.toString()) {
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
                    return controller.getView(ViewEnum.valueOf(menuEnum.toString())).runView();
            }
            return ViewEnum.VOID;
    }

}
