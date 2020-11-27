package presenter;

public class LoginPresenter implements ConsolePresenter {
    public void preUserInput() {
        System.out.println("[LOGIN MENU]");
        System.out.println("Enter your username:");
        System.out.println("* = Return to the start menu:");
    }

    public void takenUsernamePrompt() {
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
    public void postUserInput() {
        System.out.println("Logged in...");
    }
}
