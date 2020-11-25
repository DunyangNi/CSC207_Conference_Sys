package presenter;

public class LoginPresenter implements ConsolePresenter {
    @Override
    public void preUserInput() {
    }

    @Override
    public void postUserInput() {
    }

    @Override
    public void preUserInput(String input){
        switch (input) {
            case "username":
                System.out.println("[LOGIN MENU]");
                System.out.println("Enter your username:");
                break;
            case "password":
                System.out.println("Enter your password:");
                break;
        }
    }

    @Override
    public void postUserInput(String input) {
        switch (input) {
            case "username":
                System.out.println("This username does not exist, please try again. Enter '*' to return to the start menu.");
                break;
            case "password":
                System.out.println("Incorrect password, please try again. Enter '*' to return to the start menu.");
                break;
            case "login":
                System.out.println("Logging in...\n");
        }
    }
}
