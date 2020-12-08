package presenters.account;

import presenters.Presenter;

public class LoginPresenter implements Presenter {
    public void startPrompt() {
        System.out.println("[LOGIN MENU]");
    }

    public void usernamePrompt() {
        System.out.println("Enter your username or '*' to return to the start menu:");
    }

    public void dneUsernamePrompt() {
        System.out.println("{This username does not exist, please try again}");
    }

    public void passwordPrompt() {
        System.out.println("Enter your password or '*' to return to the start menu:");
    }

    public void incorrectPasswordPrompt() {
        System.out.println("{Incorrect password, please try again}");
    }

    @Override
    public void exitPrompt() {
        System.out.print("{Logging in... ");
    }
}
