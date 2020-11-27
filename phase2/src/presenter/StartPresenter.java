package presenter;

public class StartPresenter implements ConsolePresenter {
    @Override
    public void preUserInput() {
        System.out.println("[START MENU]");
        System.out.println("0 = Exit program:\n1 = Login to your account:\n2 = Register a new account:");
    }

    @Override
    public void postUserInput() {
        System.out.println("Invalid input, please try again.");
    }
}
