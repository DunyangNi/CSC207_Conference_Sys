package views.account;

import gateways.DataManager;
import presenters.StartPresenter;

import java.util.Scanner;

public class StartView {
    private final DataManager dm;
    private final StartPresenter presenter = new StartPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public StartView(DataManager dm) {
        this.dm = dm;
    }

    public boolean startMenu() {
        presenter.startPrompt();
        String command = userInput.nextLine();

        while (!command.matches("[0-2]")) {
            presenter.invalidCommandPrompt();
            command = userInput.nextLine();
        }

        if (command.equals("1")) {
            LoginView view = new LoginView(dm);
            view.runView();
        }
        if (command.equals("2")) {
            RegistrationView view = new RegistrationView(dm);
            view.runView();
        }

        return false;
    }
}