package presenter;

public class StartPresenter implements ConsolePresenter {
    @Override
    public void startPrompt() {
        System.out.println("[START MENU]");
        System.out.println("0 = Exit program:");
        System.out.println("1 = Login to your account:");
        System.out.println("2 = Register a new account:");
    }

    public void invalidCommandPrompt() {
        System.out.println("Invalid input, please try again:");
    }

    @Override
    public void exitPrompt() {
    }
}
