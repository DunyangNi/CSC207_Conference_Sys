package views;

import controller.SpeakerController;
import presenter.SpeakerPresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class SpeakerView {
    private final AccountManager accountManager;
    private final SpeakerController controller;
    private final SpeakerPresenter presenter = new SpeakerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.controller = new SpeakerController(username, am, fm, cm, em);
    }

    public boolean viewSpeakerMenu() {
        presenter.startPrompt();
        presenter.speakerMenuPrompt();

        return controller.runInteraction();
    }
}
