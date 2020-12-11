package views;

import enums.ViewEnum;
import presenters.StartPresenter;

import java.util.Scanner;

public class StartView implements View {
    private final StartPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public StartView(StartPresenter presenter) {
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.startPrompt();
        String command = userInput.nextLine();

        while (!command.matches("[0-2]")) {
            presenter.invalidCommandNotification();
            command = userInput.nextLine();
        }

        return switch (command) {
            case "1" -> ViewEnum.LOGIN;
            case "2" -> ViewEnum.REGISTRATION;
// "0"
            default -> ViewEnum.EXIT;
        };
    }
}