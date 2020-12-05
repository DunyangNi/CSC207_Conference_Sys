package presenters.start;

import presenters.ConsolePresenter;

public class LoginPresenter implements ConsolePresenter {
    public void startPrompt() {
        System.out.println("[LOGIN MENU]");
        System.out.println("(* = Return to the start menu)");
        System.out.println("Enter your username:");
    }

    public void dneUsernamePrompt() {
        System.out.println("(* = Return to the start menu)");
        System.out.println("This username does not exist, please try again:");
    }

    public void passwordPrompt() {
        System.out.println("Enter your password:");
    }

    public void incorrectPasswordPrompt() {
        System.out.println("(* = Return to the start menu)");
        System.out.println("Incorrect password, please try again:");
    }

    @Override
    public void exitPrompt() {
        System.out.print("(Logging in... ");
    }
}
