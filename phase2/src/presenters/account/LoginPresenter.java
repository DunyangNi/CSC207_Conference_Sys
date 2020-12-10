package presenters.account;

import presenters.Presenter;

public class LoginPresenter implements Presenter {
    public void startPrompt() {
        System.out.println("[LOGIN MENU]");
    }

    public void usernamePrompt() {
        System.out.println("Enter your username:");
    }

    public void dneUsernamePrompt() {
        System.out.println("{This username does not exist, please try again}");
    }

    public void passwordPrompt() {
        System.out.println("Enter your password:");
    }

    public void incorrectPasswordPrompt() {
        System.out.println("{Incorrect password, please try again}");
    }

    public void failedPrompt() {System.out.println("Do you still want to login(Enter 'Y' to continue login, otherwise return to Start Menu):");}

    @Override
    public void exitPrompt() {
        System.out.print("{Logging in... ");
    }
}
