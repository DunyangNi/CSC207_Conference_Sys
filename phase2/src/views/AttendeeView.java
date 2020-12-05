package views;

import controller.AttendeeController;
import presenter.AttendeePresenter;
import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;

import java.util.Scanner;

public class AttendeeView {
    private final AccountManager accountManager;
    private final AttendeeController controller;
    private final AttendeePresenter presenter = new AttendeePresenter();
    private final Scanner userInput = new Scanner(System.in);

    public AttendeeView(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.accountManager = am;
        this.controller = new AttendeeController(username, am, fm, cm, em);
    }

    public void viewAttendeeMenu() {
        presenter.startPrompt();
        presenter.attendeeMenuPrompt();
    }
}
