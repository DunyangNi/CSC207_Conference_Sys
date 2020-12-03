package views;

import controller.OrganizerController;
import enums.OrganizerCommand;
import presenter.OrganizerPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class OrganizerView {
    private final AccountManager accountManager;
    private final OrganizerController controller;
    private final OrganizerPresenter presenter = new OrganizerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public OrganizerView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.controller = new OrganizerController(username, am, fm, cm, em);
    }

    public boolean viewOrganizerMenu() {
        presenter.startPrompt();

        presenter.organizerMenuPrompt();

        boolean programEnd = false;
        boolean loggedIn = true;
        while (loggedIn) {

            String inputCommand = userInput.nextLine();
            OrganizerCommand[] enumCommandList = OrganizerCommand.values();
            boolean validInput = false;
            OrganizerCommand enumCommand = OrganizerCommand.EXIT;

            while (!validInput) {
                for (OrganizerCommand command : enumCommandList) {
                    if (command.command.equals(inputCommand)) {
                        validInput = true;
                        enumCommand = command;
                        break;
                    }
                }
                if (!validInput) {
                    presenter.invalidInputPrompt();
                    presenter.requestCommandPrompt();
                    inputCommand = userInput.nextLine();
                }
            }

            controller.runInteraction(enumCommand);
        }
        return false;
    }
}
