package views;

import use_cases.AccountManager;
import use_cases.ConversationManager;
import use_cases.EventManager;
import use_cases.FriendManager;
import controller.LoginController;
import presenter.*;

import java.util.Scanner;

public class LoginView {
    private final LoginController controller;
    private final LoginPresenter presenter = new LoginPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        this.controller = new LoginController(am, fm, cm, em);
    }
}