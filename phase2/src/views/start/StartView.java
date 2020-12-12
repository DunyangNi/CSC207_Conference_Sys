package views.start;

import enums.ViewEnum;
import presenters.start.StartPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for the start menu.
 */
public class StartView implements View {
    private final StartPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Creates an instance of <code>StartView</code> with a given presenter
     *
     * @param presenter given <code>StartPresenter</code>
     */
    public StartView(StartPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Runs the view.
     *
     * @return LOGIN, REGISTRATION, EXIT
     */
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