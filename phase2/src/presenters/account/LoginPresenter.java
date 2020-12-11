package presenters.account;

import presenters.Presenter;

public class LoginPresenter implements Presenter {
    @Override
    public void startPrompt() {
        System.out.println();
        System.out.println("[LOGIN MENU]");
    }

    public void usernamePrompt() {
        System.out.println("Enter your username:");
    }

    public void usernameNotFoundNotification() {
        System.out.println("{This username does not exist, please try again.}");
    }

    public void passwordPrompt() {
        System.out.println("Enter your password:");
    }

    public void incorrectPasswordNotification() {
        System.out.println("{Incorrect password, please try again.}");
    }

    public void failedLoginPrompt() {
        System.out.println("Try again? (Y/N)");
    }

    @Override
    public void exitPrompt() {
        System.out.println("{Logging in... Logged in}");
    }
}
