package presenter;

public class LoginPresenter implements ConsolePresenter {
    public void startPrompt() {
        System.out.println("[LOGIN MENU]");
        System.out.println("Enter your username:");
        System.out.println("* = Return to the start menu:");
    }

    public void dneUsernamePrompt() {
        System.out.println("This username does not exist, please try again:");
        System.out.println("* = Return to the start menu:");
    }

    public void passwordPrompt() {
        System.out.println("Enter your password:");
    }

    public void incorrectPasswordPrompt() {
        System.out.println("Incorrect password, please try again:");
        System.out.println("* = Return to the start menu:");
    }

    @Override
    public void exitPrompt() {
        System.out.println("Logged in...");
    }
}
