package presenter;

public class LoginPresenter implements ConsolePresenter {
    public LoginPresenter() {
    }

    public void preUserInput() {
        System.out.println("[LOGIN MENU]");

    }

    public void preUsernameInput() {
        System.out.println("Enter '*' to return to the start menu.\nEnter your username:");
    }

    public void postUsernameInput() {
        System.out.println("This username does not exist, please try again. Enter '*' to return to the start menu.");
    }

    public void prePasswordInput() {
        System.out.println("Enter your password:");
    }

    public void postPasswordInput() {
        System.out.println("Incorrect password, please try again. Enter '*' to return to the start menu.");
    }

    public void postLoginInputs() {
        System.out.println("Incorrect username or password, please try again. Enter '*' to return to the start menu instead:");
    }

    @Override
    public void postUserInput() {
        System.out.println("Logging in...\n");
    }
}
