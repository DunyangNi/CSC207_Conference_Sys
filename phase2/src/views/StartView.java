package views;

import controller.StartController;
import presenter.StartPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class StartView {
    private final StartController controller;
    private final StartPresenter presenter = new StartPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public StartView(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.controller = new StartController(am, fm, cm, em);
    }

    public boolean viewStartMenu() {
        presenter.preUserInputPrompt();
        String command = userInput.nextLine();

        while (!(command.equals("0") || command.equals("1") || (command.equals("2")))) {
            presenter.invalidCommandPrompt();
            command = userInput.nextLine();
        }

        return controller.start(command);
    }
}


