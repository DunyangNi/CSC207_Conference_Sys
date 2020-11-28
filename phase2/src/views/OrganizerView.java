package views;

import controller.OrganizerController;
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

        presenter.exitPrompt();
        return controller.runInteraction();
    }
}
