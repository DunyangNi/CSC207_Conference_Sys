package views;

import enums.ViewEnum;
import presenters.StartPresenter;

import java.util.Scanner;

/**
 * View responsible for the start menu
 */

public class StartView implements View {
    private final StartPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public StartView(StartPresenter presenter) {
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startHeader();
        presenter.startMenu();
        String command = userInput.nextLine();

        while (!command.matches("[0-2]")) {
            presenter.invalidCommandNotification();
            command = userInput.nextLine();
        }

        switch (command) {
            case "1":
                return ViewEnum.LOGIN;
            case "2":
                return ViewEnum.REGISTRATION;
            default:
                return ViewEnum.EXIT;
        }
    }
}